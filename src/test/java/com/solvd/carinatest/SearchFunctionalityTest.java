package com.solvd.carinatest;


import com.solvd.carinaweb.page.common.SearchPage;
import com.solvd.carinaweb.page.common.BasePage;
import com.solvd.carinaweb.page.common.ProductDetailsPage;
import com.solvd.carinaweb.page.common.MainPage;
import com.zebrunner.carina.utils.R;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchFunctionalityTest extends BaseTest {

    private static final String ITEM_TO_SEARCH = R.CONFIG.get("ITEM_TO_SEARCH");
    private static final String CATEGORY = R.CONFIG.get("CATEGORY");
    private static final String MAIN_CATEGORY = R.CONFIG.get("MAIN_CATEGORY");

    public SearchFunctionalityTest(String browser) {
        super(browser);
    }

    @Test(description = "assert that search function is working properly and outputs items")
    public void testSearchFunction() {
        MainPage mainPage = openMainPage();

        SearchPage searchPage = mainPage.search(ITEM_TO_SEARCH);

        List<String> searchedItems = searchPage.getSearchedItems();

        Assert.assertTrue(
                searchedItems.stream().anyMatch(i -> i.toLowerCase().contains(ITEM_TO_SEARCH.toLowerCase())),
                String.format("Search results do not match query. Items found: %s", searchedItems)
        );
    }

    @Test(description = "select category and assert that first item belongs to selected category")
    public void testCategoryFunctionality() {
        MainPage mainPage = openMainPage();
        SearchPage searchPage = mainPage.selectSubCategory(MAIN_CATEGORY, CATEGORY);

        ProductDetailsPage productDetailsPage = searchPage.openItemByIndex(0);
        String actualCategory = productDetailsPage.getCategory().get();

        Assert.assertEquals(
                actualCategory,
                CATEGORY,
                "First item does not belong to selected category"
        );
    }
}