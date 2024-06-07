package vn.com.lol.yasuo.category.repository;

import org.springframework.stereotype.Repository;
import vn.com.lol.repository.BaseRepository;
import vn.com.lol.yasuo.category.entities.Category;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {
}
