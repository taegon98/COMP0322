package fift.server.controller.product;


import org.springframework.ui.Model;
import fift.server.domain.product.Product;
import fift.server.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public String getAllProduct(Model model) {
        List<Product> top10Products = productService.getTOP8Products();
        model.addAttribute("products", top10Products);
        return "index";
    }

    @GetMapping("/{id}")
    public String getProductDetails(@PathVariable Long id, Model model) {
        // 여기서 id를 사용하여 특정 상품의 상세 정보를 가져오는 로직을 작성
        Product product = productService.getProduct(id);
        // 상세 정보를 모델에 추가
        model.addAttribute("product", product);
        // 뷰의 이름을 반환
        return "product/productpage"; // 실제 뷰의 이름은 "productDetails.html"이어야 함
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam("keyword") String keyword, Model model) {
        List<Product> searchResults = productService.searchProduct(keyword);
        model.addAttribute("products", searchResults);
        return "yourViewName";
    }
}
