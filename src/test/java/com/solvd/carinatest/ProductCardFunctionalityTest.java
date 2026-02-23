package com.solvd.carinatest;


import com.solvd.carinaweb.page.common.BasePage;
import com.solvd.carinaweb.page.common.ItemPage;
import com.solvd.carinaweb.page.common.MainPage;
import com.zebrunner.carina.utils.R;
import org.testng.annotations.Test;

import java.util.Random;

public class ProductCardFunctionalityTest extends BaseTest {

    private static final int WAIT_TIME = R.CONFIG.getInt("WAIT_TIME");

    @Test(description = "assert that product card from the main page works ")
    public void testProductCard() {
        BasePage basePage = initPage(getDriver(), BasePage.class);
        basePage.open();
        MainPage mainPage = basePage.switchToShopFrame();
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
