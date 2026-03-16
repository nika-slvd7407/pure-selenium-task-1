package com.solvd.carinaweb.page.desktop;

import com.solvd.carinaweb.page.common.MainPage;
import com.solvd.carinaweb.page.common.SearchPage;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = MainPage.class)

public class MainPageDesktop extends MainPage {

    public MainPageDesktop(WebDriver driver) {
        super(driver);
    }

    @Override
    public SearchPage selectSubCategory(String mainCategoryName, String subCategoryName) {

        By mainCategory = By.xpath(String.format("//ul[@id='top-menu']//a[contains(@class,'dropdown-item') and contains(normalize-space(),'%s')]", mainCategoryName));
        By subCategory = By.xpath(String.format("//a[contains(@class,'dropdown-submenu') and contains(text(),'%s')]", subCategoryName));

        waitUntilVisibilityOf(mainCategory);
        findExtendedWebElement(mainCategory).hover();

        waitUntilVisibilityOf(subCategory);
        findExtendedWebElement(subCategory).click();

        return initPage(getDriver(), SearchPage.class);
    }
}
