package fift.server.repository.product;

import fift.server.domain.products.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products,Long> {
    List<Products> findByProductNameContaining(String product_Name);
    Products findByProductId(Long product_Id);
}
