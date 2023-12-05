package fift.server.service.gifticon;

import fift.server.domain.customer.Customer;
import fift.server.domain.gifticon.Gifticon;
import fift.server.domain.product.Product;
import fift.server.repository.GifticonRepository;
import fift.server.repository.customer.CustomerRepository;
import fift.server.repository.product.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class GifticonService {
    private final GifticonRepository gifticonRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    // Gifticon 선물 기능
    @Transactional
    public Gifticon giftGifticonToUser(Long userId, Long productId) {
        Customer customer = customerRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        LocalDate currentDate = LocalDate.now();
        LocalDate expirationDate = currentDate.plusDays(365);

        double productPrice = product.getPrice();
        double currentMoney = customer.getMoney();

        if (currentMoney >= productPrice) {
            double updatedMoney = currentMoney - productPrice;
            if (updatedMoney >= 0) {
                customer.setMoney((long) (currentMoney - productPrice));
                customerRepository.save(customer);

                Gifticon gifticon = new Gifticon();
                gifticon.setCustomer(customer);
                gifticon.setProduct(product);
                gifticon.setExpirationDate(java.sql.Date.valueOf(expirationDate));
                gifticon.setStatus(1);

                return gifticonRepository.save(gifticon);
            } else {
                throw new IllegalStateException("Insufficient funds to purchase this product");
            }
        } else {
            throw new IllegalStateException("Insufficient funds to purchase this product");
        }
    }
}
