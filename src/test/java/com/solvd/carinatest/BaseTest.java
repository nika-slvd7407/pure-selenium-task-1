package com.solvd.carinatest;

import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.utils.R;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

public class BaseTest extends AbstractTest {

    protected final Logger log = LogManager.getLogger(getCases());
    protected SoftAssert sf;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) {
        R.CONFIG.put("browser", browser);
        log.info("{} browser will be used for test", browser);
        sf = new SoftAssert();
    }
}
