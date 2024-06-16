package testcases;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DragAndDrop extends BaseClass {
    SoftAssert sf = new SoftAssert();

    @Test
    public void dragAndDrop() throws InterruptedException {
        // Navigate to the drag and drop demo page
        getDriver().get("https://demo.guru99.com/test/drag_drop.html");

        // Initialize Actions class to perform drag and drop
        Actions action = new Actions(getDriver());

        // Locate the source and destination elements
        WebElement source = getDriver().findElement(By.xpath("//li[@id = 'credit2']"));
        WebElement destination = getDriver().findElement(By.xpath("//ol[@id='bank']"));

        // Perform the drag and drop action
        action.dragAndDrop(source, destination).perform(); // Ensure perform() is called

        Thread.sleep(5000);

        sf.assertEquals(1,5);
        sf.assertEquals(1,6);
        sf.assertEquals(1,1);

        sf.assertAll();

    }
}
