package edu.aust.order.dto;

import java.time.LocalDateTime;

public record OrderResponse(
        long orderId,
        long customerId,
        long productId,
        String productName,
        int quantity,
        double price,
        LocalDateTime orderDateTime,
        double totalPrice
) {
}

