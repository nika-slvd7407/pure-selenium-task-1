package com.solvd;

import com.solvd.util.Config;
import com.solvd.util.DriverManager;
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
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static com.solvd.util.DriverManager.getDriver;

public abstract class AbstractTest {

    protected final Logger log = LogManager.getLogger(getClass());
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected SoftAssert sf;

    @BeforeMethod
    public void setup() {
        log.info("setup start");
        driver = new ChromeDriver();
        DriverManager.setDriver(driver);
        getDriver().manage().window().maximize();
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Long.parseLong(Config.get("WAIT_TIME"))));
        sf = new SoftAssert();
        log.info("setup end");
        getDriver().get(Config.get("URL"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            log.info("test finished and browser closed");
        }
    }
}
