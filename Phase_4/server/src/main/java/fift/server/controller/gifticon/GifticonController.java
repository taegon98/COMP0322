package fift.server.controller.gifticon;

import fift.server.domain.product.Product;
import fift.server.service.gifticon.GifticonService;
import fift.server.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GifticonController {
    private final GifticonService gifticonService;
    private final ProductService productService;

    // Gifticon 선물하는 기능
    @PostMapping("/gifticon/gift")
    public String giftGifticonToUser(
            @RequestParam("userId") Long userId,
            @RequestParam("productId") Long productId
    ) {
        try {
            gifticonService.giftGifticonToUser(userId, productId);
            return "redirect:/success";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @GetMapping("/giftshop")
    public String giftshop(Model model) {
        List<Product> top10Products = productService.getTOP8Products();
        model.addAttribute("products", top10Products);

        return "gifticon/gifticonpage";
    }

    @GetMapping("/giftshop/{id}")
    public String present(@PathVariable Long id, Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "gifticon/productpage2";
    }
}