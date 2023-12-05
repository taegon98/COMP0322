package fift.server.repository.cartItem;

import fift.server.domain.cart.Cart;
import fift.server.domain.cartItem.CartItem;
import fift.server.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {


    CartItem findByCartAndProduct(Cart cart, Product product);

    List<CartItem> findCartItemsByCart(Cart cart);
}
