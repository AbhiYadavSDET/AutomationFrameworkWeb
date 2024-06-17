package utilities;

import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

public class CommonMethods {

    public static void navigateToUrl(String url, ExtentTest test, WebDriver driver) {
        try {
            driver.get(url);
            test.info("Navigated to " + url);
            System.out.println("Navigated to " + url);
        } catch (Exception e) {
            test.fail("Failed to navigate to " + url + ". Exception: " + e.getMessage());
            System.out.println("Failed to navigate to " + url + ". Exception: " + e.getMessage());
        }
    }

    public static void clickElement(WebElement element, String elementName, ExtentTest test) {
        try {
            element.click();
            test.info("Clicked on element: " + elementName);
        } catch (Exception e) {
            test.fail("Failed to click on element '" + elementName + "': " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void sendKeysToElement(WebElement element, String keys, ExtentTest test) {
        try {
            element.sendKeys(keys);
            test.info( "Enetered data : "+keys);
        } catch (Exception e) {
            test.fail("Failed to send keys to element: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void hoverOverElement(WebElement element, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public static WebElement fluentWait(By locator, int duration, int pollingWait, WebDriver driver) {
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(duration))
                .pollingEvery(Duration.ofSeconds(pollingWait))
                .ignoring(NoSuchElementException.class);
        return fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement explicitWait(By locator, int duration, WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static String getScreenshotAsBase64(WebDriver driver) throws IOException {
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String fileName = "screenshot_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()) + ".png";

        String path = System.getProperty("user.dir")+"/screenshots/"+fileName;
        FileUtils.copyFile(src,new File(path));

        byte [] imageBytes = IOUtils.toByteArray((new FileInputStream(path)));
        return Base64.getEncoder().encodeToString(imageBytes);
    }


    public void dragAndDrop(WebDriver driver,WebElement source,WebElement destination) throws InterruptedException {

        Actions action = new Actions(driver);
        // Perform the drag and drop action
        action.dragAndDrop(source, destination).build().perform();
    }
}
