package fift.server.domain.orderdetail;

import fift.server.domain.order.Order;
import fift.server.domain.product.Product;
import jakarta.persistence.*;
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

    private Long Quantity;
    private Long total_Price;

}
