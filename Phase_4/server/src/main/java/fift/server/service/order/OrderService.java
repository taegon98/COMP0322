package fift.server.service.order;

import fift.server.domain.cart.Cart;
import fift.server.domain.cartItem.CartItem;
import fift.server.domain.customer.Customer;
import fift.server.domain.orders.Orders;
import fift.server.domain.orderdetail.OrderDetail;
import fift.server.domain.product.Product;
import fift.server.repository.cart.CartRepository;
import fift.server.repository.cartItem.CartItemRepository;
import fift.server.repository.customer.CustomerRepository;
import fift.server.repository.order.OrderRepository;
import fift.server.repository.orderdetail.OrderdetailRepository;
import fift.server.service.cart.CartService;
import fift.server.service.customer.CustomerService;
import fift.server.service.product.ProductService;
import fift.server.service.tier.TierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartService cartService;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderdetailRepository orderdetailRepository;
    private final OrderRepository orderRepository;
    private final TierService tierService;

    private final ProductService productService;
    private final CustomerRepository customerRepository;

    private final CustomerService customerService;

    @Transactional
    public OrderDetail add_Cart_Item(Product product, CartItem cartItem) {
        OrderDetail orderDetail = OrderDetail.createOrderDetail(product, cartItem);
        orderDetail.setTotalPrice(cartItem.getProduct().getPrice());
        orderdetailRepository.save(orderDetail);
        return orderDetail;
    }

    @Transactional
    public Long add_Cart_Order(Customer customer,List<OrderDetail> orderDetailList) {

        Orders order = Orders.createOrder(customer, orderDetailList);

        Calendar calendar = Calendar.getInstance();
        order.setOrderDate(calendar.getTime());

        calendar.add(Calendar.DAY_OF_MONTH, 3);
        order.setShippedDate(calendar.getTime());

        Orders saveOrder = orderRepository.save(order);

        for(OrderDetail orderDetail:orderDetailList) {
            orderDetail.setOrders(saveOrder);
            orderdetailRepository.save(orderDetail);
        }

        return order.getOrderId();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void order_Cart(Customer customer) {

        Double temp=0.0;

        Cart byUserId = cartRepository.findByCustomer(customer);
        List<CartItem> cartItemsByCart = cartItemRepository.findCartItemsByCart(byUserId);

        System.out.println(customer.getMoney());

        if(byUserId.getTotalPrice()<=customer.getMoney()) {
            List<OrderDetail> orderDetailList = new ArrayList<>();

            for (CartItem cartItem : cartItemsByCart) {
                temp += cartItem.getCart().getTotalPrice();
                OrderDetail orderDetail = add_Cart_Item(cartItem.getProduct(), cartItem);
                orderDetailList.add(orderDetail);

                customer.setAmount(customer.getAmount() + orderDetail.getTotalPrice());
                customer.setMoney(customer.getMoney() - orderDetail.getTotalPrice());

                tierService.updateTierByAmount(customer);
                customerRepository.save(customer);
            }

            Long aLong = add_Cart_Order(customer, orderDetailList);
            cartService.subCart(customer);
        }
    }



    public List<Orders> getOrderList(Customer customer) {
        List<Orders> ordersByCustomer = orderRepository.findOrdersByCustomer(customer);
        return ordersByCustomer;
    }

    public List<OrderDetail> getOrderDetailList(List<Orders> orderList) {
        List<OrderDetail> orderDetailList=new ArrayList<>();

        for(Orders orders:orderList) {
            List<OrderDetail> orderDetailsByOrders = orderdetailRepository.findOrderDetailsByOrders(orders);
            for(OrderDetail orderDetail:orderDetailsByOrders) {
                orderDetailList.add(orderDetail);
            }
        }
        return orderDetailList;
    }


    @Transactional
    public Orders add_One_Order(Customer customer,OrderDetail orderDetailList) {

        Orders order=new Orders();
        Random random = new Random();
        order.setOrderId( random.nextLong(10000));
        order.setCustomer(customer);
        order.setStatus(0);

        Calendar calendar = Calendar.getInstance();
        order.setOrderDate(calendar.getTime());

        calendar.add(Calendar.DAY_OF_MONTH, 3);
        order.setShippedDate(calendar.getTime());

        orderRepository.save(order);

        return order;
    }

    @Transactional
    public void order_One(String userId,Long id,String friendId) {
        Customer customer1 = customerService.getCustomer(userId);

        Customer customer = customerService.getCustomer(friendId);

        Product product = productService.getProduct(id);
        Random random = new Random();

        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId(random.nextLong(2000,10000));
        orderDetail.setProduct(product);
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(product.getPrice());

        Orders orders = add_One_Order(customer, orderDetail);
        orderDetail.setOrders(orders);

        orderdetailRepository.save(orderDetail);

        System.out.println(customer1.getMoney());

        customer1.setAmount(customer1.getAmount() + orderDetail.getTotalPrice());
        customer1.setMoney(customer1.getMoney() - orderDetail.getTotalPrice());

        System.out.println(customer1.getMoney());


        customerRepository.save(customer1);
    }

}
