package com.solvd;

import com.solvd.web.page.MainPage;
import com.solvd.web.page.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.List;

public class SearchFunctionalityTest extends AbstractTest {

    private static final String ITEM_TO_SEARCH = "Mug The Adventure Begins";

    @Test(description = "assert that search function is working properly and outputs items")
    public void testSearchFunction() throws InterruptedException {
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("framelive")));
        driver.switchTo().frame(iframe);
        log.info("frame switch");

        MainPage mainPage = new MainPage(driver);
        SearchPage searchPage = mainPage.search(ITEM_TO_SEARCH);

        List<String> searchedItems = searchPage.getSearchedItems();

        sf.assertTrue(!searchedItems.isEmpty(), "error zero items found!");
        searchedItems.forEach(item -> log.info("{} found", item));
        sf.assertAll();
    }
}
