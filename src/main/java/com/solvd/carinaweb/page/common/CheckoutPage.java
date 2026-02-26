package com.solvd.carinaweb.page.common;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public abstract class CheckoutPage extends BasePage {

    private static final Logger log = LogManager.getLogger(CheckoutPage.class);

    @FindBy(css = "div.product-line-info a.label")
    private List<ExtendedWebElement> itemsInCheckout;

    @FindBy(css = "span.js-subtotal")
    private ExtendedWebElement itemAmount;

    @FindBy(css = "button.bootstrap-touchspin-up")
    private ExtendedWebElement incrementButton;

    private By itemListLocator = By.id("main");

    public CheckoutPage(WebDriver driver) {
        super(driver);
        waitUntilVisibilityOf(itemListLocator);
    }

    public List<String> getItemList() {

        wait.until(d -> !itemsInCheckout.isEmpty());

        List<String> itemList = new ArrayList<>();

        for (ExtendedWebElement element : itemsInCheckout) {
            String text = element.getText().toLowerCase().trim();
            itemList.add(text);
            log.info("Item found in checkout: {}", text);
        }

        return itemList;
    }

    public int getItemAmount() {
        String rawAmount = itemAmount.getText();
        return Integer.parseInt(rawAmount.split(" ")[0]);
    }

    public void clickIncrementButton() {
        waitUntilClickableOf(incrementButton);
        incrementButton.click();
    }

    public void incrementAmount(int clickTime) {
        for (int i = 0; i < clickTime; i++) {
            clickIncrementButton();
        }
    }

    public void waitUntilAmountUpdated(int expectedAmount) {
        wait.until(d -> getItemAmount() == expectedAmount);
    }
}