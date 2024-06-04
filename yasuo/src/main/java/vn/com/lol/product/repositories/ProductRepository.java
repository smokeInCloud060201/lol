package vn.com.lol.product.repositories;

import org.springframework.stereotype.Repository;
import vn.com.lol.product.entities.Product;
import vn.com.lol.repository.BaseRepository;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {
}
