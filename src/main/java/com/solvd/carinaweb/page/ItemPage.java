package com.solvd.carinaweb.page;

import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ItemPage extends AbstractPage {

    private static final int WAIT_TIME = R.CONFIG.getInt("WAIT_TIME");


    @FindBy(css = "button.add-to-cart")
    private ExtendedWebElement addToCartButton;

    @FindBy(css = "h1.h1")
    private ExtendedWebElement itemDescription;

    @FindBy(css = "span.current-price-value")
    private ExtendedWebElement itemCost;

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
        List<WebElement> elements = getDriver().findElements(
                By.xpath("//span[contains(text(), '" + category + "')]")
        );
        return !elements.isEmpty();
    }

    public void back() {
        getDriver().navigate().back();
    }

    public CheckoutPage clickProceedToCheckout() {

        By checkoutLocator = By.xpath("//a[contains(@class, 'btn-primary') and .//i[contains(@class, 'material-icons')]]");

        WebElement checkoutButton = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIME)).until(
                ExpectedConditions.elementToBeClickable(checkoutLocator)
        );

        checkoutButton.click();
        return new CheckoutPage(getDriver());
    }
}