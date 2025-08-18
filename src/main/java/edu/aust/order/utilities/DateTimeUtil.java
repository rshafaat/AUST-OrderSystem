package edu.aust.order.utilities;

import java.time.LocalDate;
import java.time.Period;

public class DateTimeUtil {

    public static final int getDifferenceinYears(LocalDate start, LocalDate end) {
        return Period.between(start, end).getYears();
    }

}
