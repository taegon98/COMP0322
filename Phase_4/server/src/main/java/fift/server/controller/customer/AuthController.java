package fift.server.controller.customer;

import fift.server.domain.customer.Customer;
import fift.server.domain.login.LoginDto;
import fift.server.repository.customer.CustomerRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final CustomerRepository customerRepository;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }

    //로그인
    @PostMapping("/login")
    public String login(@ModelAttribute("loginDto") LoginDto loginDto, Model model, HttpServletResponse response, HttpSession session) {
        String customerId = loginDto.getUserId();
        String password = loginDto.getPassword();

        if (customerId == null || customerId.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "Please provide valid credentials.");
            return "login";
        }

        Optional<Customer> id = customerRepository.findByUserId(customerId);

        if (customerId == null) {
            model.addAttribute("error", "User does not exist.");
            return "login";
        }

        Customer customer = id.get();
        String storedPassword = customer.getPassword();

        if (!password.equals(storedPassword)) {
            model.addAttribute("error", "Invalid credentials.");
            return "login";
        }

        session.setAttribute("customer", id.get());

        Cookie sessionCookie = new Cookie("sessionId", session.getId());
        sessionCookie.setMaxAge(30 * 60);
        response.addCookie(sessionCookie);

        return "redirect:/customer/dashboard";
    }


    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        session.invalidate();

        Cookie sessionCookie = new Cookie("sessionId", null);
        sessionCookie.setMaxAge(0);
        response.addCookie(sessionCookie);

        return "redirect:/login";
    }
}
