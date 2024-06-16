package pageObjects;

import base.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonMethods;
import utilities.Listeners;

public class LoginPage extends BaseClass {

    ExtentTest test = Listeners.getExtentTest();
    WebDriver driver = getDriver();

    // Constructor to initialize elements with WebDriver instance
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Login']")
   private WebElement loginButton;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement mobileNumberfield;

    @FindBy(xpath = "//button[@type ='submit']/span[@class='mat-button-wrapper']")
    private WebElement getOtpButton;

    @FindBy(xpath = "//input[@id='otp']")
    private WebElement enterOtpField;

    @FindBy(xpath = "//span[text()='Submit OTP']")
    private WebElement submitOtp;

    @FindBy(xpath = "//img[@class=\"profile-hmbr\"]")
    private WebElement profilebutton;

    @FindBy(xpath = "//span[text()='Back']")
    private WebElement backButtonForNonKyc;

    @FindBy(xpath = "//span[text()=\"LogOut\"]")
    private WebElement logoutButton;


    public void clickonLogin(){
        CommonMethods.clickElement(loginButton,"login button",test);

    }
    public void enterMobileNumber(String mobileNumber){
        CommonMethods.sendKeysToElement(mobileNumberfield,mobileNumber, test);
    }

    public void getOtp(){
        CommonMethods.clickElement(getOtpButton,"get otp",test);

    }
    public void enterOtp(String otp){
        CommonMethods.sendKeysToElement(enterOtpField,otp, test);
    }
    public void submitOtp(){
        CommonMethods.clickElement(submitOtp,"submit otp", test);

    }
    public void clickonProfile(){
        CommonMethods.clickElement(profilebutton,"Profile button",test);
    }
    public void clickonLogoutButton(){
        CommonMethods.clickElement(logoutButton,"Logout button",test);
    }
    public void clickonBackButton(){
        CommonMethods.clickElement(backButtonForNonKyc,"back button",test);
    }

    public boolean isbackButtonDisplayed(){
        return backButtonForNonKyc.isDisplayed();
    }




}
