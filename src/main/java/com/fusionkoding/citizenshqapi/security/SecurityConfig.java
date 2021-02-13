package com.fusionkoding.citizenshqapi.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import java.util.Map;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("#{${authorities:{'access':'scope','id':'cognito:groups'}}}")
    private Map<String, String> authorityUseTypes;

    @Value("${prefix:\"ROLE_\"}")
    private String prefix;

    @Value("${principal:\"sub\"}")
    private String principal;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and() // (1)
                .authorizeRequests().antMatchers("/actuator/health").permitAll().anyRequest().authenticated() // (2)
                .and().oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter()); // (3)
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowUrlEncodedPercent(true);
        return firewall;
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthoritiesConverter jwtAuthoritiesConverter = new JwtAuthoritiesConverter();
        jwtAuthoritiesConverter.setAuthorityUseTypes(authorityUseTypes);
        jwtAuthoritiesConverter.setAuthorityPrefix(prefix);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtAuthoritiesConverter);
        jwtAuthenticationConverter.setPrincipalClaimName(principal);
        return jwtAuthenticationConverter;
    }
}
