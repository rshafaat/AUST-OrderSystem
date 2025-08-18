package edu.aust.order;

import edu.aust.order.enums.OrderStatus;
import edu.aust.order.exceptions.OrderNotFoundException;
import edu.aust.order.models.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDao {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getAllOrders() {

        return orderRepository.findAll();

    }

    public Order getOrder(long id) throws OrderNotFoundException {

        Optional<Order> order = orderRepository.findById(id);

        if (order.isEmpty())
            throw new OrderNotFoundException("Order Not Found with order id: " + id);

        return order.get();

    }

    public List<Order> searchByProductName(String productName) throws OrderNotFoundException {

        List<Order> order = orderRepository.findAllByProductName(productName);

        if (order.isEmpty())
            throw new OrderNotFoundException("Orders Not Found with product name: " + productName);

        return order;

    }


    public List<Order> searchByStatus(OrderStatus status) throws OrderNotFoundException {

        return Optional.of(orderRepository.findAllByStatus(status))
                .filter(order -> !order.isEmpty())
                .orElseThrow(() -> new OrderNotFoundException("Orders Not Found with status: " + status));

    }

    public List<Order> getOrdersStatusAndLastDays(OrderStatus status, LocalDateTime orderDateTimeAgo) throws OrderNotFoundException {

        return Optional.of(orderRepository.getOrdersStatusAndLastDays(status, orderDateTimeAgo))
                .filter(order -> !order.isEmpty())
                .orElseThrow(() -> new OrderNotFoundException("Orders Not Found with status: COMPLETED and order after " + orderDateTimeAgo));

    }

    public Order addOrder(Order order) throws DataIntegrityViolationException {
        return orderRepository.save(order);
    }

    public Order updateOrder(long id, Order order) throws OrderNotFoundException, DataIntegrityViolationException {
        getOrder(id);
        order.setId(id);
        return orderRepository.save(order);
    }

}

