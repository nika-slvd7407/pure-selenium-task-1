package com.solvd.carinaweb.page.common;

import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public abstract class CheckoutPage extends AbstractPage {

    private static final Logger log = LogManager.getLogger(CheckoutPage.class);
    private static final int WAIT_TIME = R.CONFIG.getInt("WAIT_TIME");

    @FindBy(css = "div.product-line-info a.label")
    private List<ExtendedWebElement> itemsInCheckout;

    @FindBy(css = "span.js-subtotal")
    private ExtendedWebElement itemAmount;

    @FindBy(xpath = "//i[contains(@class,'touchspin-up')]")
    private ExtendedWebElement incrementButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getItemList() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIME)).until(
                driver -> itemsInCheckout.size() > 0
        );

        List<String> itemList = new ArrayList<>();

        for (ExtendedWebElement webElement : itemsInCheckout) {
            String itemText = webElement.getText().toLowerCase().trim();
            itemList.add(itemText);
            log.info("Item found in checkout: {}", itemText);
        }
        return itemList;
    }

    public int getItemAmount() {
        String rawAmount = itemAmount.getText();
        return Integer.valueOf(rawAmount.split(" ")[0]);
    }

    public void clickIncrementButton() {
        By incrementButtonLocator = By.cssSelector("button.js-increase-product-quantity");

        new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIME))
                .until(driver -> !driver.findElements(incrementButtonLocator).isEmpty());

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIME));
        wait.until(driver -> {
            driver.findElement(incrementButtonLocator).click();
            return true;
        });
    }
}
