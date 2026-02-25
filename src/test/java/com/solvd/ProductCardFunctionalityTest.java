package com.solvd;

import com.solvd.web.page.ItemPage;
import com.solvd.web.page.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class ProductCardFunctionalityTest extends AbstractTest {

    @Test(description = "assert that product card from the main page works ")
    public void testAddProductToCard() {
        MainPage mainPage = new MainPage(getDriver());
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
