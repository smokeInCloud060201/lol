package vn.com.lol.nautilus.commons.security.oauth2;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@ConfigurationProperties(prefix = "oauth2")
public class OAuth2ClientProperties {
    private List<Client> clients;

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Data
    public static class Client {
        private String clientId;
        private String registerClientId;
        private String clientSecret;
        private String clientName;
        private String redirectUri;
        private String clientAuthenticationMethod;
        private List<String> authorizationGrantTypes;
        private TokenSettings tokenSettings;
        private Set<String> scopes;

        @Data
        public static class TokenSettings {
            private long accessTokenTimeToLive;
            private long refreshTokenTimeToLive;
            private boolean reuseRefreshToken;

        }
    }
}
