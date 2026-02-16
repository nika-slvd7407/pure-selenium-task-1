package com.solvd;

import com.solvd.util.Config;
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
    protected WebDriverWait wait;
    protected SoftAssert sf;

    @BeforeMethod
    public void setup() {
        log.info("setup start");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driverThreadLocal.set(new ChromeDriver(options));
        WebDriver driver = getDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(Config.get("WAIT_TIME"))));
        sf = new SoftAssert();

        driver.get(Config.get("URL"));

        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("framelive")));
            log.info("frame switch successful");
        } catch (Exception e) {
            log.warn("'framelive' not found");
        }

        log.info("setup end");
    }

    @AfterMethod
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
