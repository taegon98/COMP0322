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

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    public void addCart(Customer customer, Product newProduct, int amount) {

        Cart cart = cartRepository.findByCustomer(customer);

        if(cart==null) {
            cart = Cart.createCart(customer);
            cartRepository.save(cart);
        }

        Product product = productRepository.findByProductId(newProduct.getProductId());
        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product);

        System.out.println(product.getProductName());

        if(cartItem==null) {
            cartItem = CartItem.createCartItem(cart, product, amount);
        }
        else {
            cartItem = CartItem.createCartItem(cartItem.getCart(),
                    cartItem.getProduct(),
                    cartItem.getCount());
        }
        cartItemRepository.save(cartItem);

        cart.setTotalPrice(cart.getTotalPrice()+cartItem.getCount()*cartItem.getProduct().getPrice());
        cart.setCount(cart.getCount()+cartItem.getCount());

        System.out.println(cart.getTotalPrice());
        System.out.println(cart.getCount());
        cartRepository.save(cart);

    }

    public void subCart(Customer customer) {
        Cart cart = cartRepository.findByCustomer(customer);
        List<CartItem> cartItemsByCart = cartItemRepository.findCartItemsByCart(cart);
        cartItemRepository.deleteAll(cartItemsByCart);
        cartRepository.delete(cart);
    }


    public void cancelCart(Customer customer) {
        Cart cart = getCart(customer);
        List<CartItem> cartList = getCartList(cart);

        for(CartItem cartItem:cartList) {
            cartItemRepository.delete(cartItem);
        }
        cartRepository.delete(cart);
    }


    public Cart getCart(Customer customer) {
        Cart byCustomer = cartRepository.findByCustomer(customer);
        return byCustomer;
    }

    public List<CartItem> getCartList(Cart cart) {
        List<CartItem> cartItemsByCart = cartItemRepository.findCartItemsByCart(cart);
        return cartItemsByCart;
    }

}
