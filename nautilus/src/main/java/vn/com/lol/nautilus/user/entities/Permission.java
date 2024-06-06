package vn.com.lol.nautilus.user.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.lol.entities.BaseEntity;

import java.util.List;

@Table(name = "permission")
@Entity(name = "Permission")
@Getter
@Setter
@NoArgsConstructor
public class Permission extends BaseEntity {
    private String name;

    @ManyToMany
    private List<Role> rolePermissions;
}
