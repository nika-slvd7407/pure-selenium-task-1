package com.solvd.carinaweb.page.android;

import com.solvd.carinaweb.page.common.ItemPage;
import com.solvd.util.MobileContextUtils;
import com.zebrunner.carina.utils.android.AndroidService;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;;


@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ItemPage.class)
public class ItemPageAndroid extends ItemPage {

    @FindBy(css = "img.js-qv-product-cover.img-fluid")
    private ExtendedWebElement itemImage;

    public ItemPageAndroid(WebDriver driver) {
        super(driver);
    }

    @Override
    public void downloadPicture() {
        // js-qv-product-cover img-fluid
        itemImage.assertElementPresent();
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(MobileContextUtils.View.NATIVE);

        AndroidService androidService = new AndroidService();

        androidService.longPress(itemImage);
    }
}