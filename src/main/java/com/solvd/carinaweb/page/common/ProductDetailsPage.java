package com.solvd.carinaweb.page.common;

import com.solvd.carinaweb.uielement.common.ItemCartComponent;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;
import java.util.List;

public abstract class ProductDetailsPage extends BasePage {

    @FindBy(css = "button.add-to-cart")
    private ExtendedWebElement addToCartButton;

    @FindBy(css = "h1.h1")
    private ExtendedWebElement itemName;

    @FindBy(css = "span.current-price-value")
    private ExtendedWebElement itemPrice;

    @FindBy(css = "add-to-cart-or-refresh")
    private ExtendedWebElement contentWrapper;

    @FindBy(id = "blockcart-modal")
    private ItemCartComponent itemCartComponent;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(contentWrapper);

    }

    public String getItemName() {
        return itemName.getText();
    }

    public void addToCart() {
        waitUntilClickableOf(addToCartButton);
        addToCartButton.click();
        isPageOpened();
    }

    public BigDecimal getItemPrice() {
        String rawPrice = itemPrice.getText();
        return BigDecimal.valueOf(Double.parseDouble(rawPrice.replaceAll("[^0-9.]", "")));
    }

    public ItemCartComponent getItemCartComponent() {
        return itemCartComponent;
    }

    public boolean isCategory(String category) {

        List<ExtendedWebElement> items =
                findExtendedWebElements(By.cssSelector("nav.breadcrumb a span"));

        return items.stream()
                .anyMatch(el -> el.getText().trim().equals(category));
    }

    public String getCategory() {
        List<ExtendedWebElement> items = findExtendedWebElements(By.cssSelector("nav.breadcrumb a span"));

        if (items.isEmpty()) {
            return "no category";
        }
        return items.get(items.size() - 1).getText().trim();
    }

    public void navigateBack() {
        getDriver().navigate().back();
    }

    public CheckoutPage clickProceedToCheckout() {
        return getItemCartComponent().clickProceedToCheckout();
    }
}