package fift.server.domain.orders;

import fift.server.domain.customer.Customer;
import fift.server.domain.orderdetail.OrderDetail;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Orders {

    @Id
    private Long orderId;

    @OneToMany(mappedBy = "orders")
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customerid")
    private Customer customer;

    private Date orderDate;
    private Date expectedDate;
    private Date shippedDate;
    private Boolean Status;

    @Builder
    public Orders(Customer customer, Boolean status) {
        this.customer = customer;
        Status = status;
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetailList.add(orderDetail);
        orderDetail.setOrders(this);
    }

    public static Orders createOrder(Customer customer, List<OrderDetail> orderDetailList) {
        Orders build = Orders.builder()
                .customer(customer)
                .status(false)
                .build();
        for(OrderDetail orderDetail:orderDetailList) {
            build.addOrderDetail(orderDetail);
        }
        return build;
    }


}
