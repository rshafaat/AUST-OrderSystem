package edu.aust.order;

import edu.aust.order.dto.OrderRequest;
import edu.aust.order.dto.OrderResponse;
import edu.aust.order.enums.OrderStatus;
import edu.aust.order.models.Order;
import edu.aust.order.utilities.MathUtil;

import java.util.List;
import java.util.stream.Collectors;

public class OrderConverter {

    public static Order convert(OrderRequest orderRequest) {

        return new Order(
                orderRequest.customerId(),
                orderRequest.productId(),
                orderRequest.productName(),
                orderRequest.quantity(),
                orderRequest.price(),
                OrderStatus.valueOf(orderRequest.status())
        );

    }

    public static OrderResponse convert(Order e) {

        return new OrderResponse(
                e.getId(),
                e.getCustomerId(),
                e.getProductId(),
                e.getProductName(),
                e.getQuantity(),
                e.getPrice(),
                e.getOrderDateTime(),
                MathUtil.getTotalPrice(e.getQuantity(), e.getPrice())
        );

    }

    public static List<OrderResponse> convert(List<Order> orders) {
        return orders.stream()
                .map(OrderConverter::convert)
                .collect(Collectors.toList());
    }

}