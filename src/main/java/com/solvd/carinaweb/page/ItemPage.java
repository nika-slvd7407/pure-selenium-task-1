package com.solvd.carinaweb.page;

import com.solvd.carinaweb.component.ItemCartComponent;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ItemPage extends BasePage {

    @FindBy(css = "button.add-to-cart")
    private ExtendedWebElement addToCartButton;

    @FindBy(css = "h1.h1")
    private ExtendedWebElement itemName;

    @FindBy(css = "span.current-price-value")
    private ExtendedWebElement itemPrice;

    private By content = By.id("add-to-cart-or-refresh");

    @FindBy(id = "breadcrumb")
    private ItemCartComponent ItemCartComponent;

    public ItemPage(WebDriver driver) {
        super(driver);
        //     switchToFramelive();
        waitUntilVisibilityOf(content);
        //pause(3L);
    }

    public String getItemName() {
        return itemName.getText();
    }

    public void addToCart() {
        waitUntilClickableOf(addToCartButton);
        addToCartButton.click();
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("blockcart-modal")));
    }

    public Double getItemPrice() {
        String rawPrice = itemPrice.getText();
        return Double.parseDouble(rawPrice.replaceAll("[^0-9.]", ""));
    }

    public ItemCartComponent getItemCartComponent() {
        return ItemCartComponent;
    }

    public boolean isCategory(String category) {

        List<ExtendedWebElement> items =
                findExtendedWebElements(By.cssSelector("nav.breadcrumb a span"));

        return items.stream()
                .anyMatch(el -> el.getText().trim().equals(category));
    }

    public String getCategory() {
        By breadcrumbItems = By.cssSelector("nav.breadcrumb a span");
        wait.until(ExpectedConditions.visibilityOfAllElements(getDriver().findElements(breadcrumbItems)));
        List<ExtendedWebElement> items =
                findExtendedWebElements(breadcrumbItems);
        if (items.isEmpty()) {
            return "no category";
        }
        for (ExtendedWebElement item : items) {
            log.info(item.getText().trim());
        }
        return items.get(items.size() - 1).getText().trim();
    }

    public void back() {
        getDriver().navigate().back();
    }

    public CheckoutPage clickProceedToCheckout() {
        return getItemCartComponent().clickProceedToCheckout();
    }
}