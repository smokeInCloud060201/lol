package vn.com.lol.nautilus.token.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.lol.entities.BaseEntity;

@Table(name = "token")
@Entity(name = "Token")
@Getter
@Setter
@NoArgsConstructor
public class Token extends BaseEntity {
    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "token_expired")
    private Long tokenExpired;

    @Column(name = "refresh_token_expired")
    private Long refreshTokenExpired;

    @Column(name = "invoked")
    private boolean invoked;
}
