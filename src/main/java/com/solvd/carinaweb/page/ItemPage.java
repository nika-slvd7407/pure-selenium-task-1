package com.solvd.carinaweb.page;

import com.solvd.carinaweb.component.ItemCartComponent;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ItemPage extends BasePage {

    @FindBy(css = "button.add-to-cart")
    private ExtendedWebElement addToCartButton;

    @FindBy(css = "h1.h1")
    private ExtendedWebElement itemDescription;

    @FindBy(css = "span.current-price-value")
    private ExtendedWebElement itemPrice;

    private By breadcrumbs = By.cssSelector("nav.breadcrumb");

    @FindBy(id = "breadcrumb")
    private ItemCartComponent ItemCartComponent;

    public ItemPage(WebDriver driver) {
        super(driver);
   //     switchToFramelive();
        waitUntilVisibilityOf(breadcrumbs);
    }

    public String getItemName() {
        return itemDescription.getText();
    }

    public void addToCart() {
        waitUntilClickableOf(addToCartButton);
        addToCartButton.click();
       // wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-body")));
    }

    public Double getItemPrice() {
        String rawPrice = itemPrice.getText();
        return Double.parseDouble(rawPrice.replaceAll("[^0-9.]", ""));
    }

    public ItemCartComponent getItemCartComponent() {
        return ItemCartComponent;
    }

    public boolean checkCategory(String category) {

        List<ExtendedWebElement> items =
                findExtendedWebElements(By.cssSelector("nav.breadcrumb a span"));

        return items.stream()
                .anyMatch(el -> el.getText().trim().equals(category));
    }

    public String getCategory() {
        List<ExtendedWebElement> items =
                findExtendedWebElements(By.cssSelector("nav.breadcrumb a span"));
        if (items.isEmpty()) {
            return "no category";
        }
        return items.get(items.size() - 1).getText().trim();
    }

    public void back() {
        getDriver().navigate().back();
    }

    public CheckoutPage clickProceedToCheckout(){
        return getItemCartComponent().clickProceedToCheckout();
    }
}