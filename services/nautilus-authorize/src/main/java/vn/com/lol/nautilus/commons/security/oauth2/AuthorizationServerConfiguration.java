package vn.com.lol.nautilus.commons.security.oauth2;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2RefreshTokenAuthenticationProvider;
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
@RequiredArgsConstructor
public class AuthorizationServerConfiguration {

    private final CorsConfigurationSource corsConfigurationSource;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;



    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationSecurityFilterChain(
            HttpSecurity http,
            GrantPasswordAuthenticationProvider grantPasswordAuthenticationProvider,
            RefreshTokenAuthenticationProvider refreshTokenAuthenticationProvider,
            DaoAuthenticationProvider daoAuthenticationProvider
    ) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        http
                .cors(source -> source.configurationSource(corsConfigurationSource))
                .getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .tokenEndpoint(tokenEndpoint ->
                        tokenEndpoint
                                .accessTokenRequestConverter(new OAuth2GrantPasswordAuthenticationConverter())
                                .accessTokenRequestConverter(new RefreshTokenAuthenticationConverter())
                                .authenticationProvider(grantPasswordAuthenticationProvider)
                                .authenticationProvider(refreshTokenAuthenticationProvider)
                                .authenticationProvider(daoAuthenticationProvider)
                );

        http.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(authenticationEntryPoint));
        return http.build();
    }

}
