package fift.server.controller.product;


import jakarta.servlet.http.HttpSession;
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
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "product/productpage";
    }
}
