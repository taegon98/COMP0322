package fift.server.repository.product;

import fift.server.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByProductNameContaining(String product_Name);
    Product findByProductId(Long product_Id);
    List<Product> findTop10ByOrderByProductIdDesc();
}
