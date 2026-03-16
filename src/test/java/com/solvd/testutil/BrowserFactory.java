package com.solvd.testutil;

import com.solvd.carinatest.CartFunctionalityTest;
import com.solvd.carinatest.ProductCardFunctionalityTest;
import com.solvd.carinatest.SearchFunctionalityTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;

import java.util.ArrayList;
import java.util.List;

public class BrowserFactory {

    @Factory
    @Parameters("browsers")
    public Object[] createInstances(String browsers) {

        String[] browserList = browsers.split(",");
        List<Object> tests = new ArrayList<>();

        for (String browser : browserList) {
            tests.add(new SearchFunctionalityTest(browser));
            tests.add(new ProductCardFunctionalityTest(browser));
            tests.add(new CartFunctionalityTest(browser));
        }

        return tests.toArray();
    }
}