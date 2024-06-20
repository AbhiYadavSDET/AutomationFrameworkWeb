package helper;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import pageObjects.LoginPage;
import utilities.CommonMethods;

public class LoginPageHelper {
    private WebDriver driver;
    private LoginPage loginPage;
    private ExtentTest test;


    SoftAssert softAssert = new SoftAssert();

    public LoginPageHelper(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
        this.test = test;
    }

    public void login(String url,String mobileNumber, String otp) {
        driver.get(url);
        loginPage.clickonLogin();
        loginPage.enterMobileNumber(mobileNumber);
        loginPage.getOtp();
        loginPage.enterOtp(otp);
        loginPage.submitOtp();

        if(loginPage.isBackButtonDisplayed()){
            loginPage.clickonBackButton();
        }

        // Assertion 1: Verify current URL
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertEquals(currentUrl, url, "Current URL is not as expected");

        // Assertion 2: Verify page title
        String pageTitle = driver.getTitle();
        softAssert.assertEquals(pageTitle, "MobiKwik: Bill Payment, Mobile Recharge, Wallet, UPI, Loan & Paylater", "Page title is not as expected");

        softAssert.assertAll();

    }

    public void logout() {
// dummy testing
       loginPage.clickonProfile();
       loginPage.clickonLogoutButton();


    }
}
