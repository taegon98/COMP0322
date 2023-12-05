package fift.server.controller.gifticon;

import fift.server.service.gifticon.GifticonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class GifticonController {
    private final GifticonService gifticonService;

    // Gifticon 선물하는 기능
    @PostMapping("/gifticon/gift")
    public String giftGifticonToUser(
            @RequestParam("userId") Long userId,
            @RequestParam("productId") Long productId
    ) {
        try {
            gifticonService.giftGifticonToUser(userId, productId);
            return "redirect:/success";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }
}