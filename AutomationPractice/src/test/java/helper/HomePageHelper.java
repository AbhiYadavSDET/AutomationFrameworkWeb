package helper;

import base.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pageObjects.LoginPage;
import utilities.CommonMethods;
import utilities.Listeners;

import java.util.List;

public class HomePageHelper extends BaseClass {

    private WebDriver driver;
    private LoginPage loginPage;
    private ExtentTest test;

    public HomePageHelper(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
        this.test = test;
    }

    public void checkTheBrokenLinks(String url) {
        ExtentTest test = Listeners.getExtentTest();
        test.info("Starting checkTheBrokenLinks test with thread: " + Thread.currentThread().getId());
        WebDriver driver = getDriver();
        CommonMethods.navigateToUrl(url, test, driver);
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

    public void checkTheBrokenImages(String url) {
        ExtentTest test = Listeners.getExtentTest();
        test.info("Starting checkTheBrokenImages test with thread: " + Thread.currentThread().getId());
        WebDriver driver = getDriver();
        CommonMethods.navigateToUrl(url, test, driver);
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
