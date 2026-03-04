package com.solvd.carinaweb.page;

import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class BasePage extends AbstractPage {

    protected static final int WAIT_TIME = R.CONFIG.getInt("WAIT_TIME");
    private final static String frameId = "framelive";
    protected final Logger log = LogManager.getLogger(getClass());

    @FindBy(id = "header")
    private ExtendedWebElement header;

    public BasePage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(header);
        isPageOpened();
    }

    public MainPage switchToShopFrame() {
        switchToFramelive();
        log.info("frame switched");
        return new MainPage(getDriver());
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
        findExtendedWebElement(locator, WAIT_TIME)
                .assertElementPresent(WAIT_TIME);
    }

    protected void waitUntilClickableOf(ExtendedWebElement webElement) {
        webElement.isClickable(WAIT_TIME);
    }
}

