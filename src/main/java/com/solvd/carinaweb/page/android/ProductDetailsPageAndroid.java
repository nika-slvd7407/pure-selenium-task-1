package com.solvd.carinaweb.page.android;

import com.solvd.carinaweb.page.common.ProductDetailsPage;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductDetailsPage.class)
public class ProductDetailsPageAndroid extends ProductDetailsPage {

    public ProductDetailsPageAndroid(WebDriver driver) {
        super(driver);
    }
}