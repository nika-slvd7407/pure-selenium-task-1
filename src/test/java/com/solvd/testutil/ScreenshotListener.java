package com.solvd.testutil;

import com.solvd.util.ScreenshotUtil;
import com.zebrunner.carina.core.AbstractTest;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());

        Object testClass = result.getInstance();
        if (testClass instanceof AbstractTest) {
            WebDriver driver = ((AbstractTest) testClass).getDriver();
            if (driver != null) {
                ScreenshotUtil.captureScreenshot(driver, result.getName());
            }
        }
    }
}
