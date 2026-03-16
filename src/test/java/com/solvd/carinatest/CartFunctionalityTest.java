package com.solvd.carinatest;

import com.solvd.carinaweb.page.common.BasePage;
import com.solvd.carinaweb.page.common.CheckoutPage;
import com.solvd.carinaweb.page.common.ProductDetailsPage;
import com.solvd.carinaweb.page.common.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CartFunctionalityTest extends BaseTest {

    private static final int AMOUNT_OF_CLICKS = 5;

    public CartFunctionalityTest(String browser) {
        super(browser);
    }

    @Test(description = "assert that after pressing add to cart button item added into cart")
    public void testAddItemToCart() {
        MainPage mainPage = openMainPage();

        ProductDetailsPage productDetailsPage = mainPage.clickItem(0);

        String itemName = productDetailsPage.getItemName();

        productDetailsPage.addProductToCart();
        CheckoutPage checkoutPage = productDetailsPage.clickProceedToCheckout();

        List<String> checkoutItemList = checkoutPage.getItemList();

        Assert.assertTrue(
                checkoutItemList.contains(itemName),
                String.format("Expected checkout to contain item: %s, but actual items are: %s", itemName, checkoutItemList)
        );
    }

    @Test(description = "assert that after adding item into cart incrementation function works")
    public void verifyCartQuantityUpdateAfterIncrement() {
        MainPage mainPage = openMainPage();

        ProductDetailsPage productDetailsPage = mainPage.clickItem(0);

        productDetailsPage.addProductToCart();
        CheckoutPage checkoutPage = productDetailsPage.clickProceedToCheckout();

        checkoutPage.increaseItemQuantity(AMOUNT_OF_CLICKS);

        int expectedAmount = 1 + AMOUNT_OF_CLICKS;
        checkoutPage.waitUntilAmountUpdated(expectedAmount);

        Assert.assertEquals(
                checkoutPage.getItemAmount(),
                expectedAmount
                ,"Item amount in checkout is not updated after incrementing"
        );
    }
}