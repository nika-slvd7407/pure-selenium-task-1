package com.solvd;

import com.solvd.web.page.CheckoutPage;
import com.solvd.web.page.ItemPage;
import com.solvd.web.page.MainPage;
import org.testng.annotations.Test;

import java.util.List;

public class CartFunctionalityTest extends AbstractTest {

    private static final int AMOUNT_OF_CLICKS = 5;

    @Test(description = "assert that after pressing add to cart button item added into cart")
    public void testCartFunction() {
        MainPage mainPage = new MainPage(driver);
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
        MainPage mainPage = new MainPage(driver);
        ItemPage itemPage = mainPage.clickRandomItem();

        itemPage.addToCart();
        CheckoutPage checkoutPage = itemPage.clickProceedToCheckout();

        for (int i = 0; i < AMOUNT_OF_CLICKS; i++) {
            checkoutPage.clickIncrementButton();
        }
        int expectedAmount = 1 + AMOUNT_OF_CLICKS;
        wait.until(driver -> checkoutPage.getItemAmount() == expectedAmount);
        int itemAmount = checkoutPage.getItemAmount();
        sf.assertEquals(itemAmount, expectedAmount);

        sf.assertAll();
    }
}
