package vn.com.lol.yorick.user.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import vn.com.lol.entities.BaseEntity;
import vn.com.lol.yorick.common.constant.HibernateConstant;

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
}
