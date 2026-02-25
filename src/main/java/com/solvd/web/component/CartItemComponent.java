package com.solvd.web.component;

import com.solvd.web.page.AbstractPage;
import com.solvd.web.page.CheckoutPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartItemComponent extends AbstractPage {

    private final By checkoutButtonLocator = By.cssSelector(".modal-body .cart-content-btn .btn-primary");
    private final By continueShoppingLocator = By.cssSelector(".modal-body .cart-content-btn .btn-secondary");
    private final By wrapperLocator = By.id("wrapper");

    public CartItemComponent(WebDriver driver) {
        super(driver);
        switchToFrameIfPresent();
        waitForWrapper();
    }

    private void switchToFrameIfPresent() {
            getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("framelive"));
    }

    private void waitForWrapper() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(wrapperLocator));
    }

    public CheckoutPage clickProceedToCheckout() {
        switchToFrameIfPresent();
        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(checkoutButtonLocator));
        checkoutButton.click();
        return new CheckoutPage(getDriver());
    }

    public void clickContinueShopping() {
        switchToFrameIfPresent();
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(continueShoppingLocator));
        continueButton.click();
    }
}