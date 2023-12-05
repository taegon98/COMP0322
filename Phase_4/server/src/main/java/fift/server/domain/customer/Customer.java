package fift.server.domain.customer;

import fift.server.domain.gifticon.Gifticon;
import fift.server.domain.order.Order;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @ManyToOne
    @JoinColumn(name = "tier_id")
    private Tier tier;

    @OneToMany(mappedBy = "customer")
    List<Order> orderList=new ArrayList<>();

    @Column(name = "name")
    private String name;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "postal_code")
    private Long postalCode;


    @Column(name = "coupon_id")
    private Long couponId;

    @Column(name = "money")
    private Double money;

    @Column(name = "amount")
    private Double amount;

    @OneToMany(mappedBy = "customer")
    private List<Gifticon> gifticons = new ArrayList<>();
}
