package com.solvd.carinaweb.page.common;

import com.solvd.carinaweb.page.desktop.BasePageDesktop;
import com.solvd.carinaweb.page.desktop.MainPageDesktop;
import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage extends AbstractPage {

    protected static final int WAIT_TIME = R.CONFIG.getInt("WAIT_TIME");
    private final static String frameId = "framelive";
    protected final Logger log = LogManager.getLogger(getClass());

    @FindBy(id = "header")
    private ExtendedWebElement header;

    public BasePage(WebDriver driver) {
        super(driver);

    }

    public MainPage switchToShopFrame() {
        switchToFramelive();
        return initPage(getDriver(), MainPage.class);
    }

    protected void switchToFrame(String frameId) {
        getDriver().switchTo().frame(frameId);
    }

    protected void switchToFramelive() {
        getDriver().switchTo().frame(frameId);
    }

    protected void switchToDefault() {
        getDriver().switchTo().defaultContent();
    }

    protected void waitUntilVisibilityOf(By locator) {
        findExtendedWebElement(locator)
                .assertElementPresent();
    }

    protected void waitUntilClickableOf(ExtendedWebElement webElement) {
        webElement.isClickable();
    }

    // i need this method cos the lists load late even with uiLoadingMarker in page constructors,
    // the page is initialized but the lists are empty :D
    protected void waitUntilListsArePopulated(List<ExtendedWebElement>... lists) {
        for (List<ExtendedWebElement> list : lists) {
            waitUntil(driver -> !list.isEmpty(), 10);
        }
    }
}
