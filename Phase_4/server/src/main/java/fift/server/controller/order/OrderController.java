package fift.server.controller.order;


import org.springframework.ui.Model;
import fift.server.domain.orderdetail.OrderDetail;
import fift.server.domain.orders.Orders;
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

import java.util.List;


@Controller
@RequiredArgsConstructor
public class OrderController {

    private final CustomerService customerService;
    private final OrderService orderService;

    @GetMapping("customer/{customerId}/cartList/order")
    public String cart_Order(@PathVariable("customerId") String id) {
        Customer customer = customerService.getCustomer(id);
        orderService.order_Cart(customer);
        return "redirect:/";
    }

    @GetMapping("customer/{customerId}/cartList/orderList")
    public String getOrderList(@PathVariable("customerId") String id,Model model) {
        Customer customer = customerService.getCustomer(id);

        List<Orders> orderList = orderService.getOrderList(customer);
        List<OrderDetail> orderDetailList = orderService.getOrderDetailList(orderList);

        model.addAttribute("orderDetailList",orderDetailList);

        return "/order/orderListpage";
    }
}
