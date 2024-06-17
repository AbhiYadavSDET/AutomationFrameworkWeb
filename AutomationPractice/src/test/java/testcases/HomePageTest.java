package testcases;

import base.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import helper.HomePageHelper;
import helper.LoginPageHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utilities.Listeners;

public class HomePageTest extends BaseClass {


    @Test
    public void testBrokenLinks() {
        ExtentTest test = Listeners.getExtentTest();
        WebDriver driver = getDriver();  // Get the WebDriver instance from BaseClass

        HomePageHelper homePageHelper = new HomePageHelper(driver, test);
        homePageHelper.checkTheBrokenLinks("https://www.mobikwik.com/");
    }
    @Test
    public void testBrokenImages() {
        ExtentTest test = Listeners.getExtentTest();
        WebDriver driver = getDriver();  // Get the WebDriver instance from BaseClass

        HomePageHelper homePageHelper = new HomePageHelper(driver, test);
        homePageHelper.checkTheBrokenImages("https://www.mobikwik.com/");
    }
}
