package fift.server.repository.orderdetail;

import fift.server.domain.order.Order;
import fift.server.domain.orderdetail.OrderDetail;
import fift.server.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderdetailRepository extends JpaRepository<OrderDetail,Long> {


}
