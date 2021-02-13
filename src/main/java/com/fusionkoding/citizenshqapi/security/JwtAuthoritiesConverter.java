package com.fusionkoding.citizenshqapi.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.log.LogMessage;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
public final class JwtAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter;

    private static final String TOKEN_USE_CLAIM_ID = "token_use";

    @Getter
    @Setter
    private Map<String, String> authorityUseTypes;

    public JwtAuthoritiesConverter() {
        jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authorityUseTypes = new HashMap<>();
    }

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        String authoritiesClaim = authorityUseTypes.get(jwt.getClaim(TOKEN_USE_CLAIM_ID));
        if(authoritiesClaim==null){
            log.error(TOKEN_USE_CLAIM_ID + " not found in jwt token");
            return null;
        }

        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(authoritiesClaim);
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        return jwtGrantedAuthoritiesConverter.convert(jwt);
    }

    public void setAuthorityPrefix(String prefix) {
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(prefix);
    }

}