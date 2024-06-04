package vn.com.lol.entities;

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
    private Long id;

    @CreationTimestamp
    private ZonedDateTime createdTime;

    @UpdateTimestamp
    private ZonedDateTime updatedTime;

    private boolean isDeleted;
}
