package com.solvd.carinaweb.page.common;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public abstract class SearchPage extends BasePage {

    @FindBy(css = "div.js-product.product h2 a")
    private List<ExtendedWebElement> items;

    @FindBy(id = "products")
    private ExtendedWebElement resultContainer;

    public SearchPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(resultContainer);
        isPageOpened();
    }

    public List<String> getSearchedItems() {
        items.get(0).assertElementPresent();

        List<String> titles = new ArrayList<>();

        for (ExtendedWebElement item : items) {
            titles.add(item.getText().toLowerCase().trim());
        }

        return titles;
    }

    public ProductDetailsPage openItemByIndex(int index) {
        items.get(0).assertElementPresent();
        items.get(index).click();
       return initPage(getDriver(), ProductDetailsPage.class);
    }

    public int getItemAmount() {
        items.get(0).assertElementPresent();
        return items.size();
    }
}