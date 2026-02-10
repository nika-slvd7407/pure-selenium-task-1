package com.solvd;

import com.solvd.web.page.MainPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.ref.SoftReference;
import java.util.List;

public class CoffeTest {

    private static final Logger log = LogManager.getLogger(CoffeTest.class);
    // list of coffee on the main page
    public static List<String> COFFEE = List.of(
            "espresso",
            "espresso_macchiato",
            "cappuccino",
            "mocha",
            "flat_white",
            "americano",
            "cafe_latte",
            "espresso_con panna",
            "cafe_breve"
    );

    public static String URL = "https://coffee-cart.app/";

    @BeforeClass
    public void beforeClass(){
        log.info("coffee test class started ");
    }

    @Test(description = "assert that main page contains all the essential coffees and that they are all visible")
    public void testMainPageCoffeeVisibility(){
        log.info("testMainPageCoffeeVisibility started!");

        WebDriver driver = new ChromeDriver();
        SoftAssert sf = new SoftAssert();
        driver.get(URL);
        MainPage mainPage = new MainPage(driver);

        sf.assertTrue(mainPage.areAllCoffeesVisible(), "all coffees are not visible!");
        sf.assertTrue(mainPage.getCoffeNameList().containsAll(COFFEE), "there's some coffee's mising!");
        sf.assertAll();

        driver.quit();
        log.info("testMainPageCoffeeVisibility passed!");
    }
}
