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

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Yeni kullanıcıyı kaydeder.
     * @param user Kullanıcı bilgileri
     * @return Kayıtlı kullanıcı nesnesi
     */
    public User registerNewUser(User user) {
        if (isEmailTaken(user.getEmail())) {
            throw new RuntimeException("Bu email adresi zaten kullanılıyor.");
        }

        // Şifreyi güvenli şekilde hashle
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Eğer kullanıcının rolü yoksa, ona "ROLE_USER" ver
        if (user.getRoles().isEmpty()) {
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

    /**
     * E-posta adresi veritabanında var mı kontrol eder.
     * @param email Kullanıcı e-postası
     * @return Eğer e-posta adresi varsa true, yoksa false döner.
     */
    public boolean isEmailTaken(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    /**
     * E-posta ile kullanıcıyı getirir.
     * @param email Kullanıcı e-postası
     * @return Kullanıcı nesnesi (Optional)
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
