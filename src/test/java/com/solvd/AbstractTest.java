package com.solvd;

import com.solvd.util.Config;
import com.solvd.util.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public abstract class AbstractTest {

    protected final Logger log = LogManager.getLogger(getClass());
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    protected SoftAssert sf;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        log.info("setup start");

        driverThreadLocal.set(DriverFactory.createDriver("chrome"));
        WebDriver driver = getDriver();

        sf = new SoftAssert();

        getDriver().get(Config.get("URL"));


        log.info("setup end");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();
                log.info("test finished and browser closed");
            } catch (Exception e) {
                log.error("error closing browser", e);
            }
        }
        driverThreadLocal.remove();
    }

    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }
}
