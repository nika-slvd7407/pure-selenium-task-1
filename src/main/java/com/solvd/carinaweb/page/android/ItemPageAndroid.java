package com.solvd.carinaweb.page.android;

import com.solvd.carinaweb.page.common.ItemPage;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ItemPage.class)
public class ItemPageAndroid extends com.solvd.carinaweb.page.common.ItemPage {

    public ItemPageAndroid(WebDriver driver) {
        super(driver);
    }
}