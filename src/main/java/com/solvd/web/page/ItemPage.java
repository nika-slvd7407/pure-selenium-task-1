package com.solvd.web.page;

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
    private WebElement itemCost;

    public ItemPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
    }

    public String getItemName(){
        return getText(itemDescription);
    }

    public CheckoutPage clickProceedToCheckout() {

        By checkoutLocator = By.xpath("//a[contains(@class, 'btn-primary') and .//i[contains(@class, 'material-icons')]]");

        WebElement checkoutButton = wait.until(
                ExpectedConditions.elementToBeClickable(checkoutLocator)
        );

        click(checkoutButton);
        return new CheckoutPage(driver);
    }

    public void addToCart(){
        click(addToCartButton);
    }

    public Double getPrice(){
        String rawPrice = getText(itemCost);
        return Double.valueOf(rawPrice.substring(1));
    }

    public boolean checkCategory(String category) {
        List<WebElement> elements = driver.findElements(
                By.xpath("//span[contains(text(), '" + category + "')]")
        );
        return !elements.isEmpty();
    }

    public void back(){
        driver.navigate().back();
    }
}