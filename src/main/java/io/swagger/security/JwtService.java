package io.swagger.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.entity.auth.JwtTokenCache;
import io.swagger.repository.JwtCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class JwtService {

    private final String secret;
    private final static String API_ISSUER = "MovieWatchlistAPI";
    private final JwtCacheRepository jwtCacheRepository;

    @Autowired
    public JwtService(@Value("${app.security.jwtSecret}") String secret, JwtCacheRepository jwtRepository) {
        this.secret = secret;
        this.jwtCacheRepository = jwtRepository;
    }

    public String generateToken(Authentication authentication) throws IllegalArgumentException, JWTCreationException {
        String jwtId = UUID.randomUUID().toString();
        Date currentDate = new Date();
        Date expiryDate = Date.from(currentDate.toInstant().plus(15, ChronoUnit.MINUTES));
        String email = ((User) authentication.getPrincipal()).getUsername();
        List<String> authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // Keep track of generated JWT IDs
        jwtCacheRepository.save(new JwtTokenCache(jwtId, expiryDate));

        return JWT.create()
                .withSubject(email)
                .withJWTId(jwtId)
                .withClaim("email", email)
                .withClaim("roles", authorities)
                .withIssuedAt(currentDate)
                .withIssuer(API_ISSUER)
                .withExpiresAt(expiryDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer(API_ISSUER)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        if (!jwtCacheRepository.existsById(jwt.getId())) {
            throw new JWTVerificationException("JWT is invalid or has been invalidated");
        }
        return jwt.getClaim("email").asString();
    }

    public void invalidateToken(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer(API_ISSUER)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        if (!jwtCacheRepository.existsById(jwt.getId())) {
            throw new JWTVerificationException("JWT is invalid or has already been invalidated");
        }
        jwtCacheRepository.deleteById(jwt.getId());
    }

    @Scheduled(cron = "*/15 * * * *")
    public void cleanupExpiredJwtCache() {
        Date today = new Date();
        StreamSupport.stream(jwtCacheRepository.findAll().spliterator(), true)
                .filter(jwtTokenCache -> today.after(jwtTokenCache.getExpiry()))
                .forEach(jwtCacheRepository::delete);
        System.out.println("Cleaned up expired JWTs");
    }

}
