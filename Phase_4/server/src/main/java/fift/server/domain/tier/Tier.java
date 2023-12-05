package fift.server.domain.tier;

import fift.server.domain.customer.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tier")
@Getter
@Setter
public class Tier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tierid")
    private Long tierId;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "benefit")
    private Long benefit;

    @OneToMany(mappedBy = "tier")
    private List<Customer> customer = new ArrayList<>();

}
