package testcases;

import com.aventstack.extentreports.ExtentTest;
import base.BaseClass;
import helper.LoginPageHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.ExcelUtils;
import utilities.Listeners;

public class LoginPageTest extends BaseClass {
    ConfigReader configReader = ConfigReader.getInstance();
    String prodUrl = configReader.getProperty("prodUrl");
    String mobileNumber = configReader.getProperty("mobileNumber");
    String noKycMobileNumber = configReader.getProperty("noKycMobileNumber");
    String otp = configReader.getProperty("otp");


    @Test(enabled = true)
    public void loginTest() {
        ExtentTest test = Listeners.getExtentTest();
        WebDriver driver = getDriver();  // Get the WebDriver instance from BaseClass

        LoginPageHelper loginHelper = new LoginPageHelper(driver, test);
        loginHelper.login(prodUrl, noKycMobileNumber, otp);
    }

    @Test(enabled = false)
    public void logoutTest() {
        ExtentTest test = Listeners.getExtentTest();
        WebDriver driver = getDriver();  // Get the WebDriver instance from BaseClass

        LoginPageHelper loginHelper = new LoginPageHelper(driver, test);
        loginHelper.login(prodUrl, mobileNumber, otp);
        loginHelper.logout();
    }

    @DataProvider(name = "loginData")
    public Object[][] getData() throws Exception {
        // Construct the Excel file path
        String excelFilePath = System.getProperty("user.dir") + "/testData/testData.csv";
        String sheetName = "Sheet1";

        return ExcelUtils.getTableArray(excelFilePath, sheetName);
    }
}
