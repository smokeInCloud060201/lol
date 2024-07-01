package vn.com.lol.nautilus.commons.security.oauth2;


import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;
import vn.com.lol.nautilus.commons.security.CustomAuthenticationEntryPoint;
import vn.com.lol.nautilus.commons.security.oauth2.grantPassword.GrantPasswordAuthenticationProvider;
import vn.com.lol.nautilus.commons.security.oauth2.grantPassword.OAuth2GrantPasswordAuthenticationConverter;
import vn.com.lol.nautilus.commons.security.oauth2.refresh_token.RefreshTokenAuthenticationConverter;
import vn.com.lol.nautilus.commons.security.oauth2.refresh_token.RefreshTokenAuthenticationProvider;

@Configuration
public class AuthorizationServerConfiguration {

    private final CorsConfigurationSource corsConfigurationSource;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    private final RefreshTokenAuthenticationConverter refreshTokenAuthenticationConverter;
    private final OAuth2GrantPasswordAuthenticationConverter oAuth2GrantPasswordAuthenticationConverter;
    private final Oauth2ErrorAuthenticationExceptionHandler oAuth2ErrorHandle;

    private final RefreshTokenAuthenticationProvider refreshTokenAuthenticationProvider;

    private final GrantPasswordAuthenticationProvider grantPasswordAuthenticationProvider;

    private final DaoAuthenticationProvider daoAuthenticationProvider;

    public AuthorizationServerConfiguration(CorsConfigurationSource corsConfigurationSource, CustomAuthenticationEntryPoint authenticationEntryPoint,
                                            RefreshTokenAuthenticationConverter refreshTokenAuthenticationConverter,
                                            OAuth2GrantPasswordAuthenticationConverter oAuth2GrantPasswordAuthenticationConverter,
                                            Oauth2ErrorAuthenticationExceptionHandler oAuth2ErrorHandle,
                                            @Qualifier("refreshTokenAuthenticationProvider") @NonNull RefreshTokenAuthenticationProvider refreshTokenAuthenticationProvider,
                                            @Qualifier("grantPasswordAuthenticationProvider") @NonNull GrantPasswordAuthenticationProvider grantPasswordAuthenticationProvider,
                                            @Qualifier("basicAuthenticationProvider") @NonNull DaoAuthenticationProvider daoAuthenticationProvider) {
        this.corsConfigurationSource = corsConfigurationSource;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.refreshTokenAuthenticationConverter = refreshTokenAuthenticationConverter;
        this.oAuth2GrantPasswordAuthenticationConverter = oAuth2GrantPasswordAuthenticationConverter;
        this.oAuth2ErrorHandle = oAuth2ErrorHandle;
        this.refreshTokenAuthenticationProvider = refreshTokenAuthenticationProvider;
        this.grantPasswordAuthenticationProvider = grantPasswordAuthenticationProvider;
        this.daoAuthenticationProvider = daoAuthenticationProvider;
    }


    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        http
                .cors(source -> source.configurationSource(corsConfigurationSource))
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(authenticationEntryPoint))
                .getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .tokenEndpoint(tokenEndpoint ->
                        tokenEndpoint
                                .accessTokenRequestConverter(oAuth2GrantPasswordAuthenticationConverter)
                                .accessTokenRequestConverter(refreshTokenAuthenticationConverter)
                                .authenticationProvider(grantPasswordAuthenticationProvider)
                                .authenticationProvider(refreshTokenAuthenticationProvider)
                                .authenticationProvider(daoAuthenticationProvider)
                                .errorResponseHandler(oAuth2ErrorHandle)
                );
        return http.build();
    }

}
