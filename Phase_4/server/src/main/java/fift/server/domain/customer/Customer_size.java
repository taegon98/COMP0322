package fift.server.domain.customer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer_size")
@Getter
@Setter
public class Customer_size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sizeid")
    private Long sizeId;

    @OneToOne
    @JoinColumn(name = "customerid")
    private Customer customer;

    @Column(name = "sleevelength")
    private Double sleeveLength;

    @Column(name = "toplength")
    private Double topLength;

    @Column(name = "shoulder")
    private Double shoulder;

    @Column(name = "chestcircumference")
    private Double chestCircumference;

    @Column(name = "waist")
    private Double waist;

    @Column(name = "hipcircumference")
    private Double hipCircumference;

    @Column(name = "thighcircumference")
    private Double thighCircumference;

    @Column(name = "inseam")
    private Double inseam;

    @Column(name = "footlength")
    private Double footLength;

    @Column(name = "headcircumference")
    private Double headCircumference;
}
