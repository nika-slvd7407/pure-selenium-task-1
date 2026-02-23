package com.solvd.carinaweb.page.desktop;

import com.solvd.carinaweb.page.common.BasePage;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = BasePage.class)
public class BasePageDesktop extends BasePage {

    public BasePageDesktop(WebDriver driver) {
        super(driver);
    }
}
