package vn.com.lol.nautilus.commons.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import vn.com.lol.nautilus.modules.seconddb.user.entities.User;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static vn.com.lol.nautilus.commons.constant.SecurityConstant.JWT.IP_ADDRESS;
import static vn.com.lol.nautilus.commons.constant.SecurityConstant.JWT.IS_ACCOUNT_NON_EXPIRED;
import static vn.com.lol.nautilus.commons.constant.SecurityConstant.JWT.IS_ACCOUNT_NON_LOCKED;
import static vn.com.lol.nautilus.commons.constant.SecurityConstant.JWT.IS_CREDENTIAL_NON_EXPIRED;
import static vn.com.lol.nautilus.commons.constant.SecurityConstant.JWT.IS_EMAIL_VERIFIED;
import static vn.com.lol.nautilus.commons.constant.SecurityConstant.JWT.IS_ENABLE;
import static vn.com.lol.nautilus.commons.constant.SecurityConstant.JWT.IS_MOBILE_NO_VERIFIED;

@Service
@Slf4j
public class JwtService {
    @Value("${security.jwt.secret-key}")
    private String secretKey;
    @Value("${security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails, HttpServletRequest request) {
        Map<String, Object> extractClaims = new HashMap<>();
        extractClaims.put(IP_ADDRESS, request.getRemoteAddr());
        return generateToken(extractClaims, userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        User user = (User) userDetails;
        extraClaims.put(IS_ENABLE, user.isEnabled());
        extraClaims.put(IS_CREDENTIAL_NON_EXPIRED, user.isEnabled());
        extraClaims.put(IS_ACCOUNT_NON_LOCKED, user.isAccountNonLocked());
        extraClaims.put(IS_ACCOUNT_NON_EXPIRED, user.isAccountNonExpired());
        extraClaims.put(IS_EMAIL_VERIFIED, user.isVerifiedEmail());
        extraClaims.put(IS_MOBILE_NO_VERIFIED, user.isVerifiedMobileNo());

        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public String generateRefreshToken(
            UserDetails userDetails
    ) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiration);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), Jwts.SIG.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
