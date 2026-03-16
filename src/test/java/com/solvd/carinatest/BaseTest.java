package com.solvd.carinatest;

import com.solvd.carinaweb.page.common.BasePage;
import com.solvd.carinaweb.page.common.MainPage;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.utils.R;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

public class BaseTest extends AbstractTest {

    protected final Logger log = LogManager.getLogger(getClass());
    protected String browser;

    public BaseTest() {
    }

    public BaseTest(String browser) {
        this.browser = browser;
    }

    @BeforeMethod
    public void setUp() {
        R.CONFIG.put("browser", browser);
        log.info("{} browser will be used for test", browser);
    }

    protected MainPage openMainPage() {
        BasePage basePage = initPage(getDriver(), BasePage.class);
        basePage.open();
        return basePage.switchToShopFrame();
    }
 }
