package vn.com.lol.nautilus.commons.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import vn.com.lol.nautilus.commons.security.OAuth2ClientProperties;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class OAuth2ClientConfig {

    private final OAuth2ClientProperties clientProperties;

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        List<RegisteredClient> registeredClients = clientProperties.getClients().stream()
                .map(client -> RegisteredClient.withId(UUID.randomUUID().toString())
                        .clientId(client.getClientId())
                        .clientSecret(client.getClientSecret())
                        .clientName(client.getClientName())
                        .redirectUri(client.getRedirectUri())
                        .clientAuthenticationMethod(new ClientAuthenticationMethod(client.getClientAuthenticationMethod()))
                        .authorizationGrantTypes(grantTypes -> client.getAuthorizationGrantTypes().forEach(grantType ->
                                grantTypes.add(new AuthorizationGrantType(grantType))
                        ))
                        .tokenSettings(TokenSettings.builder()
                                .accessTokenTimeToLive(Duration.ofSeconds(client.getTokenSettings().getAccessTokenTimeToLive()))
                                .build())
                        .build()
                )
                .toList();

        return new InMemoryRegisteredClientRepository(registeredClients);
    }
}
