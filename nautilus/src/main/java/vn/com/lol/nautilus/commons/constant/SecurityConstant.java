package vn.com.lol.nautilus.commons.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityConstant {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public class Header {
        public static final String AUTHORIZATION = "Authorization";
        public static final String BEARER_TOKEN_TYPE = "Bearer ";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public class GrantAuthority {
        public static final String SCOPE = "SCOPE_";
        public static final String ROLE = "ROLE_";
    }
}
