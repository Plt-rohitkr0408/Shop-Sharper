package com.shopsharper.auth_service.config;

import com.shopsharper.auth_service.security.JwtConfigurationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig  {

    private final UserDetailsService userDetailsService;
    private final JwtConfigurationFilter  jwtConfigurationFilter;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService , JwtConfigurationFilter jwtConfigurationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtConfigurationFilter = jwtConfigurationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration
                                                               authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                csrf(c-> c.disable())
                .authorizeHttpRequests(auth->
                        auth.requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/v1/categories/**").permitAll()
                                .requestMatchers("/api/v1/products/**").permitAll()
                        .anyRequest().authenticated()
                        )
                .httpBasic(b->{})
                .authenticationProvider(authenticationProvider())
                .sessionManagement(session->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        ))
                .addFilterBefore(jwtConfigurationFilter , UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*");
            }
        };
    }
}
