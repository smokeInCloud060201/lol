package vn.com.lol.nautilus.commons.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfigurationSource;
import vn.com.lol.nautilus.commons.security.grantPassword.GrantPasswordAuthenticationProvider;
import vn.com.lol.nautilus.commons.security.grantPassword.OAuth2GrantPasswordAuthenticationConverter;

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
            DaoAuthenticationProvider daoAuthenticationProvider
    ) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        http
                .cors(source -> source.configurationSource(corsConfigurationSource))
                .getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .tokenEndpoint(tokenEndpoint ->
                        tokenEndpoint
                                .accessTokenRequestConverter(new OAuth2GrantPasswordAuthenticationConverter())
                                .authenticationProvider(grantPasswordAuthenticationProvider)
                                .authenticationProvider(daoAuthenticationProvider)
                );

        http.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(authenticationEntryPoint));

        return http.build();
    }

}
