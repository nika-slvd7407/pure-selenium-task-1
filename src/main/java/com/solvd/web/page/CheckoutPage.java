package com.solvd.web.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CheckoutPage extends AbstractPage {

    @FindBy(css = "div.product-line-info a.label")
    private List<WebElement> productListItems;

    @FindBy(css = "span.js-subtotal")
    private WebElement itemAmount;

    @FindBy(css = "button.bootstrap-touchspin-up")
    private WebElement incrementButton;

    @FindBy(id = "content-wrapper")
    private WebElement checkoutContainer;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        switchToFramelive();

        waitForElementVisible(checkoutContainer);
    }

    public List<String> getItemList() {
        List<String> itemList = new ArrayList<>();

        for (WebElement webElement : productListItems) {
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

    public void incrementAmount(int clickTime) {
        for (int i = 0; i < clickTime; i++) {
            clickIncrementButton();
        }
    }

    public void waitUntilAmountUpdated(int expectedAmount) {
        wait.until(driver -> getItemAmount() == expectedAmount);
    }


}
