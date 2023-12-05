package fift.server.repository.orderdetail;

import fift.server.domain.orderdetail.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface orderdetailRepository extends JpaRepository<OrderDetail,Long> {
}
