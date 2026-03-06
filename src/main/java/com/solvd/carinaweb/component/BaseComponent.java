package com.solvd.carinaweb.component;

import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BaseComponent extends AbstractUIObject {

    public BaseComponent(WebDriver driver) {
        super(driver);
    }
}
