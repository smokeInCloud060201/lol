package vn.com.lol.product.entities;

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

import static vn.com.lol.constants.HibernateConstant.Entity.IMAGE_PRODUCT;
import static vn.com.lol.constants.HibernateConstant.IS_NOT_DELETED;
import static vn.com.lol.constants.HibernateConstant.Table.IMAGE_PRODUCT_TABLE;
import static vn.com.lol.constants.HibernateConstant.Table.SOFT_DELETE_IMAGE_PRODUCT;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = IMAGE_PRODUCT)
@Table(name = IMAGE_PRODUCT_TABLE)
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
