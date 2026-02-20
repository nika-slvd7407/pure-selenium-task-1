package com.solvd.carinatest;

import com.solvd.carinaweb.page.ItemPage;
import com.solvd.carinaweb.page.MainPage;
import com.solvd.carinaweb.page.SearchPage;
import com.solvd.util.Config;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.utils.R;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class SearchFunctionalityTestt extends AbstractTest {

    protected final Logger log = LogManager.getLogger(SearchFunctionalityTestt.class);
    private static final String ITEM_TO_SEARCH = Config.get("ITEM_TO_SEARCH");

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(String browser) {

        R.CONFIG.put("browser", browser);
        log.info("{} browser selected", browser);
    }

    @Test(description = "assert that search function is working properly and outputs items")
    @Parameters("browser")
    public void testSearchFunction() throws InterruptedException {

        MainPage mainPage = new MainPage(getDriver());
        mainPage.open();
        mainPage.switchToShopFrame();
        String url = getDriver().getCurrentUrl();
        log.info(url);
        SearchPage searchPage = mainPage.search(ITEM_TO_SEARCH);

        List<String> searchedItems = searchPage.getSearchedItems();

        Assert.assertTrue(!searchedItems.isEmpty(), "error zero items found!");
        searchedItems.forEach(item -> log.info("{} found", item));
    }

    @Test(description = "select category and assert that all the items shown are of right category")
    @Parameters("browser")
    public void testCategoryFunctionality() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.open();
        mainPage.switchToShopFrame();

        String url = getDriver().getCurrentUrl();
        log.info(url);

        SearchPage searchPage = mainPage.selectClothesMenCategory();
        int itemsOnSearchPageAmount = searchPage.getItemAmount();
        boolean areAllItemsRightCategory = true;
        for (int i = 0; i < itemsOnSearchPageAmount; i++) {

            ItemPage itemPage = searchPage.openItemByIndex(i);
            if (!itemPage.checkCategory(Config.get("CATEGORY"))) {
                areAllItemsRightCategory = false;
            }
            itemPage.back();
        }
        Assert.assertTrue(areAllItemsRightCategory);
    }
}
