package com.solvd;

import com.solvd.web.page.ProductDetailsPage;
import com.solvd.web.page.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class ProductCardFunctionalityTest extends AbstractTest {

    @Test(description = "assert that product card from the main page works ")
    public void testAddProductToCard() {
        MainPage mainPage = new MainPage(getDriver());
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
