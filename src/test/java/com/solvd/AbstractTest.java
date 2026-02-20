package com.solvd;

import com.solvd.util.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.net.URL;
import java.time.Duration;

public abstract class AbstractTest {

    protected final Logger log = LogManager.getLogger(getClass());
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    protected WebDriverWait wait;
    protected SoftAssert sf;

    @BeforeMethod()
    @Parameters("browser")
    public void setup( String browser) {
        log.info("setup start");
        driverThreadLocal.set(generateDriver(browser));
        log.info("driver for {} initialised", browser);
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Long.parseLong(Config.get("WAIT_TIME"))));
        sf = new SoftAssert();

        getDriver().get(Config.get("URL"));

        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("framelive")));
            log.info("frame switch successful");
        } catch (Exception e) {
            log.warn("'framelive' not found");
        }

        log.info("setup end");
    }

    @AfterMethod()
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

    private WebDriver generateDriver(String driverName) {

        if (driverName == null) {
            throw new RuntimeException("driver is missing");
        }

        try {
            URL gridUrl = new URL(Config.get("GRID_URL"));

            switch (driverName.toLowerCase()) {

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    return new RemoteWebDriver(gridUrl, firefoxOptions);

                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    return new RemoteWebDriver(gridUrl, chromeOptions);

                default:
                    throw new RuntimeException("uknnown driver  " + driverName);
            }

        } catch (Exception e) {
            throw new WebDriverException("didnt created browser", e);
        }
    }

}
