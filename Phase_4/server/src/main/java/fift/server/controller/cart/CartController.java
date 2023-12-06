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

    private final CartService cartService;

    private final ProductService productService;

    // 장바구니 담기

    @PostMapping("/{id}/cart")
    public String cartItem(@PathVariable("id") Long id, @RequestBody CartDto cartDto) {
        System.out.println(1);
        Customer customer = customerService.getCustomer(cartDto.getId());
        Product product = productService.getProduct(id);
        cartService.addCart(customer, product, cartDto.getCount());
        return "redirect:/";
    }
}
