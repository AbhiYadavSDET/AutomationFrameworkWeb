package utilities;

import freemarker.cache.WebappTemplateLoader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


public class CommonMethods {

    private WebDriver driver;

    public CommonMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void takeScreenshot(String fileName,WebDriver driver) {
        // Take screenshot and save it as a file
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("./screenshots/" + fileName + ".png"));
            System.out.println("Screenshot saved at: ./screenshots/" + fileName + ".png");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
    }

    public void clickElement(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            // Handle any exceptions here
            e.printStackTrace();
        }
    }

    public void sendKeysToElement(WebElement element, String keys) {
        try {
            element.sendKeys(keys);
        } catch (Exception e) {
            // Handle any exceptions here
            e.printStackTrace();
        }
    }

    public void hoverOverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

   public WebElement fluentWait(By locator, int duration, int pollingWait){

        Wait<WebDriver> fluentWait =  new FluentWait<>(driver)
               .withTimeout(Duration.ofSeconds(duration))
               .pollingEvery(Duration.ofSeconds(pollingWait))
               .ignoring(NoSuchElementException.class);

        return fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));

   }

   public WebElement explicitWait(By locator, int duration){

        WebDriverWait explicitWait = new WebDriverWait(driver,Duration.ofSeconds(duration));


       return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));

   }
}
