package vn.com.lol.yasuo.tempt.repository;

import org.springframework.stereotype.Repository;
import vn.com.lol.repository.BaseRepository;
import vn.com.lol.yasuo.tempt.entities.Temp;


@Repository
public interface TempRepository extends BaseRepository<Temp, Long> {
}
