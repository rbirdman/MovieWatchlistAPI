package io.swagger.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final String secret;
    private final static String API_ISSUER = "MovieWatchlistAPI";

    public JwtUtil(@Value("${app.security.jwtSecret}") String secret) {
        this.secret = secret;
    }

    public String generateToken(Authentication authentication) throws IllegalArgumentException, JWTCreationException {
        Date currentDate = new Date();
        Date expiryDate = Date.from(currentDate.toInstant().plus(15, ChronoUnit.MINUTES));
        String email = ((User) authentication.getPrincipal()).getUsername();
        List<String> authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return JWT.create()
                .withSubject(email)
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
        return jwt.getClaim("email").asString();
    }

}
