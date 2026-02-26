package com.solvd.carinatest;


import com.solvd.carinaweb.page.common.BasePage;
import com.solvd.carinaweb.page.common.ItemPage;
import com.solvd.carinaweb.page.common.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductCardFunctionalityTest extends BaseTest {

    @Test(description = "assert that product card from the main page works")
    public void testAddProductToCard() {

        BasePage basePage = initPage(getDriver(), BasePage.class);
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