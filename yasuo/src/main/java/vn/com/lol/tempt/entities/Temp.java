package vn.com.lol.tempt.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import vn.com.lol.entities.BaseEntity;

import static vn.com.lol.constants.HibernateConstant.Entity.PRODUCT;
import static vn.com.lol.constants.HibernateConstant.IS_NOT_DELETED;
import static vn.com.lol.constants.HibernateConstant.Table.PRODUCT_TABLE;
import static vn.com.lol.constants.HibernateConstant.Table.SOFT_DELETE_PRODUCT;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = PRODUCT + "temp")
@Table(name = PRODUCT_TABLE + "temp")
@SQLRestriction(IS_NOT_DELETED)
@SQLDelete(sql = SOFT_DELETE_PRODUCT)
public class Temp extends BaseEntity {
}
