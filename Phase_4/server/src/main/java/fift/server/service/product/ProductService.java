package fift.server.service.product;

import fift.server.domain.products.Products;
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

    /**
     * 상품 전체 조회
     * @return
     */
    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * 상품 상세 조회
     * @param product_Id
     * @return
     */
    public Products getProduct(Long product_Id) {
        return productRepository.findById(product_Id)
                .orElseThrow(()->new RuntimeException("Product Not Found"));
    }

    /**
     * 상품 삭제
     * @param product_Id
     */
    public void deleteProduct(Long product_Id) {
        Products productToDelete = productRepository.findById(product_Id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));
        productRepository.delete(productToDelete);
    }

    /**
     * 상품 검가
     * @param keyword
     * @return
     */
    public List<Products> searchProduct(String keyword) {
        return productRepository.findByProductNameContaining(keyword);
    }

}
