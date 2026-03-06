package com.solvd.carinaweb.uielement.common;

import com.solvd.carinaweb.page.common.CheckoutPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ItemCartComponent extends BaseComponent {
    @FindBy(css = ".modal-body .cart-content-btn .btn-primary")
    private ExtendedWebElement checkoutBtn;

    @FindBy(css = ".modal-body .cart-content-btn .btn-secondary")
    private ExtendedWebElement continueButton;

    public ItemCartComponent(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage clickProceedToCheckout() {
        checkoutBtn.click();
        return initPage(getDriver(), CheckoutPage.class);
    }

    public void clickContinueShopping() {
        continueButton.click();
    }
}
