package com.solvd.carinaweb.page.common;

import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public abstract class SearchPage extends AbstractPage {

    private static final Logger log = LogManager.getLogger(SearchPage.class);
    private static final int WAIT_TIME = R.CONFIG.getInt("WAIT_TIME");

    @FindBy(css = "div.js-product.product h2 a")
    private List<ExtendedWebElement> items;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getSearchedItems() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIME)).until(
                driver -> !items.isEmpty()
        );

        List<String> titles = new ArrayList<>();
        for (ExtendedWebElement item : items) {
            titles.add(item.getText().toLowerCase());
        }
        log.info("found {} items on the search page", titles.size());
        return titles;
    }

    public ItemPage openItemByIndex(int index) {
        items.get(index).click();
        return initPage(getDriver(), ItemPage.class);
    }

    public int getItemAmount() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIME)).until(
                driver -> !items.isEmpty()
        );
        return items.size();
    }

    public ItemPage openFirstItem() {
        items.stream().findFirst().get().click();

        return initPage(getDriver(), ItemPage.class);
    }
}
