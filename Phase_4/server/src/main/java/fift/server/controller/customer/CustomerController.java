package fift.server.controller.customer;

import fift.server.domain.customer.Customer;
import fift.server.domain.customer.Customer_size;
import fift.server.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("customerDto", new Customer());
        return "register";
    }

    @PostMapping("/register")
    public String registerCustomer(@ModelAttribute("customerDto") Customer customerDto) {
        customerService.registerCustomer(customerDto);
        return "string";
    }

    @GetMapping("/{customerId}/sizes")
    public String showAddSizeForm(@PathVariable Long customerId, Model model) {
        model.addAttribute("customerId", customerId);
        model.addAttribute("customerSizeDto", new Customer_size());
        return "addSize";
    }

    @PostMapping("/{customerId}/sizes")
    public String addCustomerSize(
            @PathVariable Long customerId,
            @ModelAttribute("customerSizeDto") Customer_size customerSizeDto
    ) {
        customerService.addCustomerSize(customerId, customerSizeDto);
        return "redirect:/customers/{customerId}/sizes";
    }
}
