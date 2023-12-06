package fift.server.repository.orderdetail;

import fift.server.domain.orderdetail.OrderDetail;
import fift.server.domain.orders.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderdetailRepository extends JpaRepository<OrderDetail,Long> {

    List<OrderDetail> findOrderDetailsByOrders(Orders order);
}
