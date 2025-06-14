package com.further.service;

import com.further.model.User;
import com.further.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userDetailsService")
public class UserModelDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserModelDetailsService.class);
    private final UserRepository userRepository;

    public UserModelDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating user '{}'", login);
        String lowercaseLogin = login.toLowerCase();

        User user = userRepository.findByUsername(lowercaseLogin)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return createSpringSecurityUser(lowercaseLogin, user);
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) {
        if (!user.isActivated()) {
            throw new RuntimeException("User " + lowercaseLogin + " was not activated");
        }

        // Prefix role with "ROLE_" if not already prefixed
        String rawRole = user.getRole();
        String roleWithPrefix = rawRole.startsWith("ROLE_") ? rawRole : "ROLE_" + rawRole;

        List<GrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority(roleWithPrefix));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                grantedAuthorities
        );
    }
}
