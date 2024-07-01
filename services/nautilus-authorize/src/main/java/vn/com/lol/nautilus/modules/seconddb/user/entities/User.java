package vn.com.lol.nautilus.modules.seconddb.user.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.com.lol.common.entities.BaseEntity;
import vn.com.lol.nautilus.commons.constant.HibernateConstant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static vn.com.lol.common.constants.GlobalHibernateConstant.IS_NOT_DELETED;
import static vn.com.lol.nautilus.commons.constant.HibernateConstant.Table.SOFT_DELETE_USER;
import static vn.com.lol.nautilus.commons.constant.SecurityConstant.GrantAuthority.ROLE;
import static vn.com.lol.nautilus.commons.constant.SecurityConstant.GrantAuthority.SCOPE;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = HibernateConstant.Entity.USER)
@Table(name = HibernateConstant.Table.USER)
@SQLRestriction(IS_NOT_DELETED)
@SQLDelete(sql = SOFT_DELETE_USER)
public class User extends BaseEntity implements UserDetails, Serializable {

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

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

    @ManyToMany(mappedBy = "userRoles", fetch = FetchType.EAGER)
    private List<Role> roleUsers;

    transient List<SimpleGrantedAuthority> authorities = new ArrayList<>();


    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
        if (roleUsers == null) {
            return simpleGrantedAuthorityList;
        }
        roleUsers.forEach(role -> {
            role.getPermissionRoles()
                    .forEach(permission -> simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(SCOPE + permission.getName())));
            simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(ROLE + role.getName()));
        });

        return simpleGrantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
