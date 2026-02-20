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
    protected static final int AMOUNT_OF_CLICKS = 5;
    protected SoftAssert sf;
    protected static final int WAIT_TIME = R.CONFIG.getInt("WAIT_TIME");
    protected static final String ITEM_TO_SEARCH = R.CONFIG.get("ITEM_TO_SEARCH");


    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) {
        R.CONFIG.put("browser", browser);
        log.info("{} browser will be used for test", browser);
        sf = new SoftAssert();
    }
}
