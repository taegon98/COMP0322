package fift.server.service.order;

import fift.server.domain.cart.Cart;
import fift.server.domain.cartItem.CartItem;
import fift.server.domain.customer.Customer;
import fift.server.domain.order.Order;
import fift.server.domain.orderdetail.OrderDetail;
import fift.server.domain.product.Product;
import fift.server.repository.cart.CartRepository;
import fift.server.repository.cartItem.CartItemRepository;
import fift.server.repository.customer.CustomerRepository;
import fift.server.repository.order.OrderRepository;
import fift.server.repository.orderdetail.OrderdetailRepository;
import fift.server.repository.product.ProductRepository;
import fift.server.service.cart.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final CartService cartService;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderdetailRepository orderdetailRepository;


    private final OrderRepository orderRepository;

    public OrderDetail add_Cart_Item(Product product, CartItem cartItem) {
        OrderDetail orderDetail = OrderDetail.createOrderDetail(product, cartItem);
        orderdetailRepository.save(orderDetail);
        return orderDetail;
    }

    public Long add_Cart_Order(Customer customer,List<OrderDetail> orderDetailList) {
        Order order = Order.createOrder(customer, orderDetailList);
        orderRepository.save(order);
        return order.getOrder_Id();
    }

    public Long order_Cart(Customer customer) {
        Cart byUserId = cartRepository.findByUserId(customer.getUserId());
        List<CartItem> cartItemsByCart = cartItemRepository.findCartItemsByCart(byUserId);

        List<OrderDetail> orderDetailList=new ArrayList<>();

        for(CartItem cartItem:cartItemsByCart) {
            OrderDetail orderDetail = add_Cart_Item(cartItem.getProduct(), cartItem);
            orderDetailList.add(orderDetail);
        }

        Long aLong = add_Cart_Order(customer, orderDetailList);
        cartService.subCart(customer);

        return aLong;
    }
}
