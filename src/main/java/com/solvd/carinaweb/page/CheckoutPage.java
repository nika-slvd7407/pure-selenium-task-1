package com.solvd.carinaweb.page;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CheckoutPage extends AbstractPage {

    @FindBy(css = "div.product-line-info a.label")
    private List<ExtendedWebElement> itemsInCheckout;

    @FindBy(css = "span.js-subtotal")
    private ExtendedWebElement itemAmount;

    @FindBy(css = "button.bootstrap-touchspin-up")
    private ExtendedWebElement incrementButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getItemList() {
        List<String> itemList = new ArrayList<>();

        for (ExtendedWebElement webElement : itemsInCheckout) {
            itemList.add(webElement.getText().toLowerCase());
        }
        return itemList;
    }

    public int getItemAmount() {
        String rawAmount = itemAmount.getText();
        return Integer.valueOf(rawAmount.split(" ")[0]);
    }

    public void clickIncrementButton() {
        incrementButton.getText();
    }
}
