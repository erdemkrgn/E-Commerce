package com.example.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Bu sınıf, uygulamanın güvenlik yapılandırmasını içerir.
public class SecurityConfig {

    // Yeni güvenlik yapılandırması: SecurityFilterChain bean'i tanımlıyoruz.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // URL'lere erişim kuralları belirliyoruz.
                .authorizeHttpRequests(authorize -> authorize
                        // "/" ve "/index" sayfalarına, ayrıca "/css/**" ve "/js/**" gibi statik kaynaklara herkese izin veriyoruz.
                        .requestMatchers("/", "/index", "/css/**", "/js/**").permitAll()
                        // Diğer tüm istekler doğrulama gerektirsin.
                        .anyRequest().authenticated()
                )
                // Özel giriş sayfası ve form tabanlı oturum açmayı ayarlıyoruz.
                .formLogin(form -> form
                        .loginPage("/login") // Özel giriş sayfası
                        .permitAll()         // Giriş sayfasına herkes erişebilsin
                )
                // "Remember-me" (beni hatırla) özelliğini cookie ile etkinleştiriyoruz.
                .rememberMe(remember -> remember
                        .key("uniqueAndSecret") // Güvenlik anahtarı
                        .tokenValiditySeconds(7 * 24 * 60 * 60) // Token geçerlilik süresi: 7 gün
                )
                // Çıkış işlemini yapılandırıyoruz.
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    // Bellek içi (in-memory) kullanıcı tanımlaması yapıyoruz.
    // Gerçek bir uygulamada, kullanıcılarınızı veritabanından çekecek şekilde yapılandırmanız önerilir.
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder() // Parola şifrelemesi demo amaçlıdır.
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("adminpass")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
