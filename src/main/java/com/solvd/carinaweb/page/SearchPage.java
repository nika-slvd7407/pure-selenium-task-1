package com.solvd.carinaweb.page;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BasePage {

    @FindBy(css = "div.js-product.product h2 a")
    private List<ExtendedWebElement> items;

    @FindBy(id = "wrapper")
    private ExtendedWebElement resultContainer;

    private By resultContainerLocator = By.id("wrapper");

    public SearchPage(WebDriver driver) {
        super(driver);
   //   switchToFramelive();
        waitUntilVisibilityOf(resultContainerLocator);

    }

    public List<String> getSearchedItems() {
        wait.until(d -> !items.isEmpty());

        List<String> titles = new ArrayList<>();

        for (ExtendedWebElement item : items) {
            titles.add(item.getText().toLowerCase().trim());
        }

        return titles;
    }

    public ItemPage openItemByIndex(int index) {
        wait.until(d -> !items.isEmpty());
        items.get(index).click();
        return new ItemPage(getDriver());
    }

    public int getItemAmount() {
        wait.until(d -> !items.isEmpty());
        return items.size();
    }
}