package utilities;

import base.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners implements ITestListener {

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static ExtentReports extent = ExtentReporterNG.extentReportGenerator();

    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        test.info("Starting test");
        extentTest.set(test);
        System.out.println("Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());
        System.out.println("Test failed: " + result.getMethod().getMethodName());
        try {
            String screenshotBase64 = CommonMethods.getScreenshotAsBase64(BaseClass.getDriver());
            extentTest.get().fail("Screenshot of failure",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
        System.out.println("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
        System.out.println("Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println("All tests finished.");
    }
}
