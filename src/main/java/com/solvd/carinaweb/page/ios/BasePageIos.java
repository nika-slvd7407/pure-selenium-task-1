package com.solvd.carinaweb.page.ios;

import com.solvd.carinaweb.page.common.BasePage;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = BasePage.class)
public class BasePageIos extends BasePage {

    public BasePageIos(WebDriver driver) {
        super(driver);
    }
}
