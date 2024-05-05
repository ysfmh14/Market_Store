package com.example.market_store.conf;

import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final RestTemplate restTemplate;
    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;
    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter =
            new JwtGrantedAuthoritiesConverter();

    @Value("${jwt.auth.converter.principle-attribute}")
    private String principleAttribute;
    @Value("${jwt.auth.converter.resource-id}")
    private String resourceId;

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        boolean tokenValid = validateToken(jwt.getTokenValue());
        if (!tokenValid) {
            throw new ServiceException("Token is not valid");
        }
        Collection<GrantedAuthority> authorities = Stream.concat(
                jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                extractResourceRoles(jwt).stream()
        ).collect(Collectors.toSet());

        return new JwtAuthenticationToken(
                jwt,
                authorities,
                getPrincipleClaimName(jwt)
        );
    }

    private String getPrincipleClaimName(Jwt jwt) {
        String claimName = JwtClaimNames.SUB;
        if (principleAttribute != null) {
            claimName = principleAttribute;
        }
        return jwt.getClaim(claimName);
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        Map<String, Object> resourceAccess;
        Map<String, Object> resource;
        Collection<String> resourceRoles;
        if (jwt.getClaim("resource_access") == null) {
            return Set.of();
        }
        resourceAccess = jwt.getClaim("resource_access");

        if (resourceAccess.get(resourceId) == null) {
            return Set.of();
        }
        resource = (Map<String, Object>) resourceAccess.get(resourceId);

        resourceRoles = (Collection<String>) resource.get("roles");
        return resourceRoles
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }
    public boolean validateToken(String token) {
        // Prepare request headers with content type
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Prepare request body with client credentials and token
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("client_id", clientId);
        requestBody.add("client_secret", clientSecret);
        requestBody.add("token", token);

        // Send token introspection request to Keycloak's endpoint
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.exchange(
                "http://localhost:8080/realms/master/protocol/openid-connect/token/introspect",
                HttpMethod.POST,
                requestEntity,
                Map.class);
        // Check if token is active based on Keycloak's response
        return (boolean) response.getBody().get("active");
    }
}