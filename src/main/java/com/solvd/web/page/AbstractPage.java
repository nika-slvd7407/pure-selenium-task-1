package com.solvd.web.page;

import com.solvd.util.Config;
import com.solvd.util.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.solvd.util.DriverManager.getDriver;

public abstract class AbstractPage {

    protected WebDriverWait wait;
    protected final Logger log = LogManager.getLogger(getClass());

    public AbstractPage(WebDriver driver) {
        this.wait = new WebDriverWait(
                getDriver(),
                Duration.ofSeconds(Integer.parseInt(Config.get("WAIT_TIME")))
        );
        PageFactory.initElements(getDriver(), this);
    }

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        log.debug("{} clicked", element.getTagName());
        element.click();
    }

    protected String getText(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        String text = webElement.getText();
        log.debug("{} got text {}", webElement.getTagName(), text);
        return text;
    }

    protected void submit(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        log.debug("{} submiting", webElement.getTagName());
        webElement.submit();
    }

    protected void sendKeys(WebElement webElement, String keys) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        log.debug("{} sending", webElement.getTagName());
        webElement.sendKeys(keys);
    }

    protected boolean isVisible(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        log.debug("{} checking if visible", webElement.getTagName());
        return webElement.isDisplayed();

    }

    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }
}
