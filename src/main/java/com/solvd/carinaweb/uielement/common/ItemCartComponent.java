package com.solvd.carinaweb.uielement.common;


import com.solvd.carinaweb.page.common.CheckoutPage;
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
        return initPage(getDriver(), CheckoutPage.class);
    }

    public void clickContinueShopping() {
        By continueLocator = By.cssSelector(".modal-body .cart-content-btn .btn-secondary");
        WebElement continiueBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(continueLocator));
        continiueBtn.click();
    }
}
