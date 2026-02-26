package com.solvd.carinaweb.page.common;

import com.solvd.util.PriceUtil;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public abstract class MainPage extends BasePage {

    @FindBy(css = "input.ui-autocomplete-input")
    private ExtendedWebElement inputForm;

    @FindBy(css = "div.thumbnail-container h3 a")
    private List<ExtendedWebElement> mainPageItemList;

    @FindBy(css = "div.thumbnail-container span.price")
    private List<ExtendedWebElement> priceList;

    @FindBy(id = "category-3")
    private ExtendedWebElement clotheCategoryButton;


    @FindBy(id = "wrapper")
    private ExtendedWebElement pageContainer;

    private By contentWrapper = By.id("content");

    public MainPage(WebDriver driver) {
        super(driver);
        //switchToFramelive();
        waitUntilVisibilityOf(contentWrapper);
    }

    public SearchPage search(String name) {
        inputForm.type(name);
        inputForm.sendKeys(Keys.ENTER);
        return initPage(getDriver(), SearchPage.class);
    }

    public ItemPage clickRandomItem() {
        int randomIndex = new Random().nextInt(mainPageItemList.size());
        mainPageItemList.get(randomIndex).click();
        return initPage(getDriver(), ItemPage.class);
    }

    public ItemPage clickItem(int index) {
        mainPageItemList.get(index).click();
        return initPage(getDriver(), ItemPage.class);
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
        return PriceUtil.addTaxAndRound(priceWithoutTax);
    }

    public int getMainPageItemAmount() {
        wait.until(d -> !mainPageItemList.isEmpty());
        return mainPageItemList.size();
    }

    public abstract SearchPage selectClothesMenCategory();
}