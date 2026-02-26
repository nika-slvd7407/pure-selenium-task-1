package com.solvd.web.page;

import com.solvd.util.PriceUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class MainPage extends AbstractPage {

    @FindBy(css = "input.ui-autocomplete-input")
    private WebElement inputForm;

    private By itemLocator = By.cssSelector("div.thumbnail-container h3 a");

    private By menCategory = By.id("category-4");

    @FindBy(css = "div.thumbnail-container span.price")
    private List<WebElement> priceList;

    @FindBy(id = "category-3")
    private WebElement clotheCategoryButton;

    @FindBy(id = "wrapper")
    private WebElement pageContainer;

    public MainPage(WebDriver driver) {
        super(driver);
        switchToFramelive();
        waitForElementVisible(pageContainer);
    }

    public SearchPage search(String name) {
        sendKeys(inputForm, name);
        submit(inputForm);
        return new SearchPage(getDriver());
    }

    public ItemPage clickRandomItem() {
        int randomIndex = new Random().nextInt(getItems().size());
        WebElement elementToClick = getItems().get(randomIndex);
        click(elementToClick);
        return new ItemPage(getDriver());
    }

    private List<WebElement> getItems() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(itemLocator));
    }

    public ItemPage clickItem(int index) {
        List<WebElement> items = getItems();
        click(items.get(index));
        return new ItemPage(getDriver());
    }

    public String getName(int index) {
        WebElement elementToGetName = getItems().get(index);
        return getText(elementToGetName).toLowerCase().replace("...", "");
    }

    public Double getPrice(int index) {
        String rawPrice = getText(priceList.get(index));
        double priceWithoutTax = Double.parseDouble(rawPrice
                .replaceAll("[^0-9.]", ""));
        return PriceUtil.addTaxAndRound(priceWithoutTax);
    }

    public int getMainPageItemAmount() {
        return getItems().size();
    }

    public SearchPage selectClothesMenCategory() {
        hover(clotheCategoryButton);
        click(wait.until(ExpectedConditions.elementToBeClickable(menCategory)));
        return new SearchPage(getDriver());
    }

    public SearchPage selectSubCategory(String mainCategoryName, String subCategoryName) {
        By mainCategory = By.xpath(
                "//ul[@id='top-menu']//a[contains(@class,'dropdown-item') and contains(normalize-space(),'" + mainCategoryName + "')]");

        By subCategory = By.xpath("//a[contains(@class,'dropdown-submenu') and contains(text(),'" + subCategoryName + "')]");

        hover(getDriver().findElement(mainCategory));

        click(getDriver().findElement(subCategory));
        return new SearchPage(getDriver());
    }


}
