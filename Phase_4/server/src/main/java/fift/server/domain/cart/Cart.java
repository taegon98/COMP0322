package fift.server.domain.cart;

import fift.server.domain.cartItem.CartItem;
import fift.server.domain.customer.Customer;
import fift.server.domain.order.Order;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_Id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id")
    private Customer customer;

    private int count; // 카트에 담긴 총 상품 개수

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems = new ArrayList<>();

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate; // 날짜

    @PrePersist
    public void createDate(){
        this.createDate = LocalDate.now();
    }


    @Builder
    public Cart(Customer customer) {
        this.customer = customer;
    }


    public static Cart createCart(Customer setCustomer) {
        Cart build = Cart.builder().customer(setCustomer).build();
        return build;
    }
}
