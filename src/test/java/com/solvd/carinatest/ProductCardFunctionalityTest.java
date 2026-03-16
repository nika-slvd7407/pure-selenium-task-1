package com.solvd.carinatest;


import com.solvd.carinaweb.page.common.MainPage;
import com.solvd.carinaweb.page.common.ProductDetailsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class ProductCardFunctionalityTest extends BaseTest {

    public ProductCardFunctionalityTest(String browser) {
        super(browser);
    }

    @Test(description = "assert that product card from the main page works")
    public void testProductCard() {

        MainPage mainPage = openMainPage();

        int itemIndex = 0;

        BigDecimal expectedPrice = mainPage.getPrice(itemIndex);
        ProductDetailsPage productDetailsPage = mainPage.clickItem(itemIndex);

        Assert.assertEquals(
                productDetailsPage.getItemPrice(),
                expectedPrice,
                "Product price mismatch between Main Page and Item Page"
        );
    }
}