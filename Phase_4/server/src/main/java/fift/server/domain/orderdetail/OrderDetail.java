package fift.server.domain.orderdetail;

import fift.server.domain.cartItem.CartItem;
import fift.server.domain.orders.Orders;
import fift.server.domain.product.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Random;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class OrderDetail {

    @Id
    private Long detailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="productId")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="orderId")
    private Orders orders;

    private Integer Quantity;
    private double totalPrice;

    @Builder
    public OrderDetail(Product product, CartItem cartItem) {
        Random random = new Random();
        this.detailId = random.nextLong(2000,10000);
        this.product = product;
        this.Quantity=cartItem.getCount();
        this.totalPrice=cartItem.getCount()*cartItem.getProduct().getPrice();
    }

    public static OrderDetail createOrderDetail(Product product, CartItem cartItem) {
        OrderDetail build = OrderDetail.builder()
                .product(product)
                .cartItem(cartItem)
                .build();
        return build;
    }


}
