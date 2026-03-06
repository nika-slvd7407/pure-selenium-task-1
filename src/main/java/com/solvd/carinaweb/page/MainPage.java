package com.solvd.carinaweb.page;

import com.solvd.util.PriceUtil;
import com.solvd.util.WaitUtil;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
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
    }

    public SearchPage search(String name) {
        inputForm.type(name);
        inputForm.sendKeys(Keys.ENTER);
        return new SearchPage(getDriver());
    }

    public ProductDetailsPage clickRandomItem() {
        int randomIndex = new Random().nextInt(mainPageItemList.size());
        getMainPageItemList().get(randomIndex).click();
        return new ProductDetailsPage(getDriver());
    }

    public ProductDetailsPage clickItem(int index) {
        getMainPageItemList().get(index).click();
        return new ProductDetailsPage(getDriver());
    }

    public String getItemName(int index) {
        return getMainPageItemList().get(index)
                .getText()
                .toLowerCase()
                .replace("...", "")
                .trim();
    }

    public BigDecimal getPrice(int index) {

        String rawPrice = getPriceList().get(index)
                .getText()
                .replaceAll("[^0-9.]", "");

        BigDecimal priceWithoutTax = new BigDecimal(rawPrice);
        return PriceUtil.addTaxAndRound(priceWithoutTax);
    }

    public int getMainPageItemAmount() {
        return getMainPageItemList().size();
    }

    public List<ExtendedWebElement> getMainPageItemList() {
        WaitUtil.waitForElementsListNotEmpty(mainPageItemList, 15, getDriver());
        return mainPageItemList;
    }

    public List<ExtendedWebElement> getPriceList() {
        WaitUtil.waitForElementsListNotEmpty(priceList, 15, getDriver());
        return priceList;
    }

    public SearchPage selectSubCategory(String mainCategoryName, String subCategoryName) {
        By mainCategory = By.xpath(
                "//ul[@id='top-menu']//a[contains(@class,'dropdown-item') and contains(normalize-space(),'" + mainCategoryName + "')]");

        By subCategory = By.xpath("//a[contains(@class,'dropdown-submenu') and contains(text(),'" + subCategoryName + "')]");

        isAnyElementPresent(findExtendedWebElement(mainCategory));

        findExtendedWebElement(mainCategory).hover();

        isAnyElementPresent(findExtendedWebElement(subCategory));

        findExtendedWebElement(subCategory).click();
        return new SearchPage(getDriver());
    }
}