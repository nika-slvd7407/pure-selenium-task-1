package com.solvd.carinaweb.page;

import com.solvd.util.PriceUtil;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

public class MainPage extends BasePage {

    @FindBy(css = "input.ui-autocomplete-input")
    private ExtendedWebElement inputForm;

    @FindBy(css = "div.thumbnail-container h3 a")
    private List<ExtendedWebElement> mainPageItemList;

    @FindBy(css = "div.thumbnail-container span.price")
    private List<ExtendedWebElement> priceList;

    @FindBy(id = "content")
    private ExtendedWebElement pageContainer;

    public MainPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(pageContainer);

        waitUntilListsArePopulated(priceList, mainPageItemList);
    }

    public SearchPage search(String name) {
        inputForm.type(name);
        inputForm.sendKeys(Keys.ENTER);
        return new SearchPage(getDriver());
    }

    public ProductDetailsPage clickRandomItem() {
        int randomIndex = new Random().nextInt(mainPageItemList.size());
        mainPageItemList.get(randomIndex).click();
        return new ProductDetailsPage(getDriver());
    }

    public ProductDetailsPage clickItem(int index) {
        mainPageItemList.get(index).click();
        return new ProductDetailsPage(getDriver());
    }

    public String getItemName(int index) {
        mainPageItemList.get(index).assertElementPresent();

        return mainPageItemList.get(index)
                .getText()
                .toLowerCase()
                .replace("...", "")
                .trim();
    }

    public BigDecimal getPrice(int index) {


        String rawPrice = priceList.get(index)
                .getText()
                .replaceAll("[^0-9.]", "");

        BigDecimal priceWithoutTax = BigDecimal.valueOf(Double.parseDouble(rawPrice));
        return PriceUtil.addTaxAndRound(priceWithoutTax);
    }

    public int getMainPageItemAmount() {
        mainPageItemList.get(0).assertElementPresent();
        return mainPageItemList.size();
    }

    public SearchPage selectSubCategory(String mainCategoryName, String subCategoryName) {
        By mainCategory = By.xpath(
                "//ul[@id='top-menu']//a[contains(@class,'dropdown-item') and contains(normalize-space(),'" + mainCategoryName + "')]");

        By subCategory = By.xpath("//a[contains(@class,'dropdown-submenu') and contains(text(),'" + subCategoryName + "')]");
        waitUntilVisibilityOf(mainCategory);

        findExtendedWebElement(mainCategory).hover();

        waitUntilVisibilityOf(subCategory);

        findExtendedWebElement(subCategory).click();
        return new SearchPage(getDriver());
    }
}