package br.com.petweb.petweb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable( ))

            /* Configura as permissões das páginas.*/
            .authorizeHttpRequests(auth -> auth

                    // Páginas públicas.
                    .requestMatchers(
                        "/login",
                        "/petweb",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/usuarios/criar",
                        "/usuarios/salvar"
                    )
                    .permitAll()

                    // Área permitida para USER e ADMIN.
                    .requestMatchers("/area-usuario/**")
                    .hasAnyRole("USER", "ADMIN")

                    // Todas as outras páginas ficam exclusivas do ADMIN.
                    .anyRequest()
                    .hasRole("ADMIN")
                )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/area-usuario", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );
        return http.build( );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

}

