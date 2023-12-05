package fift.server.service.cart;

import fift.server.domain.cart.Cart;
import fift.server.domain.cartItem.CartItem;
import fift.server.domain.customer.Customer;
import fift.server.domain.product.Product;
import fift.server.repository.cart.CartRepository;
import fift.server.repository.cartItem.CartItemRepository;
import fift.server.repository.product.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final ProductRepository productRepository;

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    public void addCart(Customer customer, Product newProduct,int amount) {

        Cart cart = cartRepository.findByUserId(customer.getUserId());

        if(cart==null) {
            cart = Cart.createCart(customer);
            cartRepository.save(cart);
        }

        Product product = productRepository.findProductById(newProduct.getProduct_Id());
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getCart_Id(), product.getProduct_Id());

        if(cartItem==null) {
            cartItem = CartItem.createCartItem(cart, product, amount);
            cartItemRepository.save(cartItem);
        }
        else {
            CartItem update = CartItem.createCartItem(cartItem.getCart(),
                    cartItem.getProduct(),
                    cartItem.getCount());
            cartItemRepository.save(update);
        }
        cart.setCount(cart.getCount()+amount);

    }
}
