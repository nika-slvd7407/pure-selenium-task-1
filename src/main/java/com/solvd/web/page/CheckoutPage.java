package com.solvd.web.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CheckoutPage extends AbstractPage {

    @FindBy(css = "div.product-line-info a.label")
    private List<WebElement> itemsInCheckout;

    @FindBy(css = "span.js-subtotal")
    private WebElement itemAmount;

    @FindBy(css = "button.bootstrap-touchspin-up")
    private WebElement incrementButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOfAllElements(itemsInCheckout));
    }

    public List<String> getItemList() {
        List<String> itemList = new ArrayList<>();

        for (WebElement webElement : itemsInCheckout) {
            itemList.add(getText(webElement).toLowerCase());
        }
        return itemList;
    }

    public int getItemAmount() {
        String rawAmount = getText(itemAmount);
        return Integer.valueOf(rawAmount.split(" ")[0]);
    }

    public void clickIncrementButton() {
        click(incrementButton);
    }

}
