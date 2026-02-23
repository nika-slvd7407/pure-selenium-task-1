package com.solvd.carinaweb.page.desktop;

import com.solvd.carinaweb.page.common.ItemPage;
import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = ItemPage.class)
public class ItemPageDesktop extends ItemPage {
    public ItemPageDesktop(WebDriver driver) {
        super(driver);
    }
}