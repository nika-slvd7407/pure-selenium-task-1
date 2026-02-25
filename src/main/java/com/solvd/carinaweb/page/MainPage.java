package com.solvd.carinaweb.page;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

public class MainPage extends BasePage {

    @FindBy(css = "input.ui-autocomplete-input")
    private ExtendedWebElement inputForm;

    @FindBy(css = "div.thumbnail-container h3 a")
    private List<ExtendedWebElement> mainPageItemList;

    @FindBy(css = "div.thumbnail-container span.price")
    private List<ExtendedWebElement> priceList;

    @FindBy(id = "category-3")
    private ExtendedWebElement clotheCategoryButton;

    private final By menCategory = By.id("category-4");

    @FindBy(id = "wrapper")
    private ExtendedWebElement pageContainer;

    public MainPage(WebDriver driver) {
        super(driver);
        switchToFramelive();
    }

    public SearchPage search(String name) {
        inputForm.type(name);
        inputForm.sendKeys(Keys.ENTER);
        return new SearchPage(getDriver());
    }

    public ItemPage clickRandomItem() {
        wait.until(d -> !mainPageItemList.isEmpty());
        int randomIndex = new Random().nextInt(mainPageItemList.size());
        mainPageItemList.get(randomIndex).click();
        return new ItemPage(getDriver());
    }

    public ItemPage clickItem(int index) {
        wait.until(d -> !mainPageItemList.isEmpty());
        mainPageItemList.get(index).click();
        return new ItemPage(getDriver());
    }

    public String getName(int index) {
        wait.until(d -> !mainPageItemList.isEmpty());
        return mainPageItemList.get(index)
                .getText()
                .toLowerCase()
                .replace("...", "")
                .trim();
    }

    public Double getPrice(int index) {
        wait.until(d -> !priceList.isEmpty());

        String rawPrice = priceList.get(index)
                .getText()
                .replaceAll("[^0-9.]", "");

        double priceWithoutTax = Double.parseDouble(rawPrice);
        double priceWithTax = priceWithoutTax * 1.20;

        return BigDecimal.valueOf(priceWithTax)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public int getMainPageItemAmount() {
        wait.until(d -> !mainPageItemList.isEmpty());
        return mainPageItemList.size();
    }

    public SearchPage selectClothesMenCategory() {
        clotheCategoryButton.hover();
        wait.until(d -> getDriver().findElement(menCategory).isDisplayed());
        getDriver().findElement(menCategory).click();
        return new SearchPage(getDriver());
    }
}