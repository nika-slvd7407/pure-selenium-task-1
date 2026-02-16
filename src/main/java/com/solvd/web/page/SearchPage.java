package com.solvd.web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AbstractPage {

    @FindBy(css = "div.js-product.product h2 a")
    private List<WebElement> items;

    public SearchPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOfAllElements(items));
    }

    public List<String> getSearchedItems() {
        List<String> titles = new ArrayList<>();
        for (WebElement item : items) {
            titles.add(getText(item).toLowerCase());
        }
        log.info("found {} items on the search page", titles.size());


        return titles;
    }

    public ItemPage openItemByIndex(int index){
        click(items.get(index));
        return new ItemPage(driver);
    }

    public int getItemAmount(){
        return items.size();
    }




}
