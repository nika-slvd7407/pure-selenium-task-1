package com.solvd.carinaweb.page.android;

import com.solvd.carinaweb.page.common.SearchPage;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = SearchPage.class)

public class SearchPageAndroid extends SearchPage {

    public SearchPageAndroid(WebDriver driver) {
        super(driver);
    }
}
