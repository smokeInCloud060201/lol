package vn.com.lol.yorick.user.repositories;

import org.springframework.stereotype.Repository;
import vn.com.lol.repository.BaseRepository;
import vn.com.lol.yorick.user.entities.Permission;

@Repository
public interface PermissionRepository extends BaseRepository<Permission, Long> {
}
