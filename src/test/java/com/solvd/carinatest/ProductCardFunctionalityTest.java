package com.solvd.carinatest;

import com.solvd.carinaweb.page.BasePage;
import com.solvd.carinaweb.page.ItemPage;
import com.solvd.carinaweb.page.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductCardFunctionalityTest extends BaseTest {

    @Test(description = "assert that product card from the main page works")
    public void testAddProductToCard() {

        BasePage basePage = new BasePage(getDriver());
        basePage.open();
        MainPage mainPage = basePage.switchToShopFrame();

        int itemIndex = 0;

        double expectedPrice = mainPage.getPrice(itemIndex);
        ItemPage itemPage = mainPage.clickItem(itemIndex);

        Assert.assertEquals(
                itemPage.getItemPrice(),
                expectedPrice,
                "Product price mismatch between Main Page and Item Page"
        );
    }
}