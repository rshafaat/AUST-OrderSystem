package edu.aust.order.models;

import edu.aust.order.enums.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "AUST_ORDER")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private long id;

    @Column(name = "CUSTOMER_ID",
            nullable = false)
    private long customerId;

    @Column(name = "PRODUCT_ID",
            nullable = false)
    private long productId;

    @Column(name = "PRODUCT_NAME",
            length = 512,
            nullable = false)
    private String productName;

    @Column(name = "QUANTITY",
            nullable = false)
    private int quantity;

    @Column(name = "PRICE",
            nullable = false,
            precision = 6)
    private double price; //999999.9999 // float | double | BigDecimal???

    @Column(name = "ORDER_DATETIME",
            nullable = false)
    private LocalDateTime orderDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS",
            nullable = false,
            length = 20)
    private OrderStatus status;

    public Order(long id, long customerId, long productId, String productName, int quantity,
                 double price, OrderStatus status) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.orderDateTime = LocalDateTime.now();
        this.status = status;
    }

    public Order(long customerId, long productId, String productName, int quantity,
                 double price, OrderStatus status) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.orderDateTime = LocalDateTime.now();
        this.status = status;
    }

    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
