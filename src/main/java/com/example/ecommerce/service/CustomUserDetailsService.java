package com.example.ecommerce.service;

import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(usernameOrEmail);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Kullanıcı bulunamadı: " + usernameOrEmail);
        }

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.get().getEmail())  // Kullanıcı adı olarak email kullanılıyor
                .password(user.get().getPassword())
                .roles(user.get().getRoles().toArray(new String[0]))
                .build();
    }
}
