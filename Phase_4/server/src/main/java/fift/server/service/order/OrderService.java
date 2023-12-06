package fift.server.service.order;

import fift.server.domain.cart.Cart;
import fift.server.domain.cartItem.CartItem;
import fift.server.domain.customer.Customer;
import fift.server.domain.orders.Orders;
import fift.server.domain.orderdetail.OrderDetail;
import fift.server.domain.product.Product;
import fift.server.repository.cart.CartRepository;
import fift.server.repository.cartItem.CartItemRepository;
import fift.server.repository.order.OrderRepository;
import fift.server.repository.orderdetail.OrderdetailRepository;
import fift.server.service.cart.CartService;
import fift.server.service.tier.TierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartService cartService;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderdetailRepository orderdetailRepository;
    private final OrderRepository orderRepository;
    private final TierService tierService;

    @Transactional
    public OrderDetail add_Cart_Item(Product product, CartItem cartItem) {
        OrderDetail orderDetail = OrderDetail.createOrderDetail(product, cartItem);
        orderdetailRepository.save(orderDetail);
        return orderDetail;
    }

    @Transactional
    public Long add_Cart_Order(Customer customer,List<OrderDetail> orderDetailList) {
        Orders order = Orders.createOrder(customer, orderDetailList);
        orderRepository.save(order);
        return order.getOrderId();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Long order_Cart(Customer customer) {

        Cart byUserId = cartRepository.findByCustomer(customer);
        List<CartItem> cartItemsByCart = cartItemRepository.findCartItemsByCart(byUserId);

        if(byUserId.getTotalPrice()<=customer.getMoney()) {
            List<OrderDetail> orderDetailList=new ArrayList<>();

            for(CartItem cartItem:cartItemsByCart) {
                OrderDetail orderDetail = add_Cart_Item(cartItem.getProduct(), cartItem);
                orderDetailList.add(orderDetail);
                customer.setAmount(customer.getAmount()+orderDetail.getTotalPrice());
                tierService.updateTierByAmount(customer);
            }

            Long aLong = add_Cart_Order(customer, orderDetailList);
            cartService.subCart(customer);
            return aLong;
        }
        else {
            return byUserId.getCartId();
        }
    }
}
