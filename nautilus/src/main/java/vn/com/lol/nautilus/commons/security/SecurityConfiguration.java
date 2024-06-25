//package vn.com.lol.nautilus.commons.security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//@RequiredArgsConstructor
//public class SecurityConfiguration {
//
//    private final JwtFilter jwtFilter;
//    private final LogoutHandler logoutHandler;
//    private final AuthenticationProvider authenticationProvider;
//    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
//    private final CorsConfigurationSource corsConfigurationSource;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .cors(config -> config.configurationSource(corsConfigurationSource))
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(requests ->
//                        requests.requestMatchers("/api/v1/auth/**", "/_/**").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//                .logout(logout -> logout.logoutUrl("/logout")
//                        .addLogoutHandler(logoutHandler))
//                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(authenticationEntryPoint))
//                .build()
//                ;
//
//    }
//
//}
