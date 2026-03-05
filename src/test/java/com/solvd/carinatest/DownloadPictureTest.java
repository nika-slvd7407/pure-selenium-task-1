package com.solvd.carinatest;

import com.solvd.carinaweb.page.common.BasePage;
import com.solvd.carinaweb.page.common.MainPage;
import com.solvd.carinaweb.page.common.ProductDetailsPage;
import org.testng.annotations.Test;

public class DownloadPictureTest extends BaseTest {

    @Test(description = "assert that picture can be downloaded and use android switching to different contexts")
    public void testDownloadPicture() {
        BasePage basePage = initPage(getDriver(), BasePage.class);
        basePage.open();
        MainPage mainPage = basePage.switchToShopFrame();
        ProductDetailsPage productDetailsPage = mainPage.clickItem(0);

        productDetailsPage.downloadPicture();


    }
}
