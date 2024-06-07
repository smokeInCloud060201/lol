package vn.com.lol.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@MappedSuperclass
@Getter
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(generator = "customId")
    @GenericGenerator(name = "customId", type = vn.com.lol.utils.CustomIdGenerator.class)
    @Column(name = "id")
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private ZonedDateTime createdTime;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedTime;

    @Column(name = "is_deleted")
    private boolean isDeleted;
}
