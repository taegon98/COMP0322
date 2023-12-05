package fift.server.domain.gifticon;

import fift.server.domain.customer.Customer;
import fift.server.domain.products.Products;
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
    @Column(name = "gifticonid")
    private Long gifticonId;

    @ManyToOne
    @JoinColumn(name = "customerid")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "productid")
    private Products products;

    @Column(name = "expirationdate")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    @Column(name = "status")
    private int status;
}
