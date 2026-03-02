package com.solvd.carinatest;

import com.solvd.carinaweb.page.common.BasePage;
import com.solvd.carinaweb.page.common.ItemPage;
import com.solvd.carinaweb.page.common.MainPage;
import org.testng.annotations.Test;

public class DownloadPictureTest extends BaseTest {

    @Test(description = "assert that picture can be downloaded")
    public void testDownloadPicture() {
        BasePage basePage = initPage(getDriver(), BasePage.class);
        basePage.open();
        MainPage mainPage = basePage.switchToShopFrame();
        ItemPage itemPage = mainPage.clickItem(0);

        itemPage.downloadPicture();

    }
}
