package com.solvd.carinaweb.component;

import com.solvd.carinaweb.page.CheckoutPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ItemCartComponent extends BaseComponent {


    public ItemCartComponent(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage clickProceedToCheckout() {

        By checkoutLocator = By.cssSelector(".modal-body .cart-content-btn .btn-primary");
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutLocator));
        getDriver().findElement(checkoutLocator).click();
        return new CheckoutPage(getDriver());
    }

    public void clickContinueShopping() {
        By continueLocator = By.cssSelector(".modal-body .cart-content-btn .btn-secondary");
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueLocator));
        getDriver().findElement(continueLocator).click();
    }
}
