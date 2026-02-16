package com.solvd.web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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

    }

    public SearchPage search(String name) {
        sendKeys(inputForm, name);
        submit(inputForm);
        return new SearchPage(driver);
    }

    public ItemPage clickRandomItem() {
        int randomIndex = (int) (Math.random() * mainPageItemList.size() - 1);
        WebElement elementToClick = mainPageItemList.get(randomIndex);
        click(elementToClick);
        return new ItemPage(driver);
    }

    public ItemPage clickItem(int index) {
        WebElement elementToClick = mainPageItemList.get(index);
        click(elementToClick);
        return new ItemPage(driver);
    }

    public String getName(int index) {
        WebElement elementToGetName = mainPageItemList.get(index);
        return getText(elementToGetName).toLowerCase().replace("...","");
    }

    public Double getPrice(int index) {
        String rawPrice = getText(priceList.get(index));
        double priceWithoutTax = Double.valueOf(rawPrice.substring(1));
        return priceWithoutTax * 1.20;
    }

    public int getMainPageItemAmount() {
        return mainPageItemList.size();
    }

    public SearchPage selectClothesMenCategory(){
        hover(clotheCategoryButton);
        WebElement subMenu = driver.findElement(By.id("category-4"));
        click(subMenu);
        return new SearchPage(driver);
    }


}
