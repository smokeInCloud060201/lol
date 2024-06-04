package vn.com.lol.category.repository;

import org.springframework.stereotype.Repository;
import vn.com.lol.category.entities.Category;
import vn.com.lol.repository.BaseRepository;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {
}
