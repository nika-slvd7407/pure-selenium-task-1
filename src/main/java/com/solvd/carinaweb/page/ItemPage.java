package com.solvd.carinaweb.page;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ItemPage extends BasePage {

    @FindBy(css = "button.add-to-cart")
    private ExtendedWebElement addToCartButton;

    @FindBy(css = "h1.h1")
    private ExtendedWebElement itemDescription;

    @FindBy(css = "span.current-price-value")
    private ExtendedWebElement itemPrice;

    @FindBy(id = "content")
    private ExtendedWebElement pageContainer;

    public ItemPage(WebDriver driver) {
        super(driver);
        switchToFramelive();
        wait.until(ExpectedConditions.visibilityOf(pageContainer.getElement()));
    }

    public String getItemName() {
        return itemDescription.getText();
    }

    public void addToCart() {
        addToCartButton.click();
    }

    public Double getItemPrice() {
        String rawPrice = itemPrice.getText();
        return Double.parseDouble(rawPrice.replaceAll("[^0-9.]", ""));
    }

    public CheckoutPage clickProceedToCheckout() {

        By checkoutLocator = By.cssSelector(".modal-body .cart-content-btn .btn-primary");

        wait.until(d -> d.findElement(checkoutLocator).isDisplayed());

        getDriver().findElement(checkoutLocator).click();

        return new CheckoutPage(getDriver());
    }

    public void clickContinueShopping() {

        By continueLocator = By.cssSelector(".modal-body .cart-content-btn .btn-secondary");

        wait.until(d -> d.findElement(continueLocator).isDisplayed());

        getDriver().findElement(continueLocator).click();
    }

    public boolean checkCategory(String category) {

        List<ExtendedWebElement> items =
                findExtendedWebElements(By.cssSelector("nav.breadcrumb a span"));

        return items.stream()
                .anyMatch(el -> el.getText().trim().equals(category));
    }

    public String getCategory() {
        List<ExtendedWebElement> items =
                findExtendedWebElements(By.cssSelector("nav.breadcrumb a span"));
        if (items.isEmpty()) {
            return "no category";
        }

        return items.get(items.size() - 1).getText().trim();
    }

    public void back() {
        getDriver().navigate().back();
    }
}