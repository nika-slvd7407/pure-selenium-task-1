package com.solvd.carinatest;

import com.solvd.carinaweb.page.BasePage;
import com.solvd.carinaweb.page.ItemPage;
import com.solvd.carinaweb.page.MainPage;
import com.solvd.carinaweb.page.SearchPage;
import com.zebrunner.carina.utils.R;
import org.testng.annotations.Test;

import java.util.List;

public class SearchFunctionalityTestt extends BaseTest {

    @Test(description = "assert that search function is working properly and outputs items")
    public void testSearchFunction() throws InterruptedException {

        BasePage basePage = new BasePage(getDriver());
        basePage.open();
        MainPage mainPage = basePage.switchToShopFrame();

        String url = getDriver().getCurrentUrl();
        log.info(ITEM_TO_SEARCH);
        SearchPage searchPage = mainPage.search(ITEM_TO_SEARCH);
        List<String> searchedItems = searchPage.getSearchedItems();

        sf.assertTrue(!searchedItems.isEmpty(), "error zero items found!");
        searchedItems.forEach(item -> log.info("{} found", item));
        sf.assertAll();
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
            log.info("found item amount {} ", itemsOnSearchPageAmount);
            ItemPage itemPage = searchPage.openItemByIndex(i);
            log.info("found item description: {}", itemPage.getItemName());
            if (!itemPage.checkCategory(R.CONFIG.get("CATEGORY"))) {
                areAllItemsRightCategory = false;
            }
            itemPage.back();
        }
        sf.assertTrue(areAllItemsRightCategory);
        sf.assertTrue(itemsOnSearchPageAmount > 0, "error zero items found!");
        sf.assertAll();
    }
}
