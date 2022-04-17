package io.swagger.api.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.entity.users.User;
import io.swagger.model.auth.TokenCredentials;
import io.swagger.model.users.UserCredentials;
import io.swagger.repository.UserRepository;
import io.swagger.security.JwtService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-04-02T22:43:09.213512-04:00[America/New_York]")
@RestController
public class AuthApiController implements AuthApi {

    private static final Logger log = LoggerFactory.getLogger(AuthApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthApiController(ObjectMapper objectMapper, HttpServletRequest request, JwtService jwtService, AuthenticationManager authManager, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.jwtService = jwtService;
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<Void> authTokenDelete() {
        String authHeader = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authHeader)) {
            return new ResponseEntity("Request must provide bearer token for authenticaiton.",
                    HttpStatus.UNAUTHORIZED);
        }
        try {
            String bearerToken = org.apache.commons.lang3.StringUtils.removeStart(authHeader, "Bearer").trim();
            jwtService.invalidateToken(bearerToken);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Invalid bearer token", HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<TokenCredentials> authTokenPost() {
        String authHeader = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authHeader)) {
            return new ResponseEntity("Request must provide email and password for basic authentication.",
                    HttpStatus.UNAUTHORIZED);
        }
        try {
            UserCredentials userCredentials = UserCredentials.from(authHeader);
            UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
                    userCredentials.getEmail(),
                    userCredentials.getPassword());

            Authentication authentication = authManager.authenticate(authInputToken);
            String token = jwtService.generateToken(authentication);

            return ResponseEntity.ok().body(new TokenCredentials().accessToken(token));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }

    }

    @Override
    public ResponseEntity<Void> userRegistration(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody UserCredentials userCredentials) {
        if (userRepository.findByEmail(userCredentials.getEmail()).isEmpty()) {
            User user = new User();
            user.setEmail(userCredentials.getEmail());
            user.setPassword(passwordEncoder.encode(userCredentials.getPassword()));
            userRepository.save(user);
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity("Email already has been registered and cannot be modified", HttpStatus.CONFLICT);
        }
    }

}
