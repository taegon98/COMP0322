package fift.server.domain.customer;

import fift.server.domain.tier.Tier;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Column(name = "amount")
    private Double amount;

    @Column(name = "coupon_id")
    private Long couponId;
}
