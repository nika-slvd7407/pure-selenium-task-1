package com.solvd.carinaweb.uielement.common;

import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseComponent extends AbstractUIObject {

    protected WebDriverWait wait;
    private int WAIT_TIME = R.CONFIG.getInt("WAIT_TIME");

    public BaseComponent(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(WAIT_TIME)
        );
    }
}
