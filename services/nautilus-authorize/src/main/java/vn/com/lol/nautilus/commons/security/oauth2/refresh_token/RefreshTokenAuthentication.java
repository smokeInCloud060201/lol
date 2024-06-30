package vn.com.lol.nautilus.commons.security.oauth2.refresh_token;


import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;
import org.springframework.util.Assert;
import vn.com.lol.nautilus.commons.security.oauth2.Oauth2GrantType;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Getter
@Setter
public class RefreshTokenAuthentication extends OAuth2AuthorizationGrantAuthenticationToken {

    private static final long serialVersionUID = 1L;
    private final String refreshToken;
    private final Set<String> scopes;

    public RefreshTokenAuthentication(String refreshToken, Authentication clientPrincipal, @Nullable Set<String> scopes, @Nullable Map<String, Object> additionalParameters) {
        super(AuthorizationGrantType.REFRESH_TOKEN, clientPrincipal, additionalParameters);
        Assert.hasText(refreshToken, "refreshToken cannot be empty");
        this.refreshToken = refreshToken;
        //Unchecked
        this.scopes = Collections.unmodifiableSet((Set<String>)(scopes != null ? new HashSet<String>(scopes) : Collections.emptySet()));
    }
}