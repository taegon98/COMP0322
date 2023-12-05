package fift.server.repository.cart;

import fift.server.domain.cart.Cart;
import fift.server.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByCustomer(Customer customer);
}
