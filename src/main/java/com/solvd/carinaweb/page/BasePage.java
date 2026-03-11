package com.solvd.carinaweb.page;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class BasePage extends AbstractPage {

    private final static String FRAME_LIVE_ID = "framelive";
    protected static final Logger LOGGER = LogManager.getLogger(BasePage.class);

    @FindBy(id = "header")
    private ExtendedWebElement header;

    public BasePage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(header);
    }

    public MainPage switchToShopFrame() {
        switchToFrame(FRAME_LIVE_ID);
        LOGGER.info("frame switched");
        return new MainPage(getDriver());
    }

    protected void switchToFrame(String frameId) {
        getDriver().switchTo().frame(frameId);
    }

    protected void switchToDefault() {
        getDriver().switchTo().defaultContent();
    }

}

