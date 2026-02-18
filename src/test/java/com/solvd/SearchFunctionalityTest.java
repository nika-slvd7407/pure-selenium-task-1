package com.solvd;

import com.solvd.util.Config;
import com.solvd.web.page.ItemPage;
import com.solvd.web.page.MainPage;
import com.solvd.web.page.SearchPage;
import org.testng.annotations.Test;

import java.util.List;

public class SearchFunctionalityTest extends AbstractTest {

    private static final String ITEM_TO_SEARCH = Config.get("ITEM_TO_SEARCH");

    @Test(description = "assert that search function is working properly and outputs items")
    public void testSearchFunction() throws InterruptedException {

        MainPage mainPage = new MainPage(getDriver());
        SearchPage searchPage = mainPage.search(ITEM_TO_SEARCH);

        List<String> searchedItems = searchPage.getSearchedItems();

        sf.assertTrue(!searchedItems.isEmpty(), "error zero items found!");
        searchedItems.forEach(item -> log.info("{} found", item));
        sf.assertAll();
    }

    @Test(description = "select category and assert that all the items shown are of right category")
    public void testCategoryFunctionality() {
        MainPage mainPage = new MainPage(getDriver());
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
        sf.assertTrue(areAllItemsRightCategory);
        sf.assertAll();
    }
}
