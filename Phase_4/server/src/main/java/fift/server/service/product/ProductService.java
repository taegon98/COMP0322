package fift.server.service.product;

import fift.server.domain.product.Product;
import fift.server.repository.product.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long product_Id) {
        return productRepository.findById(product_Id)
                .orElseThrow(()->new RuntimeException("Product Not Found"));
    }

    public void deleteProduct(Long product_Id) {
        Product productToDelete = productRepository.findById(product_Id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));
        productRepository.delete(productToDelete);
    }

    public List<Product> searchProduct(String keyword) {
        return productRepository.findByNameContaining(keyword);
    }

}
