package edu.aust.order;

import edu.aust.order.dto.OrderRequest;
import edu.aust.order.dto.OrderResponse;
import edu.aust.order.enums.OrderStatus;
import edu.aust.order.exceptions.OrderNotFoundException;
import edu.aust.order.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class OrderServices {

    @Autowired
    OrderDao orderDao;

    public List<OrderResponse> getAllOrders() {
        return OrderConverter.convert(orderDao.getAllOrders());
    }

    public OrderResponse getOrder(Long id) throws OrderNotFoundException {
        return OrderConverter.convert(orderDao.getOrder(id));
    }

    public List<OrderResponse> getOrdersByStatus(String status) throws OrderNotFoundException {
        return OrderConverter.convert(orderDao.searchByStatus(OrderStatus.valueOf(status)));
    }

    public List<OrderResponse> getOrdersNewAndLast30Days() throws OrderNotFoundException {
        return OrderConverter.convert(orderDao.getOrdersStatusAndLastDays(OrderStatus.NEW, LocalDateTime.now().minusDays(30)));
    }

    public OrderResponse saveOrder(OrderRequest orderRequest) throws DataIntegrityViolationException {
        Order e = OrderConverter.convert(orderRequest);
        return OrderConverter.convert(orderDao.addOrder(e));
    }

    public OrderResponse updateOrder(long id, OrderRequest orderRequest) throws OrderNotFoundException, DataIntegrityViolationException {
        Order order = OrderConverter.convert(orderRequest);
        return OrderConverter.convert(orderDao.updateOrder(id, order));
    }

}