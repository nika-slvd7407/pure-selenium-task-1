package com.solvd;

import com.solvd.web.page.CheckoutPage;
import com.solvd.web.page.ItemPage;
import com.solvd.web.page.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CartFunctionalityTest extends AbstractTest {

    private static final int AMOUNT_OF_CLICKS = 5;

    @Test(description = "assert that after pressing add to cart button item added into cart")
    public void testAddItemToCart() {
        MainPage mainPage = new MainPage(getDriver());
        ItemPage itemPage = mainPage.clickItem(0);

        String itemName = itemPage.getItemName().toLowerCase();
        itemPage.addToCart();

        CheckoutPage checkoutPage = itemPage.clickProceedToCheckout();
        List<String> checkoutItemList = checkoutPage.getItemList();
        Assert.assertTrue(checkoutItemList.contains(itemName), "checkout doesn't contains added item");
    }

    @Test(description = "assert that after adding item into cart incrementation function works")
    public void testCartQuantityUpdate() {
        MainPage mainPage = new MainPage(getDriver());
        ItemPage itemPage = mainPage.clickItem(0);

        itemPage.addToCart();
        CheckoutPage checkoutPage = itemPage.clickProceedToCheckout();

        checkoutPage.incrementAmount(AMOUNT_OF_CLICKS);
        int expectedAmount = 1 + AMOUNT_OF_CLICKS;
        checkoutPage.waitUntilAmountUpdated(expectedAmount);

        int itemAmount = checkoutPage.getItemAmount();
        Assert.assertEquals(itemAmount, expectedAmount);
    }
}
