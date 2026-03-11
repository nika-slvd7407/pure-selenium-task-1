package com.solvd.carinaweb.page;

import com.solvd.util.WaitUtil;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BasePage {

    @FindBy(css = "div.js-product.product h2 a")
    private List<ExtendedWebElement> items;

    @FindBy(id = "products")
    private ExtendedWebElement resultContainer;

    public SearchPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(resultContainer);
    }

    public List<String> getSearchedItems() {
        List<String> titles = new ArrayList<>();

        for (ExtendedWebElement item : getItems()) {
            titles.add(item.getText().toLowerCase().trim());
        }

        return titles;
    }

    public ProductDetailsPage openItemByIndex(int index) {
        getItems().get(index).click();
        return new ProductDetailsPage(getDriver());
    }

    public int getItemQuantity() {
        return items.size();
    }

    public List<ExtendedWebElement> getItems() {
        WaitUtil.waitForElementsListNotEmpty(items, 15, getDriver());
        return items;
    }
}