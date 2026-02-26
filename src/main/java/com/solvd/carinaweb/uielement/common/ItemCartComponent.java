package com.solvd.carinaweb.uielement.common;

import com.solvd.carinaweb.page.common.CheckoutPage;
import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ItemCartComponent extends AbstractUIObject {

    private WebDriverWait wait;
    private int WAIT_TIME = R.CONFIG.getInt("WAIT_TIME");

    public ItemCartComponent(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(WAIT_TIME)
        );
    }

    public CheckoutPage clickProceedToCheckout() {

        By checkoutLocator = By.cssSelector(".modal-body .cart-content-btn .btn-primary");
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutLocator));
        getDriver().findElement(checkoutLocator).click();
        return initPage(getDriver(), CheckoutPage.class);
    }

    public void clickContinueShopping() {
        By continueLocator = By.cssSelector(".modal-body .cart-content-btn .btn-secondary");
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueLocator));
        getDriver().findElement(continueLocator).click();
    }
}
