package fift.server.repository.order;

import fift.server.domain.customer.Customer;
import fift.server.domain.orders.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {
    List<Orders> findOrdersByCustomer(Customer customer);
}
