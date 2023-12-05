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
    @Column(name = "gifticonid")
    private Long gifticonId;

    @ManyToOne
    @JoinColumn(name = "customerid")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    @Column(name = "expirationdate")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    @Column(name = "status")
    private int status;
}
