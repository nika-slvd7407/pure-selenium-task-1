package com.solvd.carinaweb.page.android;

import com.solvd.carinaweb.page.common.MainPage;
import com.solvd.carinaweb.page.common.SearchPage;
import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = MainPage.class)
public class MainPageAndroid extends MainPage {

    @FindBy(xpath = "(//a[contains(@class, 'all-product-link')])[1]")
    private ExtendedWebElement allProductsLink;


    public MainPageAndroid(WebDriver driver) {
        super(driver);
    }

    @Override
    public SearchPage selectSubCategory(String mainCategoryName, String subCategoryName) {

        allProductsLink.click();

        By mainCategory = By.xpath(
                "//li[@data-depth='0']/a[normalize-space(text())='" + mainCategoryName + "']" +
                        "/following-sibling::div[contains(@class,'navbar-toggler')]"
        );

        By subCategory = By.xpath(
                "//ul[contains(@class, 'category-sub-menu')]//a[contains(text(), '" + subCategoryName + "')]"
        );

        waitUntilVisibilityOf(mainCategory);
        findExtendedWebElement(mainCategory).click();

        waitUntilVisibilityOf(subCategory);
        findExtendedWebElement(subCategory).click();

         log.info("finished selecting categorry {}", R.CONFIG.get("CATEGORY"));
        return initPage(getDriver(), SearchPage.class);
    }
}