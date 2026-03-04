package com.solvd.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverFactory {

    public static WebDriver createDriver(String driverName) {

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