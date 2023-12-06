package fift.server.controller.order;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Isolation;
import fift.server.domain.customer.Customer;
import fift.server.service.customer.CustomerService;
import fift.server.service.order.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class OrderController {

    private final CustomerService customerService;
    private final OrderService orderService;
    @GetMapping("customer/{customerId}/cartList/order")
    public String cart_Order(@PathVariable("customerId") String id, HttpServletRequest request, HttpServletResponse response) {
        Customer customer = customerService.getCustomer(id);

        HttpSession session = request.getSession();
        session.setAttribute("customer", customer);

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("JSESSIONID")) {
                cookie.setValue(session.getId());
                response.addCookie(cookie);
                break;
            }
        }

        orderService.order_Cart(customer);
        return "redirect:/";
    }
}
