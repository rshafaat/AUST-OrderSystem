package edu.aust.order.utilities;

public class MathUtil {

    public static final float getTotalPrice(int quantity, float pricePerUnit) {
        return quantity * pricePerUnit;
    }

}
