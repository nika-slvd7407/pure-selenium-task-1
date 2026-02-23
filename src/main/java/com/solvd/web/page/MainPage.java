package com.solvd.web.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(MainPage.class);

    @FindBy(css = "input.ui-autocomplete-input")
    private WebElement inputForm;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(inputForm));
    }

    public SearchPage search(String name) {
        inputForm.sendKeys(name);
        inputForm.submit();
        return new SearchPage(driver);
    }

}
