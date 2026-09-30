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

    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    SecurityConfig(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }

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
                    .requestMatchers("/usuario/**")
                    .hasAnyRole("ROLE_USER", "ROLE_ADMIN")

                    // Todas as outras páginas ficam exclusivas do ADMIN.
                    .anyRequest()
                    .hasRole("ROLE_ADMIN")
                )
            .formLogin(form -> form
                .loginPage("/login")
                .successHandler(customAuthenticationSuccessHandler)
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

