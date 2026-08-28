package com.example.security.security.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;


import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

class SecurityConfigTest {

    private final SecurityConfig securityConfig = new SecurityConfig();
    private JWKSource<SecurityContext> jwkSource;
    private JwtDecoder jwtDecoder;

    @BeforeEach
    void setUp() {
        jwkSource = securityConfig.jwkSource();
        jwtDecoder = securityConfig.jwtDecoder(jwkSource);
    }

    @Test
    void generatesAndDecodesSignedJwt() {
        JwtEncoder jwtEncoder = new NimbusJwtEncoder(jwkSource);
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject("user")
                .claim("scope", "openid users.read")
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        var decodedToken = jwtDecoder.decode(token);

        assertThat(token).isNotBlank();
        assertThat(decodedToken.getSubject()).isEqualTo("user");
        assertThat(decodedToken.getClaimAsString("scope")).isEqualTo("openid users.read");
    }

    @Test
    void jwtTokenCustomizerAddsUserRoles() {
        OAuth2TokenCustomizer<JwtEncodingContext> customizer = securityConfig.jwtTokenCustomizer();
        JwtEncodingContext context = mock(JwtEncodingContext.class);
        JwtClaimsSet.Builder claims = JwtClaimsSet.builder();
        var authentication = new UsernamePasswordAuthenticationToken(
                "user",
                null,
                List.of(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("SCOPE_users.read")));

        when(context.getPrincipal()).thenReturn(authentication);
        when(context.getClaims()).thenReturn(claims);

        customizer.customize(context);

        assertThat(claims.build().getClaimAsStringList("roles"))
                .containsExactly("ROLE_USER", "SCOPE_users.read");
    }

    @Test
    void registeredClientContainsExpectedScopes() {
        RegisteredClientRepository repository = securityConfig.registeredClientRepository();
        RegisteredClient client = repository.findByClientId("oidc-client");

        assertThat(client).isNotNull();
        assertThat(client.getScopes()).contains("openid", "profile", "users.read", "users.write");
    }
}