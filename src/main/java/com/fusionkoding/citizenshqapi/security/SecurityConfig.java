package com.fusionkoding.citizenshqapi.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and() // (1)
                .authorizeRequests().anyRequest().authenticated() // (2)
                .and().oauth2ResourceServer().jwt(); // (3)
    }
}
