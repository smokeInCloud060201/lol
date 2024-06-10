package vn.com.lol.repository;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface ReadOnlyBaseRepository<T, I> extends BaseRepository<T, I> {
    Optional<T> findById(I id);
    List<T> findAll();
}
