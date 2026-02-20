package com.solvd.carinatest;

import com.solvd.carinaweb.page.BasePage;
import com.solvd.carinaweb.page.ItemPage;
import com.solvd.carinaweb.page.MainPage;
import com.solvd.carinaweb.page.SearchPage;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.utils.R;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class SearchFunctionalityTestt extends AbstractTest {

    protected final Logger log = LogManager.getLogger(SearchFunctionalityTestt.class);
    private static final String ITEM_TO_SEARCH = R.CONFIG.get("ITEM_TO_SEARCH");

    @BeforeMethod
    public void setUp() {
        sf = new SoftAssert();
    }

    @Test(description = "assert that search function is working properly and outputs items")
    public void testSearchFunction() throws InterruptedException {

        BasePage basePage = new BasePage(getDriver());
        basePage.open();
        MainPage mainPage = basePage.switchToShopFrame();
        String url = getDriver().getCurrentUrl();
        log.info(url);
        SearchPage searchPage = mainPage.search(ITEM_TO_SEARCH);

        List<String> searchedItems = searchPage.getSearchedItems();

        Assert.assertTrue(!searchedItems.isEmpty(), "error zero items found!");
        searchedItems.forEach(item -> log.info("{} found", item));
    }

    @Test(description = "select category and assert that all the items shown are of right category")
    public void testCategoryFunctionality() {
        BasePage basePage = new BasePage(getDriver());
        basePage.open();
        MainPage mainPage = basePage.switchToShopFrame();

        String url = getDriver().getCurrentUrl();
        log.info(url);

        SearchPage searchPage = mainPage.selectClothesMenCategory();
        int itemsOnSearchPageAmount = searchPage.getItemAmount();
        boolean areAllItemsRightCategory = true;
        for (int i = 0; i < itemsOnSearchPageAmount; i++) {

            ItemPage itemPage = searchPage.openItemByIndex(i);
            if (!itemPage.checkCategory(R.CONFIG.get("CATEGORY"))) {
                areAllItemsRightCategory = false;
            }
            itemPage.back();
        }
        Assert.assertTrue(areAllItemsRightCategory);
    }
}
