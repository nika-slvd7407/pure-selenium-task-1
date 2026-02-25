package com.solvd.web.page;

import com.solvd.web.component.CartItemComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ItemPage extends AbstractPage {

    @FindBy(css = "button.add-to-cart")
    private WebElement addToCartButton;

    @FindBy(css = "h1.h1")
    private WebElement itemDescription;

    @FindBy(css = "span.current-price-value")
    private WebElement itemPrice;

    private CartItemComponent cartItemComponent = new CartItemComponent(getDriver());

    public ItemPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
    }

    public String getItemName() {
        return getText(itemDescription);
    }

    public CheckoutPage clickProceedToCheckout() {
        cartItemComponent.clickProceedToCheckout();
        return new CheckoutPage(getDriver());
    }

    public void clickContinueShopping() {
        cartItemComponent.clickContinueShopping();
    }

    public void addToCart() {
        click(addToCartButton);
    }

    public Double getItemPrice() {
        String rawPrice = getText(itemPrice);
        return  Double.parseDouble(rawPrice
                .replaceAll("[^0-9.]", ""));
    }

    public boolean checkCategory(String category) {
        List<WebElement> items = getDriver()
                .findElements(By.cssSelector("nav.breadcrumb a span"));

        return items.stream()
                .anyMatch(el -> el.getText().trim().equals(category));
    }

    public String getCategory() {
        List<WebElement> items = getDriver()
                .findElements(By.cssSelector("nav.breadcrumb a span"));

        if (items.isEmpty()) {
            return "no category";
        }

        return items.get(items.size() - 1).getText().trim();
    }

    public void back() {
        getDriver().navigate().back();
    }
}