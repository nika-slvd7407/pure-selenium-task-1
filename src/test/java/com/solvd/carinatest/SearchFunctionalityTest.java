package com.solvd.carinatest;

import com.solvd.carinaweb.page.common.BasePage;
import com.solvd.carinaweb.page.common.ItemPage;
import com.solvd.carinaweb.page.common.MainPage;
import com.solvd.carinaweb.page.common.SearchPage;
import com.zebrunner.carina.utils.R;
import org.testng.annotations.Test;

import java.util.List;

public class SearchFunctionalityTest extends BaseTest {

    @Test(description = "assert that search function is working properly and outputs items")
    public void testSearchFunction() throws InterruptedException {

        BasePage basePage = initPage(getDriver(), BasePage.class);
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

    @Test(description = "select category and assert that the first item shown is of the right category")
    public void testCategoryFunctionality() {
        BasePage basePage = initPage(getDriver(), BasePage.class);
        basePage.open();
        MainPage mainPage = basePage.switchToShopFrame();

        SearchPage searchPage = mainPage.selectClothesMenCategory();
        pause(5L);
        int itemsOnSearchPageAmount = searchPage.getItemAmount();

        sf.assertTrue(itemsOnSearchPageAmount > 0, "error zero items found!");

        ItemPage firstItemPage = searchPage.openFirstItem();
        boolean isFirstItemRightCategory = firstItemPage.checkCategory(R.CONFIG.get("CATEGORY"));
        firstItemPage.back();

        sf.assertTrue(isFirstItemRightCategory, " item is not in the correct category");
        sf.assertAll();
    }
}
