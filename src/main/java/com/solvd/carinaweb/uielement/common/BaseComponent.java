package com.solvd.carinaweb.uielement.common;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.WebDriver;

public abstract class BaseComponent extends AbstractUIObject {

    public BaseComponent(WebDriver driver) {
        super(driver);
    }
}
