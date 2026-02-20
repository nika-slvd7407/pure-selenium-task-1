package com.solvd.carinaweb.page;

import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.decorator.ExtendedFieldDecorator;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class MainPage extends AbstractPage {

    private static final Logger log = LogManager.getLogger(MainPage.class);

    @FindBy(css = "input.ui-autocomplete-input")
    private ExtendedWebElement inputForm;

    @FindBy(css = "div.thumbnail-container h3 a")
    private List<ExtendedWebElement> mainPageItemList;

    @FindBy(css = "div.thumbnail-container span.price")
    private List<ExtendedWebElement> priceList;

    @FindBy(id = "category-3")
    private ExtendedWebElement clotheCategoryButton;

    private static final int WAIT_TIME = R.CONFIG.getInt("WAIT_TIME");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public SearchPage search(String name) {
        inputForm.sendKeys(name);
        inputForm.submit();
        return new SearchPage(getDriver());
    }

    public ItemPage clickRandomItem() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIME)).until(
                driver -> mainPageItemList.size() > 0
        );
        int randomIndex = new Random().nextInt(mainPageItemList.size());
        ExtendedWebElement elementToClick = mainPageItemList.get(randomIndex);
        elementToClick.click();
        return new ItemPage(getDriver());
    }

    public ItemPage clickItem(int index) {
        ExtendedWebElement elementToClick = mainPageItemList.get(index);
        elementToClick.click();
        return new ItemPage(getDriver());
    }

    public String getName(int index) {
        ExtendedWebElement elementToGetName = mainPageItemList.get(index);
        return elementToGetName.getText().toLowerCase().replace("...", "");
    }

    public Double getPrice(int index) {
        String rawPrice = priceList.get(index).getText();
        double priceWithoutTax = Double.parseDouble(rawPrice.substring(1));
        double priceWithTax = priceWithoutTax * 1.20;
        BigDecimal rounded = BigDecimal.valueOf(priceWithTax).setScale(2, RoundingMode.HALF_UP);
        return rounded.doubleValue();
    }

    public int getMainPageItemAmount() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIME)).until(
                driver -> mainPageItemList.size() > 0
        );
        return mainPageItemList.size();
    }

    public SearchPage selectClothesMenCategory() {
        clotheCategoryButton.hover();
        WebElement subMenu = getDriver().findElement(By.id("category-4"));
        subMenu.click();
        return new SearchPage(getDriver());
    }



}
