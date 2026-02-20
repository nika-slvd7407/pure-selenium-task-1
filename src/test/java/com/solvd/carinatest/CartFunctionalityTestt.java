package com.solvd.carinatest;


import com.solvd.carinaweb.page.BasePage;
import com.solvd.carinaweb.page.CheckoutPage;
import com.solvd.carinaweb.page.ItemPage;
import com.solvd.carinaweb.page.MainPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CartFunctionalityTestt extends BaseTest {

    @Test(description = "assert that after pressing add to cart button item added into cart")
    public void testCartFunction() {
        BasePage basePage = new BasePage(getDriver());
        basePage.open();
        MainPage mainPage = basePage.switchToShopFrame();
        ItemPage itemPage = mainPage.clickRandomItem();

        String itemName = itemPage.getItemName().toLowerCase().trim();
        log.info("{} - added item", itemName);

        itemPage.addToCart();
        CheckoutPage checkoutPage = itemPage.clickProceedToCheckout();
        List<String> checkoutItemList = checkoutPage.getItemList();
        log.info("Checkout items: {}", checkoutItemList);
        log.info("Looking for item: '{}'", itemName);

        boolean itemFound = false;
        for (String checkoutItem : checkoutItemList) {
            if (checkoutItem.contains(itemName) || itemName.contains(checkoutItem.replace("...", ""))) {
                itemFound = true;
                break;
            }
        }

        sf.assertTrue(itemFound, "checkout doesn't contains added item. Looking for: '" + itemName + "' in: " + checkoutItemList);
        sf.assertAll();
    }

    @Test(description = "assert that after adding item into cart incrementation function works")
    public void testCartQuantityUpdate() {
        BasePage basePage = new BasePage(getDriver());
        basePage.open();
        MainPage mainPage = basePage.switchToShopFrame();
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
