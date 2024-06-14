package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ExtentReports extent;

    @BeforeSuite
    public void setUpSuite() {
        initializeExtentReport();
    }

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver.set(new ChromeDriver());
        System.out.println("Initialized WebDriver for thread: " + Thread.currentThread().getId());
        getDriver().manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            System.out.println("Quit WebDriver for thread: " + Thread.currentThread().getId());
        }
        driver.remove();
    }

    @AfterSuite
    public void tearDownSuite() {
        flushExtentReport();
    }

    protected WebDriver getDriver() {
        return driver.get();
    }

    private void initializeExtentReport() {
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Testing");
        reporter.config().setDocumentTitle("Test Case Results");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Abhishek Yadav");
    }

    private void flushExtentReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
