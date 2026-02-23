package com.solvd.carinaweb.page.android;

import com.solvd.carinaweb.page.common.MainPage;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = MainPage.class)
public class MainPageAndroid extends com.solvd.carinaweb.page.common.MainPage {

    public MainPageAndroid(WebDriver driver) {
        super(driver);
    }
}
