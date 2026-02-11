package com.solvd;

import com.solvd.web.page.MainPage;
import com.solvd.web.page.SearchPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class SearchFunctionalityTest {

    private static final Logger log = LogManager.getLogger(SearchFunctionalityTest.class);

    public static final String URL = "https://demo.prestashop.com/#/en/front";
    private static final String ITEM_TO_SEARCH = "Mug The Adventure Begins";
    private WebDriver driver;
    private WebDriverWait wait;
    private SoftAssert sf;

    @BeforeMethod
    public void setup(){
        log.info("setup start");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(URL);
        sf = new SoftAssert();
        log.info("setup end");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            log.info("test finished and browser closed");
        }
    }

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
