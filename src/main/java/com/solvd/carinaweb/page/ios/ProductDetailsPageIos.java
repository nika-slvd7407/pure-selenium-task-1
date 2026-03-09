package com.solvd.carinaweb.page.ios;

import com.solvd.carinaweb.page.common.ProductDetailsPage;
import com.solvd.util.MobileContextUtils;
import com.zebrunner.carina.utils.android.AndroidService;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductDetailsPage.class)
public class ProductDetailsPageIos extends ProductDetailsPage {

    public ProductDetailsPageIos(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean downloadPicture() {
        By picturePath = By.xpath("//section[contains(@id,'content')]//img[contains(@class,'js-qv-product-cover')]");

        String imageUrl = findExtendedWebElement(picturePath).getAttribute("src");

        getDriver().get(imageUrl);
        MobileContextUtils mobileContextUtils = new MobileContextUtils();
        mobileContextUtils.switchMobileContext(MobileContextUtils.View.NATIVE);

        ExtendedWebElement browserMenu = findExtendedWebElement(By.id("com.android.chrome:id/menu_button"));
        browserMenu.click();

        ExtendedWebElement downloadButton = findExtendedWebElement(By.id("com.android.chrome:id/button_three"));
        downloadButton.click();

        AndroidService androidService = new AndroidService();
        androidService.pressHome();

        ExtendedWebElement photoApp = findExtendedWebElement(By.xpath("//android.widget.TextView[contains (@text, 'Photos')]"));
        photoApp.click();
        return isPhotoListNotEmpty();
    }

    public boolean isPhotoListNotEmpty(){
        By photoList = By.xpath("//android.widget.GridView[@resource-id='com.google.android.apps.photos:id/recycler_view']//android.widget.ImageView");
        return !findExtendedWebElements(photoList).isEmpty();
    }

}