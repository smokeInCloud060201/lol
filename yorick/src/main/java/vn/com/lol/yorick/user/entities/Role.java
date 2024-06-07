package vn.com.lol.yorick.user.entities;

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
import vn.com.lol.entities.BaseEntity;
import vn.com.lol.yorick.common.constant.HibernateConstant;

import java.util.List;

import static vn.com.lol.constants.GlobalHibernateConstant.IS_NOT_DELETED;
import static vn.com.lol.yorick.common.constant.HibernateConstant.Table.SOFT_DELETE_ROLE;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = HibernateConstant.Entity.ROLE)
@Table(name = HibernateConstant.Table.ROLE)
@SQLRestriction(IS_NOT_DELETED)
@SQLDelete(sql = SOFT_DELETE_ROLE)
public class Role extends BaseEntity {
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissionRoles;

    @ManyToMany
    @JoinTable(
            name = "role_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<User> userRoles;
}
