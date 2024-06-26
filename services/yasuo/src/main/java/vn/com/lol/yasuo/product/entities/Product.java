package vn.com.lol.yasuo.product.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import vn.com.lol.common.entities.BaseEntity;
import vn.com.lol.yasuo.category.entities.Category;
import vn.com.lol.yasuo.commons.constant.HibernateConstant;

import java.util.List;

import static vn.com.lol.common.constants.GlobalHibernateConstant.IS_NOT_DELETED;
import static vn.com.lol.yasuo.commons.constant.HibernateConstant.Table.SOFT_DELETE_PRODUCT;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = HibernateConstant.Entity.PRODUCT)
@Table(name = HibernateConstant.Table.PRODUCT)
@SQLRestriction(IS_NOT_DELETED)
@SQLDelete(sql = SOFT_DELETE_PRODUCT)
public class Product extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "sku_code")
    private String skuCode;

    @Column(name = "bar_code")
    private String barCode;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category productCategory;

    @OneToMany(mappedBy = "productImage")
    private List<ImageProduct> imageProductList;
}
