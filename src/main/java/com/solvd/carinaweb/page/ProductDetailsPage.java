package com.solvd.carinaweb.page;

import com.solvd.carinaweb.component.ItemCartComponent;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.util.List;

public class ProductDetailsPage extends BasePage {

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

        waitUntilListsArePopulated(breadcrumbItems);
    }

    public String getItemName() {
        return itemName.getText();
    }

    public void addProductToCart() {
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
                getGetBreadcrumbItems();

        return items.stream()
                .anyMatch(el -> el.getText().trim().equals(category));
    }

    public String getCategory() {
        List<ExtendedWebElement> items = getGetBreadcrumbItems();

        if (items.isEmpty()) {
            return "no category";
        }

        for (ExtendedWebElement item : items) {
            log.info(item.getText().trim());
        }

        return items.get(items.size() - 1).getText().trim();
    }

    public void navigateBack() {
        getDriver().navigate().back();
    }

    public CheckoutPage clickProceedToCheckout() {
        return getItemCartComponent().clickProceedToCheckout();
    }

    public List<ExtendedWebElement> getGetBreadcrumbItems() {
        return breadcrumbItems;
    }
}