package vn.com.lol.nautilus.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.com.lol.exceptions.ResourceExistsException;
import vn.com.lol.nautilus.auth.dtos.request.AuthenticationRequest;
import vn.com.lol.nautilus.auth.dtos.response.AuthenticationResponse;
import vn.com.lol.nautilus.auth.service.AuthService;
import vn.com.lol.nautilus.security.JwtService;
import vn.com.lol.nautilus.token.TokenRepository;
import vn.com.lol.nautilus.token.entities.Token;
import vn.com.lol.nautilus.user.entities.User;
import vn.com.lol.nautilus.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Value("${security.jwt.expiration}")
    private long tokenExpiration;
    @Value("${security.jwt.refresh-token.expiration}")
    private long refreshTokenExpiration;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws ResourceExistsException {

        User user = userRepository.findByUserName(authenticationRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Not found user"));

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(), authenticationRequest.getPassword()
        ));

        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        saveToken(token, refreshToken);

        return AuthenticationResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveToken(String accessToken, String refreshToken) throws ResourceExistsException {
        Token token = tokenRepository.findByToken(accessToken).orElse(null);

        if (token != null) {
            throw new ResourceExistsException("Token was exists");
        }
        token = new Token();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setInvoked(false);
        token.setTokenExpired(getExpirationTime(tokenExpiration));
        token.setRefreshTokenExpired(getExpirationTime(refreshTokenExpiration));

        tokenRepository.save(token);
    }

    private long getExpirationTime(long expirationTime) {
        return System.currentTimeMillis() + expirationTime;
    }
}
