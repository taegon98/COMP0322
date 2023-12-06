package fift.server.controller.customer;

import fift.server.domain.customer.Customer;
import fift.server.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    //회원가입 (GET)
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "register/registerpage";
    }

    //회원가입 (POST)
    @PostMapping("/register")
    public String registerCustomer(@ModelAttribute("customerDto") Customer customerDto) {
        customerService.registerCustomer(customerDto);
        return "redirect:/";
    }


}
