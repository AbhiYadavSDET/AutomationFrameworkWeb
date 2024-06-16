package testcases;

import base.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import helper.LoginPageHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utilities.Listeners;

public class LoginPageTest extends BaseClass {

    @Test
    public void loginTest() {
        ExtentTest test = Listeners.getExtentTest();
        WebDriver driver = getDriver();  // Get the WebDriver instance from BaseClass

        LoginPageHelper loginHelper = new LoginPageHelper(driver,test);
        loginHelper.login("1234567890", "123456");
    }

    @Test
    public void logoutTest() {
        ExtentTest test = Listeners.getExtentTest();
        WebDriver driver = getDriver();  // Get the WebDriver instance from BaseClass

        LoginPageHelper loginHelper = new LoginPageHelper(driver,test);
        loginHelper.login("1234567890", "123456");
        loginHelper.logout();
    }
}
