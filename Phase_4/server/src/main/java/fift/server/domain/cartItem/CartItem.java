package fift.server.domain.cartItem;

import fift.server.domain.cart.Cart;
import fift.server.domain.order.Order;
import fift.server.domain.orderdetail.OrderDetail;
import fift.server.domain.product.Product;
import jakarta.persistence.*;
import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItem_Id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private Product product;

    private int count; // 상품 개수


    @Builder
    public CartItem(Product product,Cart cart, Integer quantity) {
        this.product = product;
        this.cart = cart;
        this.count = quantity;
    }

    public static CartItem createCartItem(Cart setCart, Product setProduct, int setAmount) {
        CartItem build = CartItem.builder()
                .cart(setCart)
                .product(setProduct)
                .quantity(setAmount)
                .build();
        return build;
    }

    public void addCount(int count,int price) {
        this.count+=count;
    }

}