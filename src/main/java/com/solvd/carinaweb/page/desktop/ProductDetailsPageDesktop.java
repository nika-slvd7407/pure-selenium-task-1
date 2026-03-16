package com.solvd.carinaweb.page.desktop;

import com.solvd.carinaweb.page.common.ProductDetailsPage;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = ProductDetailsPage.class)
public class ProductDetailsPageDesktop extends ProductDetailsPage {

    public ProductDetailsPageDesktop(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPhotoDownloaded() {
        By picturePath = By.xpath("//section[contains(@id,'content')]//img[contains(@class,'js-qv-product-cover')]");

        String imageUrl = findExtendedWebElement(picturePath).getAttribute("src");

        try (InputStream in = new URL(imageUrl).openStream()) {
            Path path = Paths.get("target/product-image.jpg");

            Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
            return Files.exists(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}