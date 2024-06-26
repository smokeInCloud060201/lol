package vn.com.lol.yorick.modules.user.repositories;

import org.springframework.stereotype.Repository;
import vn.com.lol.common.repository.BaseRepository;
import vn.com.lol.yorick.modules.user.entities.Permission;

@Repository
public interface PermissionRepository extends BaseRepository<Permission, Long> {
}
