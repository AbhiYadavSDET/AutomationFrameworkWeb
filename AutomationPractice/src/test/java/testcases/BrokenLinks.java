package testcases;

import base.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.CommonMethods;
import utilities.Listeners;

import java.util.List;

public class BrokenLinks extends BaseClass {

    @Test(priority = 1)
    public void checkTheBrokenLinks() {
        ExtentTest test = Listeners.getExtentTest();
        test.info("Starting checkTheBrokenLinks test with thread: " + Thread.currentThread().getId());
        WebDriver driver = getDriver();
        CommonMethods.navigateToUrl("https://google.com/", test, driver);
        List<WebElement> links = driver.findElements(By.tagName("a"));
        test.info("Found " + links.size() + " links.");
        for (WebElement link : links) {
            String linkUrl = link.getAttribute("href");
            if (linkUrl != null && !linkUrl.isEmpty()) {
                test.info("Checking URL: " + linkUrl);
                checkURL(linkUrl, test);
            }
        }
    }

    @Test(priority = 2)
    public void checkTheBrokenImages() {
        ExtentTest test = Listeners.getExtentTest();
        test.info("Starting checkTheBrokenImages test with thread: " + Thread.currentThread().getId());
        WebDriver driver = getDriver();
        CommonMethods.navigateToUrl("https://the-internet.herokuapp.com/broken_images", test, driver);
        List<WebElement> images = driver.findElements(By.tagName("img"));
        test.info("Found " + images.size() + " images.");
        for (WebElement image : images) {
            String imageUrl = image.getAttribute("src");
            if (imageUrl != null && !imageUrl.isEmpty()) {
                test.info("Checking image URL: " + imageUrl);
                checkURL(imageUrl, test);
            }
        }
    }

    public static void checkURL(String url, ExtentTest test) {
        try {
            Response response = RestAssured.get(url);
            int statusCode = response.statusCode();

            if (statusCode >= 400) {
                test.fail(url + ": is broken. Status code: " + statusCode);
            }
            Assert.assertTrue(statusCode < 400, "The link is broken with response code: " + statusCode);

        } catch (Exception e) {
            test.fail("Exception checking URL: " + url + ". Message: " + e.getMessage());

        }
    }
}
