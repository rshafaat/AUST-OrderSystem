package edu.aust.order;


import edu.aust.order.dto.OrderRequest;
import edu.aust.order.dto.OrderResponse;
import edu.aust.order.exceptions.OrderNotFoundException;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/orders/v1")
public class OrderController {

    @Autowired
    OrderServices orderServices;

    private HttpHeaders createHeader(String name, String value) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(name, value);
        return headers;
    }

    @GetMapping(
            path = "",
            produces = "application/json")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {

        return new ResponseEntity<>(
                orderServices.getAllOrders(),
                createHeader("Orders", "ALL"),
                HttpStatus.OK
        );

    }

    @GetMapping(
            path = "/{id}",
            produces = "application/json"
    )
    public ResponseEntity<OrderResponse> getOrder(
            @PathVariable long id) throws OrderNotFoundException {

        return new ResponseEntity<>(
                orderServices.getOrder(id),
                createHeader("Order", String.valueOf(id)),
                HttpStatus.OK
        );

    }

    @GetMapping(
            path = "/status/{status}",
            produces = "application/json"
    )
    public ResponseEntity<List<OrderResponse>> getOrdersByStatus(
            @PathVariable String status) throws OrderNotFoundException {

        return new ResponseEntity<>(
                orderServices.getOrdersByStatus(status),
                createHeader("Order-Status", status),
                HttpStatus.OK
        );

    }

    @GetMapping(
            path = "/history",
            produces = "application/json"
    )
    public ResponseEntity<List<OrderResponse>> getOrdersCompletedAndLast30Days() throws OrderNotFoundException {

        return new ResponseEntity<>(
                orderServices.getOrdersNewAndLast30Days(),
                HttpStatus.OK
        );

    }

    @PostMapping(
            path = "",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<OrderResponse> saveOrder(
            @RequestBody @Nullable OrderRequest orderRequest)
            throws DataIntegrityViolationException {

        OrderResponse newOrder = orderServices.saveOrder(orderRequest);

        return new ResponseEntity<>(
                newOrder,
                createHeader("Order", String.valueOf(newOrder.orderId())),
                HttpStatus.CREATED
        );

    }

    @PutMapping(
            path = "/{id}",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<OrderResponse> updateOrder(
            @PathVariable long id, @RequestBody OrderRequest orderRequest) throws OrderNotFoundException, DataIntegrityViolationException {

        return new ResponseEntity<>(
                orderServices.updateOrder(id, orderRequest),
                createHeader("Order", String.valueOf(id)),
                HttpStatus.ACCEPTED
        );

    }

}