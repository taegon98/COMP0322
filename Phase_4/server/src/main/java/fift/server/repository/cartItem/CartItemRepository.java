package fift.server.repository.cartItem;

import fift.server.domain.cartItem.CartItem;
import fift.server.domain.order.Order;
import fift.server.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {


    CartItem findByCartIdAndProductId(Long cartId,Long productId);
}
