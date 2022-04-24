package io.swagger.service;

import io.swagger.entity.users.User;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Component
public class MovieUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public MovieUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Could not find user with email = " + email);
        }

        String role = user.get().isAdmin() ? "ROLE_ADMIN" : "ROLE_USER";

        return new org.springframework.security.core.userdetails.User(
                user.get().getId().toString(),
                user.get().getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(role)));
    }

    public UserDetails loadUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            //throw new UsernameNotFoundException("Could not find user with id = " + id);
            return null;
        }

        String role = user.get().isAdmin() ? "ROLE_ADMIN" : "ROLE_USER";

        return new org.springframework.security.core.userdetails.User(
                user.get().getEmail(),
                user.get().getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(role)));
    }
}
