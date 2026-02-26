package com.solvd.web.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartItemComponent extends AbstractComponent {

    private final By checkoutButtonLocator =
            By.cssSelector(".modal-body .cart-content-btn .btn-primary");

    private final By continueShoppingLocator =
            By.cssSelector(".modal-body .cart-content-btn .btn-secondary");

    private final By wrapperLocator = By.id("wrapper");

    public CartItemComponent(WebDriver driver, WebElement root) {
        super(root, driver);
    }

    public void clickProceedToCheckout() {
        WebElement checkoutButton =
                wait.until(ExpectedConditions.elementToBeClickable(checkoutButtonLocator));
        click(checkoutButton);
    }

    public void clickContinueShopping() {
        WebElement continueButton =
                wait.until(ExpectedConditions.elementToBeClickable(continueShoppingLocator));
        click(continueButton);
    }

    private void click(WebElement element) {
        waitForElementVisible(element);
        element.click();
    }

    protected WebElement waitForElementVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}