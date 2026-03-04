package com.solvd.util;

import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceUtil {

    public static BigDecimal addTaxAndRound(double price){
        double priceWithTax = price * 1.20;
        BigDecimal rounded = BigDecimal.valueOf(priceWithTax).setScale(2, RoundingMode.HALF_UP);
        return rounded;
    }

    public static int parseItemAmountText(String rawAmount) {
        return Integer.valueOf(rawAmount.split(" ")[0]);
    }

}
