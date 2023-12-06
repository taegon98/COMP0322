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
import java.util.Random;

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
    private Integer Status;

    @Builder
    public Orders(Customer customer, Integer status) {
        Random random = new Random();
        this.orderId = random.nextLong(10000);
        this.customer = customer;
        this.Status = status;
    }


    public static Orders createOrder(Customer customer, List<OrderDetail> orderDetailList) {
        Orders build = Orders.builder()
                .customer(customer)
                .status(0)
                .build();

        for(OrderDetail orderDetail:orderDetailList) {
            build.addOrderDetail(orderDetail);
        }
        return build;
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        this.orderDetailList.add(orderDetail);
    }



}
