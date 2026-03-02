package com.solvd.carinaweb.component;

import com.solvd.carinaweb.page.CheckoutPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ItemCartComponent extends BaseComponent {


    public ItemCartComponent(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage clickProceedToCheckout() {

        By checkoutLocator = By.cssSelector(".modal-body .cart-content-btn .btn-primary");
        WebElement checkoutBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutLocator));
        checkoutBtn.click();
        return new CheckoutPage(getDriver());
    }

    public void clickContinueShopping() {
        By continueLocator = By.cssSelector(".modal-body .cart-content-btn .btn-secondary");
        WebElement continiueBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(continueLocator));
        continiueBtn.click();
    }
}
