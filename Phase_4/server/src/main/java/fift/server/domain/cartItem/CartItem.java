package fift.server.domain.cartItem;

import fift.server.domain.cart.Cart;
import fift.server.domain.products.Products;
import jakarta.persistence.*;
import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cartid")
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="productid")
    private Products products;

    private int count; // 상품 개수


    @Builder
    public CartItem(Products product, Cart cart, Integer quantity) {
        this.products = product;
        this.cart = cart;
        this.count = quantity;
    }

    public static CartItem createCartItem(Cart setCart, Products setProduct, int setAmount) {
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