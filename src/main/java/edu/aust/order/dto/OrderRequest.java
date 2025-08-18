package edu.aust.order.dto;

public record OrderRequest(
        long customerId,
        long productId,
        String productName,
        int quantity,
        double price,
        String status
) {

}
