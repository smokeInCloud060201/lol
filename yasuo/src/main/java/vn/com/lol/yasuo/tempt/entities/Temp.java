package vn.com.lol.yasuo.tempt.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import vn.com.lol.common.entities.BaseEntity;


import static vn.com.lol.common.constants.GlobalHibernateConstant.IS_NOT_DELETED;
import static vn.com.lol.yasuo.commons.constant.HibernateConstant.Table.PRODUCT;
import static vn.com.lol.yasuo.commons.constant.HibernateConstant.Table.SOFT_DELETE_PRODUCT;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = PRODUCT + "temp")
@Table(name = PRODUCT + "temp")
@SQLRestriction(IS_NOT_DELETED)
@SQLDelete(sql = SOFT_DELETE_PRODUCT)
public class Temp extends BaseEntity {
}
