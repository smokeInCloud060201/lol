package vn.com.lol.thresh.modules.blob.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.lol.entities.BaseEntity;
import vn.com.lol.thresh.modules.storage.entities.FileMetadata;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Blob extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "secret_key")
    private String secretKey;

    @Column(name = "public_key")
    private String publicKey;

    @OneToMany(mappedBy = "fileBlob")
    private List<FileMetadata> blobFiles;
}
