package com.example.ecommerce.service;

import com.example.ecommerce.model.User;
import com.example.ecommerce.model.Role;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUser(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Bu email adresi zaten kullanılıyor.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRoles().isEmpty()){
            Role roleUser = roleRepository.findByName("ROLE_USER")
                    .orElseGet(() -> {
                        Role newRole = new Role();
                        newRole.setName("ROLE_USER");
                        return roleRepository.save(newRole);
                    });
            user.getRoles().add(roleUser);
        }
        return userRepository.save(user);
    }
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
