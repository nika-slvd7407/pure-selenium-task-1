package com.solvd.carinatest;


import com.solvd.carinaweb.page.CheckoutPage;
import com.solvd.carinaweb.page.ItemPage;
import com.solvd.carinaweb.page.MainPage;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.utils.R;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class CartFunctionalityTestt extends AbstractTest {

    protected final Logger log = LogManager.getLogger(CartFunctionalityTestt.class);
    private static final int AMOUNT_OF_CLICKS = 5;
    protected SoftAssert sf;
    private static final int WAIT_TIME = R.CONFIG.getInt("WAIT_TIME");


    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(String browser) {
        R.CONFIG.put("browser", browser);
        log.info("{} browser selected", browser);
    }

    @Test(description = "assert that after pressing add to cart button item added into cart")
    public void testCartFunction() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.open();
        mainPage.switchToShopFrame();
        ItemPage itemPage = mainPage.clickRandomItem();

        String itemName = itemPage.getItemName().toLowerCase();
        log.info("{} - added item", itemName);

        itemPage.addToCart();
        CheckoutPage checkoutPage = itemPage.clickProceedToCheckout();
        List<String> checkoutItemList = checkoutPage.getItemList();
        sf.assertTrue(checkoutItemList.contains(itemName), "checkout doesn't contains added item");
        sf.assertAll();
    }

    @Test(description = "assert that after adding item into cart incrementation function works")
    public void testCartQuantityUpdate() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.open();
        mainPage.switchToShopFrame();
        ItemPage itemPage = mainPage.clickRandomItem();

        itemPage.addToCart();
        CheckoutPage checkoutPage = itemPage.clickProceedToCheckout();

        for (int i = 0; i < AMOUNT_OF_CLICKS; i++) {
            checkoutPage.clickIncrementButton();
        }
        int expectedAmount = 1 + AMOUNT_OF_CLICKS;
        new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIME)).until(driver -> checkoutPage.getItemAmount() == expectedAmount);
        int itemAmount = checkoutPage.getItemAmount();
        sf.assertEquals(itemAmount, expectedAmount);

        sf.assertAll();
    }
}
