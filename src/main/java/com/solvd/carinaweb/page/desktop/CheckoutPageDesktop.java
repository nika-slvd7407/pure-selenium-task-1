package com.solvd.carinaweb.page.desktop;

import com.solvd.carinaweb.page.common.CheckoutPage;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = CheckoutPage.class)
public class CheckoutPageDesktop extends CheckoutPage {

    public CheckoutPageDesktop(WebDriver driver) {
        super(driver);
    }
}
