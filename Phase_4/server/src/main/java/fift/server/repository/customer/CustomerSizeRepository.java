package fift.server.repository.customer;

import fift.server.domain.customer.Customer_size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSizeRepository extends JpaRepository<Customer_size, Long> {
}
