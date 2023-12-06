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
        return "login/loginpage";
    }

    //사이즈 등록(GET)
    @GetMapping("/{customerId}/sizes")
    public String showAddSizeForm(@PathVariable Long customerId, Model model) {
        model.addAttribute("customerId", customerId);
        model.addAttribute("customerSizeDto", new Customer_size());
        return "addSize";
    }

    //사이즈 등록(POST)
    @PostMapping("/{customerId}/sizes")
    public String addCustomerSize(
            @PathVariable Long customerId,
            @ModelAttribute("customerSizeDto") Customer_size customerSizeDto
    ) {
        customerService.addCustomerSize(customerId, customerSizeDto);
        return "redirect:/customers/{customerId}/sizes";
    }

    //비밀번호 변경 (GET)
    @GetMapping("/{customerId}/change-password")
    public String showChangePasswordForm(@PathVariable Long customerId, Model model) {
        model.addAttribute("customerId", customerId);
        model.addAttribute("newPassword", "");
        return "changePassword";
    }

    //비밀번호 변경 (POST)
    @PostMapping("/{customerId}/change-password")
    public String changePassword(
            @PathVariable Long customerId,
            @RequestParam("newPassword") String newPassword
    ) {
        customerService.changePassword(customerId, newPassword);
        return "redirect:/customer/{customerId}/change-password";
    }


}
