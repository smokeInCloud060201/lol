package vn.com.lol.yorick.modules.user.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import vn.com.lol.entities.BaseEntity;
import vn.com.lol.yorick.common.constant.HibernateConstant;

import java.util.List;

import static vn.com.lol.constants.GlobalHibernateConstant.IS_NOT_DELETED;
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

    @Column(name = "is_verified_email")
    private boolean isVerifiedEmail;

    @Column(name = "is_verified_mobile_no")
    private boolean isVerifiedMobileNo;

    @ManyToMany(mappedBy = "userRoles", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Role> roleUsers;
}
