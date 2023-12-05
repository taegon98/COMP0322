package fift.server.repository.cartItem;

import fift.server.domain.cart.Cart;
import fift.server.domain.cartItem.CartItem;
import fift.server.domain.products.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {


    CartItem findByCartAndProducts(Cart cart, Products products);

    List<CartItem> findCartItemsByCart(Cart cart);
}
