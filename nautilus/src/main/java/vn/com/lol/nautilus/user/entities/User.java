package vn.com.lol.nautilus.user.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.com.lol.entities.BaseEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static vn.com.lol.nautilus.commons.constants.SecurityConstant.GrantAuthority.ROLE;
import static vn.com.lol.nautilus.commons.constants.SecurityConstant.GrantAuthority.SCOPE;


@Entity(name = "User")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity implements UserDetails, Serializable {

    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roleUsers;


    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
