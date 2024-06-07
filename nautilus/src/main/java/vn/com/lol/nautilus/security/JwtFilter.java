package vn.com.lol.nautilus.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import vn.com.lol.nautilus.token.TokenRepository;
import vn.com.lol.nautilus.token.entities.Token;
import vn.com.lol.nautilus.user.entities.User;
import vn.com.lol.nautilus.user.repository.UserRepository;

import java.io.IOException;

import static vn.com.lol.nautilus.commons.constant.SecurityConstant.Header.AUTHORIZATION;
import static vn.com.lol.nautilus.commons.constant.SecurityConstant.Header.BEARER_TOKEN_TYPE;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (null == authorizationHeader || !authorizationHeader.startsWith(BEARER_TOKEN_TYPE)) {
            filterChain.doFilter(request, response);
            return;
        }
        String accessToken = authorizationHeader.substring(BEARER_TOKEN_TYPE.length());

        if (StringUtils.isNotBlank(accessToken)) {
            String userName = jwtService.extractUsername(accessToken);
            User user = userRepository.findByUserName(userName).orElse(null);
            Token token = tokenRepository.findByToken(accessToken).orElse(null);
            if (null != user && null != token && isValidToken(token) && jwtService.isTokenValid(accessToken, user)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean isValidToken(Token token) {
        return System.currentTimeMillis() < token.getTokenExpired()
                && !token.isInvoked();
    }
}
