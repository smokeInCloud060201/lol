package vn.com.lol.nautilus.commons.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.InMemoryOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import vn.com.lol.nautilus.commons.security.grantPassword.AuthorizationGrantTypePassword;
import vn.com.lol.nautilus.commons.security.grantPassword.GrantPasswordAuthenticationProvider;
import vn.com.lol.nautilus.commons.security.grantPassword.OAuth2GrantPasswordAuthenticationConverter;

import java.util.UUID;

@Configuration
public class AuthorizationServerConfiguration {
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationSecurityFilterChain(
        HttpSecurity http,
        GrantPasswordAuthenticationProvider grantPasswordAuthenticationProvider,
        DaoAuthenticationProvider daoAuthenticationProvider
    ) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        http
            .getConfigurer(OAuth2AuthorizationServerConfigurer.class)
            .tokenEndpoint(tokenEndpoint ->
                tokenEndpoint
                    .accessTokenRequestConverter(new OAuth2GrantPasswordAuthenticationConverter())
                    .authenticationProvider(grantPasswordAuthenticationProvider)
                    .authenticationProvider(daoAuthenticationProvider)
            );

        http
            .exceptionHandling(
                exceptions ->
                    exceptions.authenticationEntryPoint(
                        new LoginUrlAuthenticationEntryPoint("/login")
                    )
            );

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(
        PasswordEncoder passwordEncoder, UserDetailsService userDetailsService
    ) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }

    @Bean
    public OAuth2AuthorizationService authorizationService() {
        return new InMemoryOAuth2AuthorizationService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient demoClient = RegisteredClient.withId(UUID.randomUUID().toString())
            .clientName("Demo client")
            .clientId("demo-client")

            // {noop} means "no operation," i.e., a raw password without any encoding applied.
            .clientSecret("$2a$10$NyUJXgkdoztj7Leq9MOM8.1NGdIUJcx.kWzEP.97WCA7Uek0VCflC")

            .redirectUri("http://localhost:8080/auth")
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
            .authorizationGrantType(AuthorizationGrantTypePassword.GRANT_PASSWORD)
            .build();

        return new InMemoryRegisteredClientRepository(demoClient);
    }

}
