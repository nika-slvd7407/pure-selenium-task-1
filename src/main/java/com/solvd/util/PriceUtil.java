package com.solvd.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceUtil {

    public static BigDecimal addTaxAndRound(BigDecimal price) {
        BigDecimal priceWithTax = price.multiply(BigDecimal.valueOf(1.20));
        BigDecimal rounded = priceWithTax.setScale(2, RoundingMode.HALF_UP);
        return rounded;
    }

    public static int parseItemAmountText(String rawAmount) {
        return Integer.valueOf(rawAmount.split(" ")[0]);
    }

    public static BigDecimal parsePrice(String rawPrice) {
        return new BigDecimal(rawPrice.replaceAll("[^0-9.]", ""));
    }

}