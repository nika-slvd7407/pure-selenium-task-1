package com.solvd.web.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class MainPage {

    private static final Logger log = LogManager.getLogger(MainPage.class);

    @FindBy(css = ".cup-body")
    private List<WebElement> coffee;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<String> getCoffeNameList() {
        List<String> coffeeNames = new ArrayList<>();
        coffee.forEach(webElement -> coffeeNames.add(webElement.getAttribute("data-test").toLowerCase()));
        return coffeeNames;
    }

    public boolean areAllCoffeesVisible() {
        for (WebElement webElement : coffee) {
            if (!webElement.isDisplayed()) {
                log.info(webElement.getAccessibleName() + " is not visible");
                return false;
            }
        }
        return true;
    }


}
