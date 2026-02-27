package com.solvd;

import com.solvd.util.Config;
import com.solvd.web.page.ItemPage;
import com.solvd.web.page.MainPage;
import com.solvd.web.page.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchFunctionalityTest extends AbstractTest {

    private static final String ITEM_TO_SEARCH = Config.get("ITEM_TO_SEARCH");
    private static final String CATEGORY = Config.get("CATEGORY");
    private static final String MAIN_CATEGORY = Config.get("MAIN_CATEGORY");


    @Test(description = "assert that search function is working properly and outputs items")
    public void testSearchFunction() throws InterruptedException {

        MainPage mainPage = new MainPage(getDriver());
        SearchPage searchPage = mainPage.search(ITEM_TO_SEARCH);

        List<String> searchedItems = searchPage.getSearchedItems();

        Assert.assertTrue(
                searchedItems.stream().anyMatch(item -> item.contains(ITEM_TO_SEARCH.toLowerCase())),
                "error no items found with name: " + ITEM_TO_SEARCH
        );
    }

    @Test(description = "select category and assert that all the items shown are of right category")
    public void testCategoryFunctionality() {
        MainPage mainPage = new MainPage(getDriver());
        SearchPage searchPage = mainPage.selectSubCategory(MAIN_CATEGORY, CATEGORY);
        ItemPage itemPage = searchPage.openItemByIndex(0);
        String firstCategory = itemPage.getCategory();

        Assert.assertEquals(
                firstCategory,
                CATEGORY,
                "First item does not belong to selected category: " + firstCategory
        );
    }

}
