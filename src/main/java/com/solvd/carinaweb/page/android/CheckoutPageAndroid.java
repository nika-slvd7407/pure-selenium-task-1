package com.solvd.carinaweb.page.android;

import com.solvd.carinaweb.page.common.CheckoutPage;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CheckoutPage.class)
public class CheckoutPageAndroid extends CheckoutPage {

    public CheckoutPageAndroid(WebDriver driver) {
        super(driver);
    }
}
