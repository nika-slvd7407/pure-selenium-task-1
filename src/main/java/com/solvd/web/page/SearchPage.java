package com.solvd.web.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class SearchPage extends AbstractPage {

    @FindBy(css = "div.js-product.product h2 a")
    private List<WebElement> items;

    @FindBy(id = "wrapper")
    private WebElement resultContainer;

    public SearchPage(WebDriver driver) {
        super(driver);
        switchToFramelive();
        waitForElementVisible(resultContainer);
    }

    public List<String> getSearchedItems() {
        return getItems().stream()
                .map(e -> getText(e).toLowerCase())
                .collect(Collectors.toList());
    }

    public ItemPage openItemByIndex(int index) {
        List<WebElement> elements = getItems();

        if (index >= elements.size()) {
            throw new IllegalArgumentException(
                    "Index " + index + " is out of bounds. Total items: " + elements.size()
            );
        }

        click(elements.get(index));
        return new ItemPage(getDriver());
    }


    public int getItemAmount() {
        return items.size();
    }

    private List<WebElement> getItems() {
        return items;
    }

}
