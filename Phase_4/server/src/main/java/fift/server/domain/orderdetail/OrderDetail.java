package fift.server.domain.orderdetail;

import fift.server.domain.cartItem.CartItem;
import fift.server.domain.orders.Orders;
import fift.server.domain.products.Products;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="productId")
    private Products products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="orderId")
    private Orders orders;

    private Integer Quantity;
    private double totalPrice;

    @Builder
    public OrderDetail(Products product, CartItem cartItem) {
        this.products = product;
        this.Quantity=cartItem.getCount();
        this.totalPrice=cartItem.getCount()*cartItem.getProducts().getPrice();
    }

    public static OrderDetail createOrderDetail(Products product, CartItem cartItem) {
        OrderDetail build = OrderDetail.builder()
                .product(product)
                .cartItem(cartItem)
                .build();
        return build;
    }

}
