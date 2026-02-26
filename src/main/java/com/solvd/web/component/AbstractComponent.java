package com.solvd.web.component;

import com.solvd.util.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractComponent {

    protected WebDriverWait wait;
    protected WebDriver driver;
    protected WebElement context;

    public AbstractComponent(WebElement root, WebDriver driver) {
        this.context = root;
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(Long.parseLong(Config.get("WAIT_TIME")))
        );
    }

    protected WebDriver getDriver() {
        return driver;
    }
}