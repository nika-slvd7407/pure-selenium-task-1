package com.solvd.carinaweb.page.desktop;

import com.solvd.carinaweb.page.common.BasePage;
import com.solvd.carinaweb.page.common.SearchPage;
import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = SearchPage.class)

public class SearchPageDesktop extends SearchPage {

    public SearchPageDesktop(WebDriver driver) {
        super(driver);
    }
}
