package fift.server.domain.orderdetail;

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
    private Integer total_Price;

    @Builder
    public OrderDetail(Product product, Order order, Integer quantity, Integer total_Price) {
        this.product = product;
        this.order = order;
        Quantity = quantity;
        this.total_Price = total_Price;
    }

    public static OrderDetail createOrderItem(Product product,Order order,Integer Quantity,Integer price) {
        OrderDetail build = OrderDetail.builder()
                .product(product)
                .order(order)
                .quantity(Quantity)
                .total_Price(price)
                .build();
        return build;
    }

    public void addDetail(Integer price) {
        this.Quantity+=1;
        this.total_Price+=price;
    }
}
