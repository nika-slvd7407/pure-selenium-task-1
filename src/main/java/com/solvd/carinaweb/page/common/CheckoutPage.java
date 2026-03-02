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

    private final By itemAmountLocator = By.cssSelector("#cart-subtotal-products span.js-subtotal");
    private final By cartContainerLocator = By.cssSelector("div.cart-overview.js-cart");

    @FindBy(css = "div.product-line-info a.label")
    private List<ExtendedWebElement> itemsInCheckout;

    @FindBy(css = "button.bootstrap-touchspin-up")
    private ExtendedWebElement incrementButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        waitUntilClickableOf(incrementButton);
    }

    public List<String> getItemList() {
    //    wait.until(d -> !itemsInCheckout.isEmpty());

        List<String> itemList = new ArrayList<>();

        for (ExtendedWebElement element : itemsInCheckout) {
            String text = element.getText().toLowerCase().trim();
            itemList.add(text);
            log.info("Item found in checkout: {}", text);
        }

        return itemList;
    }

    public int getItemAmount() {
        String rawAmount = wait.until(driver -> driver.findElement(itemAmountLocator).getText());
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
        wait.until(d -> {
            String text = d.findElement(itemAmountLocator).getText();
            int current = Integer.parseInt(text.split(" ")[0]);
            return current == expectedAmount;
        });
    }
}