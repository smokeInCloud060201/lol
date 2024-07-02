package vn.com.lol.nautilus.commons.security.oauth2.refresh_token;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import vn.com.lol.nautilus.commons.security.oauth2.Oauth2TokenService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static vn.com.lol.nautilus.commons.security.oauth2.Oauth2GrantType.REFRESH_TOKEN;

@Component
@RequiredArgsConstructor
public class RefreshTokenAuthenticationConverter implements AuthenticationConverter {

    private final Oauth2TokenService oauth2TokenService;


    @Nullable
    @Override
    public Authentication convert(HttpServletRequest request) {

        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);

        if (!REFRESH_TOKEN.getValue().equals(grantType)) {
            return null;
        }

        MultiValueMap<String, String> parameters = getParameters(request);

        // scope (OPTIONAL)
        String scope = parameters.getFirst(OAuth2ParameterNames.SCOPE);
        if (StringUtils.hasText(scope)
            && parameters.get(OAuth2ParameterNames.SCOPE).size() != 1
        ) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
        }
        // refreshToken (REQUIRED)
        String refreshToken = parameters.getFirst(OAuth2ParameterNames.REFRESH_TOKEN);

        if (StringUtils.hasText(refreshToken)
                && parameters.get(OAuth2ParameterNames.REFRESH_TOKEN).size() != 1
        ) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
        }

        if (oauth2TokenService.isTokenRefreshed(refreshToken)) {
            throw new OAuth2AuthenticationException(new OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST), "Token has already been refreshed.");
        }


        Set<String> requestedScopes = null;
        if (StringUtils.hasText(scope)) {
            requestedScopes = new HashSet<>(
                Arrays.asList(StringUtils.delimitedListToStringArray(scope, " ")));
        }

        Map<String, Object> additionalParameters = parameters.entrySet().stream()
            .filter(entry ->
                !OAuth2ParameterNames.GRANT_TYPE.equals(entry.getKey())
                    && !OAuth2ParameterNames.SCOPE.equals(entry.getKey())
                    && !OAuth2ParameterNames.REFRESH_TOKEN.equals(entry.getKey())
            )
            .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().get(0)));

        Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();

        return new RefreshTokenAuthentication(
            refreshToken, clientPrincipal, requestedScopes, additionalParameters
        );
    }

    private static MultiValueMap<String, String> getParameters(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>(parameterMap.size());
        parameterMap.forEach((key, values) -> {
            for (String value : values) {
                parameters.add(key, value);
            }
        });
        return parameters;
    }
}