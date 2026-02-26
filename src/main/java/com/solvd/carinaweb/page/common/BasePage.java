package com.solvd.carinaweb.page.common;

import com.solvd.carinaweb.page.desktop.BasePageDesktop;
import com.solvd.carinaweb.page.desktop.MainPageDesktop;
import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage extends AbstractPage {

    protected static final Logger log = LogManager.getLogger(BasePage.class);
    protected static final int WAIT_TIME = R.CONFIG.getInt("WAIT_TIME");
    protected WebDriverWait wait;
    private final static String frameId = "framelive";

    public BasePage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(WAIT_TIME)
        );
    }

    public MainPage switchToShopFrame() {
        switchToFramelive();
        return initPage(getDriver(), MainPage.class);
    }

    protected void switchToFrame(String frameId) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(frameId)));
    }

    protected void switchToFramelive() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(frameId)));
    }

    protected void switchToDefault() {
        getDriver().switchTo().defaultContent();
    }

    protected void waitUntilVisibilityOf(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitUntilClickableOf(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }
}
