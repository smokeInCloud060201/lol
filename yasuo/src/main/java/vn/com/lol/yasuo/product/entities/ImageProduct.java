package vn.com.lol.yasuo.product.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import vn.com.lol.entities.BaseEntity;
import vn.com.lol.entities.File;
import vn.com.lol.yasuo.commons.constant.GlobalHibernateConstant;

import static vn.com.lol.constants.GlobalHibernateConstant.IS_NOT_DELETED;
import static vn.com.lol.yasuo.commons.constant.GlobalHibernateConstant.Table.SOFT_DELETE_IMAGE_PRODUCT;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = GlobalHibernateConstant.Entity.IMAGE_PRODUCT)
@Table(name = GlobalHibernateConstant.Table.IMAGE_PRODUCT)
@SQLRestriction(IS_NOT_DELETED)
@SQLDelete(sql = SOFT_DELETE_IMAGE_PRODUCT)
public class ImageProduct extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "file_id")
    private File imageProductFile;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productImage;
}
