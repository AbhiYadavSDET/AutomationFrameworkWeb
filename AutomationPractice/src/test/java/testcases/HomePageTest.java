package testcases;

import base.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import helper.HomePageHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Listeners;
import utilities.RetryAnalyzer;

public class HomePageTest extends BaseClass {

    private static final ConfigReader configReader = ConfigReader.getInstance();
    private static final String prodUrl = configReader.getProperty("prodUrl");

    @Test()
    public void testBrokenLinks() {
        ExtentTest test = Listeners.getExtentTest();
        WebDriver driver = getDriver();  // Get the WebDriver instance from BaseClass

        HomePageHelper homePageHelper = new HomePageHelper(driver, test);
        homePageHelper.checkTheBrokenLinks(prodUrl);
    }

    @Test()
    public void testBrokenImages() {
        ExtentTest test = Listeners.getExtentTest();
        WebDriver driver = getDriver();  // Get the WebDriver instance from BaseClass

        HomePageHelper homePageHelper = new HomePageHelper(driver, test);
        homePageHelper.checkTheBrokenImages(prodUrl);
    }
}
