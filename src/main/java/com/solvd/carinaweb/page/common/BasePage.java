package com.solvd.carinaweb.page.common;

import com.solvd.carinaweb.page.desktop.BasePageDesktop;
import com.solvd.carinaweb.page.desktop.MainPageDesktop;
import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage extends AbstractPage {
    private static final Logger log = LogManager.getLogger(BasePageDesktop.class);
    private static final int WAIT_TIME = R.CONFIG.getInt("WAIT_TIME");

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public MainPage switchToShopFrame() {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIME)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("framelive")));
            log.info("frame switched");
            return initPage(getDriver(), MainPage.class);
        } catch (Exception e) {
            log.error("failed to switch frame {}", e);
        }
        return null;
    }
}
