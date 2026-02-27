package com.solvd.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceUtil {

    public static Double addTaxAndRound(double price) {
        double priceWithTax = price * 1.20;
        BigDecimal rounded = BigDecimal.valueOf(priceWithTax).setScale(2, RoundingMode.HALF_UP);
        return rounded.doubleValue();
    }

}
