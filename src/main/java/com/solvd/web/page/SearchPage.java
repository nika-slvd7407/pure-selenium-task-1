package com.solvd.web.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchPage {

    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(MainPage.class);

    @FindBy(css = "div.js-product.product h2 a")
    private List<WebElement> items;

    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public List<String> getSearchedItems() {
        List<String> titles = new ArrayList<>();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElements(items));

        for (WebElement item : items) {
            titles.add(item.getText().toLowerCase());
            log.info("found {} items on the search page", titles.size());
        }

        return titles;
    }

}
