package com.solvd.web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

public class MainPage extends AbstractPage {

    @FindBy(css = "input.ui-autocomplete-input")
    private WebElement inputForm;

    @FindBy(css = "div.thumbnail-container h3 a")
    private List<WebElement> mainPageItemList;

    @FindBy(css = "div.thumbnail-container span.price")
    private List<WebElement> priceList;

    @FindBy(id = "category-3")
    private WebElement clotheCategoryButton;


    public MainPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(inputForm));
        wait.until(ExpectedConditions.visibilityOfAllElements(mainPageItemList));
        wait.until(ExpectedConditions.visibilityOfAllElements(priceList));
        wait.until(ExpectedConditions.visibilityOf(clotheCategoryButton));

    }

    public SearchPage search(String name) {
        sendKeys(inputForm, name);
        submit(inputForm);
        return new SearchPage(getDriver());
    }

    public ItemPage clickRandomItem() {
        int randomIndex = new Random().nextInt(mainPageItemList.size());
        WebElement elementToClick = mainPageItemList.get(randomIndex);
        click(elementToClick);
        return new ItemPage(getDriver());
    }

    public ItemPage clickItem(int index) {
        WebElement elementToClick = mainPageItemList.get(index);
        click(elementToClick);
        return new ItemPage(getDriver());
    }

    public String getName(int index) {
        WebElement elementToGetName = mainPageItemList.get(index);
        return getText(elementToGetName).toLowerCase().replace("...", "");
    }

    public Double getPrice(int index) {
        String rawPrice = getText(priceList.get(index));
        double priceWithoutTax = Double.parseDouble(rawPrice.substring(1));
        double priceWithTax = priceWithoutTax * 1.20;
        BigDecimal rounded = BigDecimal.valueOf(priceWithTax).setScale(2, RoundingMode.HALF_UP);
        return rounded.doubleValue();
    }

    public int getMainPageItemAmount() {
        return mainPageItemList.size();
    }

    public SearchPage selectClothesMenCategory() {
        hover(clotheCategoryButton);
        WebElement subMenu = getDriver().findElement(By.id("category-4"));
        click(subMenu);
        return new SearchPage(getDriver());
    }


}
