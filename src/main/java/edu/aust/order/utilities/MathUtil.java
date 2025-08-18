package edu.aust.order.utilities;

public class MathUtil {

    public static final double getTotalPrice(int quantity, double pricePerUnit) {
        return ((double) quantity) * pricePerUnit;
    }

}
