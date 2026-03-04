package com.solvd.carinaweb.page.desktop;

import com.solvd.carinaweb.page.common.ProductDetailsPage;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = ProductDetailsPage.class)
public class ProductDetailsPageDesktop extends ProductDetailsPage {
    public ProductDetailsPageDesktop(WebDriver driver) {
        super(driver);
    }
}