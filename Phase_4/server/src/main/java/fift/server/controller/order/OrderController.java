package fift.server.controller.order;


import fift.server.domain.customer.Customer;
import fift.server.service.customer.CustomerService;
import fift.server.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final CustomerService customerService;
    private final OrderService orderService;

    @PostMapping("customer/{customerId}/cartList/order")
    public String cart_Order(@PathVariable("customerId") Long id) {
        Customer customer = customerService.getCustomer(id);
        orderService.order_Cart(customer);
        return "redirect:/";
    }
}
