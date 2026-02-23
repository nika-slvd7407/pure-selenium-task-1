package com.solvd.carinaweb.page.android;

import com.solvd.carinaweb.page.common.BasePage;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = BasePage.class)
public class BasePageAndroid extends com.solvd.carinaweb.page.common.BasePage {

    public BasePageAndroid(WebDriver driver) {
        super(driver);
    }
}
