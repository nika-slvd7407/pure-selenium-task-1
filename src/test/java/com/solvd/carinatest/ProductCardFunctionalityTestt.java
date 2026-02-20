package com.solvd.carinatest;


import com.solvd.carinaweb.page.BasePage;
import com.solvd.carinaweb.page.ItemPage;
import com.solvd.carinaweb.page.MainPage;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.utils.R;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class ProductCardFunctionalityTestt extends AbstractTest {

    protected SoftAssert sf;
    private static final int WAIT_TIME = R.CONFIG.getInt("WAIT_TIME");

    @BeforeMethod
    public void setUp() {
        sf = new SoftAssert();
    }

    @Test(description = "assert that product card from the main page works ")
    public void testProductCard() {
        BasePage basePage = new BasePage(getDriver());
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
