package vn.com.lol.yasuo.category.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import vn.com.lol.entities.BaseEntity;
import vn.com.lol.yasuo.commons.constant.GlobalHibernateConstant;
import vn.com.lol.yasuo.product.entities.Product;

import java.util.List;

import static vn.com.lol.constants.GlobalHibernateConstant.IS_NOT_DELETED;
import static vn.com.lol.yasuo.commons.constant.GlobalHibernateConstant.Table.SOFT_DELETE_CATEGORY;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = GlobalHibernateConstant.Entity.CATEGORY)
@Table(name = GlobalHibernateConstant.Table.CATEGORY)
@SQLRestriction(IS_NOT_DELETED)
@SQLDelete(sql = SOFT_DELETE_CATEGORY)
public class Category extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "productCategory")
    private List<Product> products;
}
