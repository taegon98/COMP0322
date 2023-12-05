package fift.server.domain.orderdetail;

import fift.server.domain.cartItem.CartItem;
import fift.server.domain.order.Order;
import fift.server.domain.product.Product;
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
    private Long detail_Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_Id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_Id")
    private Order order;

    private Integer Quantity;
    private double total_Price;

    @Builder
    public OrderDetail(Product product,CartItem cartItem) {
        this.product = product;
        this.Quantity=cartItem.getCount();
        this.total_Price=cartItem.getCount()*cartItem.getProduct().getPrice();
    }

    public static OrderDetail createOrderDetail(Product product, CartItem cartItem) {
        OrderDetail build = OrderDetail.builder()
                .product(product)
                .cartItem(cartItem)
                .build();
        return build;
    }

}
