package com.rish889.product.service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@Configuration
public class SpringSecurityConfiguration {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf().disable()
                .httpBasic(Customizer.withDefaults())
                .authorizeExchange()
                .pathMatchers("/").permitAll()
                .pathMatchers("/v1/products").permitAll()
                .pathMatchers("/v1/products/*").permitAll()
                .anyExchange().authenticated()
                .and().formLogin()
                .and()
                .build();
    }
}
