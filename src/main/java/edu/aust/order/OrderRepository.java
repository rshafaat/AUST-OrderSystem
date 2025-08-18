package edu.aust.order;

import edu.aust.order.enums.OrderStatus;
import edu.aust.order.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    public List<Order> findAllByProductName(String productName);

    public List<Order> findAllByStatus(OrderStatus status);

    @Query("SELECT o " +
            "FROM Order o " +
            "WHERE o.status = :status " +
                "AND o.orderDateTime > :orderDateTimeAgo")
    public List<Order> getOrdersStatusAndLastDays(@Param("status") OrderStatus status,
                                                  @Param("orderDateTimeAgo") LocalDateTime orderDateTimeAgo);

}
