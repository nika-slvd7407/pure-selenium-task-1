package com.solvd.carinatest;


import com.solvd.carinaweb.page.ItemPage;
import com.solvd.carinaweb.page.MainPage;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.utils.R;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class ProductCardFunctionalityTestt extends AbstractTest {

    protected SoftAssert sf;
    private static final int WAIT_TIME = R.CONFIG.getInt("WAIT_TIME");

    @Test(description = "assert that product card from the main page works ")
    public void testProductCard() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.open();
        mainPage.switchToShopFrame();
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
