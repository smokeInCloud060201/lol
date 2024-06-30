package vn.com.lol.nautilus.commons.security.oauth2;

import jakarta.persistence.SecondaryTable;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class OAuth2ClientConfig {

    private final OAuth2ClientProperties clientProperties;

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        List<RegisteredClient> registeredClients = clientProperties.getClients().stream()
                .map(client -> RegisteredClient.withId(client.getRegisterClientId())
                        .clientId(client.getClientId())
                        .clientSecret(client.getClientSecret())
                        .clientName(client.getClientName())
                        .redirectUri(client.getRedirectUri())
                        .clientAuthenticationMethod(new ClientAuthenticationMethod(client.getClientAuthenticationMethod()))
                        .authorizationGrantTypes(grantTypes -> client.getAuthorizationGrantTypes().forEach(grantType ->
                                grantTypes.add(new AuthorizationGrantType(grantType))
                        ))
                        .scopes(strings -> strings.addAll(client.getScopes()))
                        .tokenSettings(TokenSettings.builder()
                                .accessTokenTimeToLive(Duration.ofSeconds(client.getTokenSettings().getAccessTokenTimeToLive()))
                                .refreshTokenTimeToLive(Duration.ofSeconds(client.getTokenSettings().getRefreshTokenTimeToLive()))
                                .reuseRefreshTokens(client.getTokenSettings().isReuseRefreshToken())
                                .build())
                        .build()
                )
                .toList();

        return new InMemoryRegisteredClientRepository(registeredClients);
    }
}
