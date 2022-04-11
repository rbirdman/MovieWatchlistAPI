package io.swagger.configuration;

import io.swagger.entity.users.User;
import io.swagger.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Getter
@Setter
@Configuration
public class AdminPolicyConfig {

    @Value("${app.admin-policy.admin-users}")
    private List<String> adminUsers = new ArrayList<>();

    @Value("${app.admin-policy.admin-secret}")
    private String adminSecret;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminPolicyConfig(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    void initializeAdminUsers() {
        // Clean up existing admin users
        StreamSupport.stream(userRepository.findAll().spliterator(), true)
                .filter(User::isAdmin)
                .forEach(userRepository::delete);

        // Provision configured admin users
        for (String adminUser : adminUsers) {
            User admin = new User();
            admin.setEmail(adminUser);
            admin.setPassword(passwordEncoder.encode(adminSecret));
            admin.setAdmin(true);
            userRepository.save(admin);
        }

        System.out.println("Successfully initialized users " + adminUsers.toString());
    }

}
