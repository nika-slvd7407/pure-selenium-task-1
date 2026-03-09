package com.solvd.carinaweb.page.ios;

import com.solvd.carinaweb.page.common.SearchPage;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = SearchPage.class)

public class SearchPageIos extends SearchPage {

    public SearchPageIos(WebDriver driver) {
        super(driver);
    }
}
