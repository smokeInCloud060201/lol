package vn.com.lol.tempt.repository;

import org.springframework.stereotype.Repository;
import vn.com.lol.tempt.entities.Temp;
import vn.com.lol.repository.BaseRepository;

@Repository
public interface TempRepository extends BaseRepository<Temp, Long> {
}
