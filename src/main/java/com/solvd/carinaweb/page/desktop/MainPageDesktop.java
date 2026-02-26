package com.solvd.carinaweb.page.desktop;

import com.solvd.carinaweb.page.common.BasePage;
import com.solvd.carinaweb.page.common.MainPage;
import com.solvd.carinaweb.page.common.SearchPage;
import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;
import java.util.Random;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = MainPage.class)

public class MainPageDesktop extends MainPage {

    @FindBy(id = "category-3")
    private ExtendedWebElement clotheCategoryButton;

    private final By menCategory = By.id("category-4");

    public MainPageDesktop(WebDriver driver) {
        super(driver);
    }

    @Override
    public SearchPage selectClothesMenCategory() {
        clotheCategoryButton.hover();
        wait.until(d -> getDriver().findElement(menCategory).isDisplayed());
        getDriver().findElement(menCategory).click();
        return initPage(getDriver(), SearchPage.class);
    }

}
