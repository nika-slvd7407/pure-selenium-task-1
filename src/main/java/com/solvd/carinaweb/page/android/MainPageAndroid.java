package com.solvd.carinaweb.page.android;

import com.solvd.carinaweb.page.common.MainPage;
import com.solvd.carinaweb.page.common.SearchPage;
import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = MainPage.class)
public class MainPageAndroid extends MainPage {

    @FindBy(xpath = "(//a[contains(@class, 'all-product-link')])[1]")
    private ExtendedWebElement allProductsLink;

    private static final int WAIT_TIME = R.CONFIG.getInt("WAIT_TIME");

    public MainPageAndroid(WebDriver driver) {
        super(driver);
    }

    @Override
    public SearchPage selectClothesMenCategory() {

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIME));

        allProductsLink.click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[contains(@class, 'navbar-toggler')])[1]")
        )).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@class, 'category-sub-link') and contains(text(), 'Men')]")
        )).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("div.js-product.product")
        ));
         log.info("finished selecting categorry {}", R.CONFIG.get("CATEGORY"));
        return initPage(getDriver(), SearchPage.class);
    }
}