package com.solvd.util;

import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtil {

    private static Duration waitTimeoutSec = Duration.ofSeconds(Long.parseLong(R.CONFIG.get("WAIT_TIME")));

    public static void waitForElementsListNotEmpty(final List<? extends ExtendedWebElement> elements, WebDriver driver) {
        try {
            new WebDriverWait(driver, waitTimeoutSec)
                    .ignoring(WebDriverException.class)
                    .until(d -> elements.stream().anyMatch(ExtendedWebElement::isElementPresent));
        } catch (TimeoutException e) {
            throw new RuntimeException("Elements list doesn't contain any visible elements");
        }
    }
}
