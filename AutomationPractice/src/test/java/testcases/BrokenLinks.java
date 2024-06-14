package testcases;

import base.BaseClass;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class BrokenLinks extends BaseClass {

    @Test(priority = 1)
    public void checkTheBrokenLinks() {
        WebDriver driver = getDriver();
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized.");
        }

        System.out.println("Starting checkTheBrokenLinks test with thread: " + Thread.currentThread().getId());
        driver.get("https://www.mobikwik.com/");
        System.out.println("Navigated to https://www.mobikwik.com/");

        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Found " + links.size() + " links.");

        for (WebElement link : links) {
            String linkUrl = link.getAttribute("href");
            if (linkUrl != null && !linkUrl.isEmpty()) {
                System.out.println("Checking URL: " + linkUrl);
                checkURL(linkUrl);
            }
        }
    }

    @Test(priority = 2)
    public void checkTheBrokenImages() {
        WebDriver driver = getDriver();
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized.");
        }

        System.out.println("Starting checkTheBrokenImages test with thread: " + Thread.currentThread().getId());
        driver.get("https://the-internet.herokuapp.com/broken_images");
        System.out.println("Navigated to https://the-internet.herokuapp.com/broken_images");

        List<WebElement> images = driver.findElements(By.tagName("img"));
        System.out.println("Found " + images.size() + " images.");

        for (WebElement image : images) {
            String imageUrl = image.getAttribute("src");
            if (imageUrl != null && !imageUrl.isEmpty()) {
                System.out.println("Checking image URL: " + imageUrl);
                checkURL(imageUrl);
            }
        }
    }

    public static void checkURL(String url) {
        try {
            Response response = RestAssured.get(url);
            int statusCode = response.statusCode();

            if (statusCode >= 400) {
                System.out.println(url + ": is broken. Status code: " + statusCode);
            } else {
                System.out.println(url + ": is valid. Status code: " + statusCode);
            }
        } catch (Exception e) {
            System.out.println("Exception checking URL: " + url + ". Message: " + e.getMessage());
        }
    }
}
