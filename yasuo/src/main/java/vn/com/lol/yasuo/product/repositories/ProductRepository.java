package vn.com.lol.yasuo.product.repositories;

import org.springframework.stereotype.Repository;
import vn.com.lol.common.repository.BaseRepository;
import vn.com.lol.yasuo.product.entities.Product;


@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {
}
