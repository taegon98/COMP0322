package fift.server.domain.customer;

import fift.server.domain.gifticon.Gifticon;
import fift.server.domain.orders.Orders;
import fift.server.domain.tier.Tier;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer {
    @Id
    @Column(name = "customerid")
    private Long customerId;

    @ManyToOne
    @JoinColumn(name = "tierid")
    private Tier tier;

    @OneToMany(mappedBy = "customer")
    List<Orders> orderList=new ArrayList<>();

    @Column(name = "name")
    private String name;

    @Column(name = "userid")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "postalCode")
    private Long postalCode;


    @Column(name = "couponid")
    private Long couponId;

    @Column(name = "money")
    private Double money;

    @Column(name = "amount")
    private Double amount;

    @OneToMany(mappedBy = "customer")
    private List<Gifticon> gifticons = new ArrayList<>();
}
