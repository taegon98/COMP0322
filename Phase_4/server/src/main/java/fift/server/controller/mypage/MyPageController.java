package fift.server.controller.mypage;

import fift.server.domain.cart.Cart;
import fift.server.domain.cartItem.CartItem;
import fift.server.domain.customer.Customer;
import fift.server.domain.customer.Customer_size;
import fift.server.service.cart.CartService;
import fift.server.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class MyPageController {

    private final CustomerService customerService;
    private final CartService cartService;

    // Mypage
    @GetMapping("/{customerId}")
    public String getMyPage(@PathVariable String customerId,Model model) {
        Customer customer = customerService.getCustomer(customerId);
        model.addAttribute("customer",customer);
        return "/mypage/mypage";
    }


    //사이즈 등록(GET)
    @GetMapping("/{customerId}/sizes")
    public String showAddSizeForm(@PathVariable String customerId, Model model) {
        model.addAttribute("customerId", customerId);
        model.addAttribute("customerSizeDto", new Customer_size());
        return "/mypage/sizepage";
    }

    //사이즈 등록(POST)
    @PostMapping("/{customerId}/sizes")
    public String addCustomerSize(
            @PathVariable String customerId,
            @ModelAttribute("customerSizeDto") Customer_size customerSizeDto
    ) {
        customerService.addCustomerSize(customerId, customerSizeDto);
        return "redirect:/customers/{customerId}/sizes";
    }

    //비밀번호 변경 (GET)
    @GetMapping("/{customerId}/change-password")
    public String showChangePasswordForm(@PathVariable String customerId, Model model) {
        model.addAttribute("customerId", customerId);
        model.addAttribute("newPassword", "");
        return "/mypage/passwordpage";
    }

    //비밀번호 변경 (POST)
    @PostMapping("/{customerId}/change-password")
    public String changePassword(
            @PathVariable String customerId,
            @RequestParam("newPassword") String newPassword
    ) {
        customerService.changePassword(customerId, newPassword);
        return "redirect:/";
    }


    //장바구니 내역
    @GetMapping("/{customerId}/cartList")
    public String getCartList(@PathVariable String customerId, Model model) {
        Customer customer = customerService.getCustomer(customerId);
        Cart cart = cartService.getCart(customer);
        List<CartItem> cartList = cartService.getCartList(cart);

        int totalCartItemCount = cartList.stream().mapToInt(CartItem::getCount).sum();

        model.addAttribute("customer", customer);
        model.addAttribute("cart", cart);
        model.addAttribute("cartList", cartList);
        model.addAttribute("totalCartItemCount", totalCartItemCount);

        return "cart/cartpage";
    }


    //장바구니 내역 다 삭제
    @GetMapping("{customerId}/cartList/deleteAll")
    public String deleteAll_Cart(@PathVariable String customerId) {
        Customer customer = customerService.getCustomer(customerId);
        cartService.cancelCart(customer);
        return "redirect:/";
    }

}
