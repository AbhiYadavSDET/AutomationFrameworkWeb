package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

    private ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private ExtentReports extent = ExtentReporterNG.extentReportGenerator();
    private ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        System.out.println("Test started: " + result.getMethod().getMethodName());
    }
    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());
        System.out.println("Test failed: " + result.getMethod().getMethodName());

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
