package com.solvd.carinaweb.page.common;

import com.solvd.carinaweb.uielement.common.ItemCartComponent;
import com.solvd.util.PriceUtil;
import com.solvd.util.WaitUtil;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public abstract class ProductDetailsPage extends BasePage {

    @FindBy(css = "button.add-to-cart")
    private ExtendedWebElement addToCartButton;

    @FindBy(css = "nav.breadcrumb a span")
    private List<ExtendedWebElement> breadcrumbItems;

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
        return itemName.getText().toLowerCase();
    }

    public void addProductToCart() {
        addToCartButton.waitUntil(ExpectedConditions.elementToBeClickable(addToCartButton), 15);
        addToCartButton.click();
    }

    public BigDecimal getItemPrice() {
        String rawPrice = itemPrice.getText();
        return PriceUtil.parsePrice(rawPrice);
    }

    public ItemCartComponent getItemCartComponent() {
        return itemCartComponent;
    }

    public Optional<String> getCategory() {
        List<ExtendedWebElement> items = getBreadcrumbItems();

        Optional<String> category = Optional.empty();

        if (items.isEmpty()) {
            return category;
        }

        return category = Optional.of(items.get(items.size() - 1).getText().trim());
    }

    public void navigateBack() {
        getDriver().navigate().back();
    }

    public CheckoutPage clickProceedToCheckout() {
        return getItemCartComponent().clickProceedToCheckout();
    }

    public List<ExtendedWebElement> getBreadcrumbItems() {
        WaitUtil.waitForElementsListNotEmpty(breadcrumbItems, getDriver());
        return breadcrumbItems;
    }
}