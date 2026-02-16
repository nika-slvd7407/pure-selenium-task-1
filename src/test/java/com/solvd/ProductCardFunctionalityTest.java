package com.solvd;

import com.solvd.web.page.ItemPage;
import com.solvd.web.page.MainPage;
import org.testng.annotations.Test;

import java.util.Random;

public class ProductCardFunctionalityTest extends AbstractTest {

    @Test(description = "assert that product card from the main page works ")
    public void testProductCard() {
        MainPage mainPage = new MainPage(getDriver());
        int itemIndex = new Random().nextInt(mainPage.getMainPageItemAmount());

        Double supposedCost = mainPage.getPrice(itemIndex);
        String supposedTitle = mainPage.getName(itemIndex);

        ItemPage itemPage = mainPage.clickItem(itemIndex);
        Double actualCost = itemPage.getPrice();
        String actualTitle = itemPage.getItemName().toLowerCase();

        sf.assertEquals(actualCost, supposedCost, "wrong cost");
        sf.assertTrue(actualTitle.contains(supposedTitle), "wrong title");
        sf.assertAll();
    }
}
