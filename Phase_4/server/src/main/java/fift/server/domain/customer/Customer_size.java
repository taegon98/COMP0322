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
    @Column(name = "size_id")
    private Long sizeId;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "sleeve_length")
    private Double sleeveLength;

    @Column(name = "top_length")
    private Double topLength;

    @Column(name = "shoulder")
    private Double shoulder;

    @Column(name = "chest_circumference")
    private Double chestCircumference;

    @Column(name = "waist")
    private Double waist;

    @Column(name = "hip_circumference")
    private Double hipCircumference;

    @Column(name = "thigh_circumference")
    private Double thighCircumference;

    @Column(name = "inseam")
    private Double inseam;

    @Column(name = "foot_length")
    private Double footLength;

    @Column(name = "head_circumference")
    private Double headCircumference;
}
