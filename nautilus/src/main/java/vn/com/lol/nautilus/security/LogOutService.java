package vn.com.lol.nautilus.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import vn.com.lol.nautilus.commons.constant.SecurityConstant;
import vn.com.lol.nautilus.token.TokenRepository;
import vn.com.lol.nautilus.token.entities.Token;


@Service
@RequiredArgsConstructor
public class LogOutService implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String jwtToken = request.getHeader(SecurityConstant.Header.AUTHORIZATION);

        if (StringUtils.isNotBlank(jwtToken) && jwtToken.startsWith(SecurityConstant.Header.BEARER_TOKEN_TYPE)) {
            Token token = tokenRepository.findByToken(jwtToken).orElse(null);

            if (null != token) {
                token.setInvoked(true);
                tokenRepository.save(token);
            }
        }

        SecurityContextHolder.clearContext();
    }
}
