package com.solvd.carinaweb.page;

import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BasePage extends AbstractPage {

    private final static String frameId = "framelive";
    protected final Logger log = LogManager.getLogger(getClass());

    @FindBy(id = "header")
    private ExtendedWebElement header;

    public BasePage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(header);
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

