package com.solvd.carinaweb.page;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
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
    }

    public String getItemName() {
        return itemDescription.getText();
    }


    public void addToCart() {
        addToCartButton.click();
    }

    public Double getPrice() {
        String rawPrice = itemCost.getText();
        return Double.valueOf(rawPrice.substring(1));
    }

    public boolean checkCategory(String category) {
        List<WebElement> elements = driver.findElements(
                By.xpath("//span[contains(text(), '" + category + "')]")
        );
        return !elements.isEmpty();
    }

    public void back() {
        getDriver().navigate().back();
    }
}