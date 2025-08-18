package edu.aust.order.repository;

import edu.aust.order.OrderRepository;
import edu.aust.order.enums.OrderStatus;
import edu.aust.order.models.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("repotest")
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepositoryTest;

    @Test
    void testGetAllOrders() {

        //Given
        fillUpData();

        // Action
        //List<Order> orders = orderRepositoryTest.getOrdersCompletedAndLastDays(LocalDateTime.now().minusDays(30));
        List<Order> orders = orderRepositoryTest.findAll();
        // Assert
        assertNotNull(orders);
        assertFalse(orders.isEmpty());

    }
    


    void fillUpData() {

        List<Order> orders = new ArrayList<>();

        orders.add(new Order(2020, 56789, "Mobile Phone", 100, 24999.9999, OrderStatus.COMPLETED));
        orders.add(new Order(2020, 56790, "Smart TV", 10, 150000.0000, OrderStatus.COMPLETED));
        orders.add(new Order(2021, 56789, "Mobile Phone", 150, 54999.9999, OrderStatus.COMPLETED));
        orders.add(new Order(2021, 56790, "Smart TV", 50, 250000.0000, OrderStatus.COMPLETED));
        orders.add(new Order(2022, 56789, "Mobile Phone", 1, 14999.9999, OrderStatus.COMPLETED));

        orderRepositoryTest.saveAll(orders);
    }

}
