package com.solvd.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebUtil {

    public static void switchFrame(WebDriver driver, String id, WebDriverWait wait){
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
        driver.switchTo().frame(iframe);
    }
}
