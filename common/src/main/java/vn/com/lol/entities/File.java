package vn.com.lol.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import vn.com.lol.constants.GlobalHibernateConstant;
import vn.com.lol.enums.FileExtension;

import static vn.com.lol.constants.GlobalHibernateConstant.IS_NOT_DELETED;
import static vn.com.lol.constants.GlobalHibernateConstant.Table.SOFT_DELETE_FILE;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = GlobalHibernateConstant.Entity.FILE)
@Table(name = GlobalHibernateConstant.Table.FILE)
@SQLRestriction(IS_NOT_DELETED)
@SQLDelete(sql = SOFT_DELETE_FILE)
public class File extends BaseEntity {
    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_id")
    private String fileId;

    @Column(name = "file_type")
    private FileExtension fileType;
}
