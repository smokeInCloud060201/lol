package vn.com.lol.nautilus.modules.auth.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import vn.com.lol.common.exceptions.ResourceNotFoundException;
import vn.com.lol.nautilus.commons.security.basic.JwtUtil;
import vn.com.lol.nautilus.modules.auth.dtos.request.AuthenticationRequest;
import vn.com.lol.nautilus.modules.auth.dtos.response.AuthenticationResponse;
import vn.com.lol.nautilus.modules.auth.service.AuthService;
import vn.com.lol.nautilus.modules.firstdb.token.TokenRepository;
import vn.com.lol.nautilus.modules.firstdb.token.entities.Token;
import vn.com.lol.nautilus.modules.firstdb.token.enums.TokenType;
import vn.com.lol.nautilus.modules.seconddb.user.entities.User;
import vn.com.lol.nautilus.modules.seconddb.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;


    public AuthenticationResponse authenticate(AuthenticationRequest request, HttpServletRequest servletRequest) throws ResourceNotFoundException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                request.getPassword()));

        User user = userRepository.findByUserName(request.getEmail()).orElseThrow(() -> new
                ResourceNotFoundException("Not found user"));

        String accessToken = jwtUtil.generateToken(user, servletRequest);
        String refreshToken = jwtUtil.generateRefreshToken(user, servletRequest);

        Token token = new Token();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setUsername(user.getUsername());
        token.setTokenType(TokenType.BASIC);
        tokenRepository.save(token);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
