package com.solvd.carinaweb.page.ios;

import com.solvd.carinaweb.page.common.CheckoutPage;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CheckoutPage.class)
public class CheckoutPageIos extends CheckoutPage {

    public CheckoutPageIos(WebDriver driver) {
        super(driver);
    }
}
