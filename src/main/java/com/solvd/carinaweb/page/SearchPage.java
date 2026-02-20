package com.solvd.carinaweb.page;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.solvd.carinaweb.page.ItemPage;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AbstractPage {

    private static final Logger log = LogManager.getLogger(SearchPage.class);

    @FindBy(css = "div.js-product.product h2 a")
    private List<ExtendedWebElement> items;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getSearchedItems() {
        List<String> titles = new ArrayList<>();
        for (ExtendedWebElement item : items) {
            titles.add(item.getText().toLowerCase());
        }
        log.info("found {} items on the search page", titles.size());


        return titles;
    }

    public ItemPage openItemByIndex(int index) {
        items.get(index).click();
        return new ItemPage(getDriver());
    }

    public int getItemAmount() {
        return items.size();
    }
}
