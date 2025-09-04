package io.github.Rodfc773.rpg_references_api.users.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter){
        this.jwtAuthenticationFilter=jwtAuthenticationFilter;
    }

    private static final String[] PUBLIC_ROUTES = {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/api/v1/users/create"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       try {
           http.csrf(AbstractHttpConfigurer::disable)
                   .authorizeHttpRequests(auth -> auth
                           .requestMatchers(PUBLIC_ROUTES).permitAll()
                           .anyRequest().authenticated()
                   )
                   .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class);

           return http.build();
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
