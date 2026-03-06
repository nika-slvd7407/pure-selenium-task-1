package com.solvd.carinaweb.page;

import com.solvd.util.PriceUtil;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CheckoutPage extends BasePage {

    private static final Logger log = LogManager.getLogger(CheckoutPage.class);

    @FindBy(css = "div.product-line-info a.label")
    private List<ExtendedWebElement> itemsInCheckout;

    @FindBy(css = "span.js-subtotal")
    private ExtendedWebElement itemAmount;

    @FindBy(css = "button.bootstrap-touchspin-up")
    private ExtendedWebElement incrementButton;

    @FindBy(xpath = "//div[contains(@class, 'cart-grid-body col-lg-8')]")
    private ExtendedWebElement mainWrapper;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(mainWrapper);

        waitUntilListsArePopulated(itemsInCheckout);
    }

    public List<String> getItemList() {

        itemsInCheckout.get(0).assertElementPresent();

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
        return PriceUtil.parseItemAmountText(rawAmount);
    }

    public void clickIncrementButton() {
        waitUntilClickableOf(incrementButton);
        incrementButton.click();
    }

    public void increaseItemQuantity(int clickTime) {
        for (int i = 0; i < clickTime; i++) {
            clickIncrementButton();
        }
    }

    public void waitUntilAmountUpdated(int expectedAmount) {
        itemAmount.waitUntil(driver -> getItemAmount() == expectedAmount, 15);

    }
}