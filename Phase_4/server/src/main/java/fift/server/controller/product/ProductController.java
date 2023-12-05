package fift.server.controller.product;


import org.springframework.ui.Model;
import fift.server.domain.product.Product;
import fift.server.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String getAllProduct(Model model) {
        List<Product> top10Products = productService.getTOP10Products();
        model.addAttribute("products", top10Products);
        return "yourViewName";
    }

}
