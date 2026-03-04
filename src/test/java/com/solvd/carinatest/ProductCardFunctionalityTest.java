package com.solvd.carinatest;

import com.solvd.carinaweb.page.BasePage;
import com.solvd.carinaweb.page.ProductDetailsPage;
import com.solvd.carinaweb.page.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class ProductCardFunctionalityTest extends BaseTest {

    @Test(description = "assert that product card from the main page works")
    public void testAddProductToCard() {

        BasePage basePage = new BasePage(getDriver());
        basePage.open();
        MainPage mainPage = basePage.switchToShopFrame();

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