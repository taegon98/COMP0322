package fift.server.domain.gifticon;

import fift.server.domain.customer.Customer;
import fift.server.domain.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "gifticon")
@Getter
@Setter
public class Gifticon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gifticon_id")
    private Long gifticonId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "expiration_date")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    @Column(name = "status")
    private int status;
}
