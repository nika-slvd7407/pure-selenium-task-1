package com.solvd.carinatest;


import com.solvd.carinaweb.page.common.SearchPage;
import com.solvd.carinaweb.page.common.BasePage;
import com.solvd.carinaweb.page.common.ItemPage;
import com.solvd.carinaweb.page.common.MainPage;
import com.zebrunner.carina.utils.R;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchFunctionalityTest extends BaseTest {

    private static final String ITEM_TO_SEARCH = R.CONFIG.get("ITEM_TO_SEARCH");
    private static final String CATEGORY = R.CONFIG.get("CATEGORY");
    private static final String MAIN_CATEGORY = R.CONFIG.get("MAIN_CATEGORY");


    @Test(description = "assert that search function is working properly and outputs items")
    public void testSearchFunction() {
        BasePage basePage = initPage(getDriver(), BasePage.class);
        basePage.open();
        MainPage mainPage = basePage.switchToShopFrame();

        SearchPage searchPage = mainPage.search(ITEM_TO_SEARCH);

        List<String> searchedItems = searchPage.getSearchedItems();

        Assert.assertFalse(
                searchedItems.isEmpty(),
                "error zero items found"
        );
    }

    @Test(description = "select category and assert that first item belongs to selected category")
    public void testCategoryFunctionality() {

        BasePage basePage = initPage(getDriver(), BasePage.class);
        basePage.open();
        MainPage mainPage = basePage.switchToShopFrame();
        SearchPage searchPage = mainPage.selectSubCategory(MAIN_CATEGORY, CATEGORY);

        ItemPage itemPage = searchPage.openItemByIndex(0);
        String actualCategory = itemPage.getCategory();

        Assert.assertEquals(
                actualCategory,
                CATEGORY,
                "First item does not belong to selected category"
        );
    }
}