package vn.com.lol.nautilus.commons.security.oauth2;

import org.springframework.security.oauth2.core.AuthorizationGrantType;

public class Oauth2GrantType {
    public static final AuthorizationGrantType GRANT_PASSWORD =
        new AuthorizationGrantType("grant_password");

    public static final AuthorizationGrantType REFRESH_TOKEN = AuthorizationGrantType.REFRESH_TOKEN;
}
