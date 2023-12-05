package fift.server.controller.customer;

import fift.server.domain.login.LoginDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class AuthController {

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }

    //로그인
    @PostMapping("/login")
    public String login(@ModelAttribute("loginDto") LoginDto loginDto, Model model, HttpServletResponse response, HttpSession session) {


        session.setAttribute("user", loginDto.getUserId());

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
