package com.solvd.carinaweb.page.desktop;

import com.solvd.carinaweb.page.common.SearchPage;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = SearchPage.class)

public class SearchPageDesktop extends SearchPage {

    public SearchPageDesktop(WebDriver driver) {
        super(driver);
    }
}
