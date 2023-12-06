package fift.server.controller.cart;


import fift.server.domain.customer.Customer;
import fift.server.domain.product.Product;
import fift.server.dto.cart.CartDto;
import fift.server.service.cart.CartService;
import fift.server.service.customer.CustomerService;
import fift.server.service.product.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CustomerService customerService;
    private final ProductService productService;

    private final CartService cartService;

    // 장바구니 담기

    @PostMapping("/{id}/cart")
    public String cart_Item(@PathVariable("id") Long id,
                            @RequestParam("quantity") int quantity,
                            HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        Product product = productService.getProduct(id);
        cartService.addCart(customer, product, quantity);
        return "redirect:/";
    }




}
