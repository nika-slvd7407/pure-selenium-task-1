package com.solvd.web.page;

import com.solvd.util.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public abstract class AbstractPage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final Logger log = LogManager.getLogger(getClass());
    private final static String frameId = "framelive";

    public AbstractPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(Integer.parseInt(Config.get("WAIT_TIME")))
        );

        PageFactory.initElements(driver, this);
    }

    protected void click(WebElement element) {
        waitForElementVisible(element);
        log.debug("{} clicked", element.getTagName());
        element.click();
    }

    protected String getText(WebElement webElement) {
        waitForElementVisible(webElement);
        String text = webElement.getText();
        log.debug("{} got text {}", webElement.getTagName(), text);
        return text;
    }

    protected void submit(WebElement webElement) {
        waitForElementVisible(webElement);
        log.debug("{} submiting", webElement.getTagName());
        webElement.submit();
    }

    protected void sendKeys(WebElement webElement, String keys) {
        waitForElementVisible(webElement);
        log.debug("{} sending", webElement.getTagName());
        webElement.sendKeys(keys);
    }

    protected boolean isVisible(WebElement webElement) {
        waitForElementVisible(webElement);
        log.debug("{} checking if visible", webElement.getTagName());
        return webElement.isDisplayed();
    }

    protected WebElement waitForElementVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void hover(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).perform();
        log.debug("{} hovering over", webElement.getTagName());
    }

    protected void switchToFrame(String frameId) {
        log.info("Switching to frame: {}", frameId);
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(frameId)));
            log.info("frame switch successful");
        } catch (Exception e) {
            log.warn("'framelive' not found");
        }
    }

    protected void switchToFramelive() {
        switchToFrame(frameId);
    }

    protected void switchToDefault() {
        driver.switchTo().defaultContent();
    }

    protected WebDriver getDriver() {
        return driver;
    }

}
