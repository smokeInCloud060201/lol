package vn.com.lol.yorick.modules.user.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import vn.com.lol.common.entities.BaseEntity;
import vn.com.lol.yorick.common.constant.HibernateConstant;
import vn.com.lol.yorick.modules.user.enums.UserRank;

import java.util.List;

import static vn.com.lol.common.constants.GlobalHibernateConstant.IS_NOT_DELETED;
import static vn.com.lol.yorick.common.constant.HibernateConstant.Table.SOFT_DELETE_USER;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = HibernateConstant.Entity.USER)
@Table(name = HibernateConstant.Table.USER)
@SQLRestriction(IS_NOT_DELETED)
@SQLDelete(sql = SOFT_DELETE_USER)
public class User extends BaseEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "mobile_no")
    private String mobileNo;

    private UserRank userRank;

    @Column(name = "is_verified_email")
    private boolean isVerifiedEmail;

    @Column(name = "is_verified_mobile_no")
    private boolean isVerifiedMobileNo;

    @Column(name = "is_account_non_expired")
    private boolean isAccountNonExpired;

    @Column(name = "is_account_non_locked")
    private boolean isAccountNonLocked;

    @Column(name = "is_credential_non_expired")
    private boolean isCredentialsNonExpired;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> userRoles;
}
