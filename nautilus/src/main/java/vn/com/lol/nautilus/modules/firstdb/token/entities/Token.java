package vn.com.lol.nautilus.modules.firstdb.token.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import vn.com.lol.entities.BaseEntity;
import vn.com.lol.nautilus.commons.constant.HibernateConstant;

import static vn.com.lol.constants.GlobalHibernateConstant.IS_NOT_DELETED;
import static vn.com.lol.nautilus.commons.constant.HibernateConstant.Table.SOFT_DELETE_TOKEN;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = HibernateConstant.Entity.TOKEN)
@Table(name = HibernateConstant.Table.TOKEN)
@SQLRestriction(IS_NOT_DELETED)
@SQLDelete(sql = SOFT_DELETE_TOKEN)
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
