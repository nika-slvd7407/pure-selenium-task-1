package com.solvd.web.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends AbstractPage {

    @FindBy(css = "input.ui-autocomplete-input")
    private WebElement inputForm;

    public MainPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(inputForm));
    }

    public SearchPage search(String name) {
        sendKeys(inputForm, name);
        submit(inputForm);
        return new SearchPage(driver);
    }

}
