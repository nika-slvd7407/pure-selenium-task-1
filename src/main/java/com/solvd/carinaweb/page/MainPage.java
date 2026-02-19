package com.solvd.carinaweb.page;

import com.solvd.carinaweb.page.SearchPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;

public class MainPage extends AbstractPage {

    @FindBy(css = "input.ui-autocomplete-input")
    private ExtendedWebElement inputForm;

    @FindBy(css = "div.thumbnail-container h3 a")
    private List<ExtendedWebElement> mainPageItemList;

    @FindBy(css = "div.thumbnail-container span.price")
    private List<ExtendedWebElement> priceList;

    @FindBy(id = "category-3")
    private ExtendedWebElement clotheCategoryButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public SearchPage search(String name) {
        inputForm.sendKeys(name);
        inputForm.submit();
        return new SearchPage(driver);
    }

    public ItemPage clickRandomItem() {
        int randomIndex = (int) (Math.random() * mainPageItemList.size() - 1);
        WebElement elementToClick = mainPageItemList.get(randomIndex);
        elementToClick.click();
        return new ItemPage(driver);
    }

    public ItemPage clickItem(int index) {
        WebElement elementToClick = mainPageItemList.get(index);
        elementToClick.click();
        return new ItemPage(driver);
    }

    public String getName(int index) {
        WebElement elementToGetName = mainPageItemList.get(index);
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
        return mainPageItemList.size();
    }

    public SearchPage selectClothesMenCategory() {
        clotheCategoryButton.hover();
        WebElement subMenu = driver.findElement(By.id("category-4"));
        subMenu.click();
        return new SearchPage(driver);
    }

    public void switchToShopFrame() {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(50L)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("framelive")));
        } catch (Exception e) {
        }
        getDriver().switchTo().frame("framelive");
    }

}
