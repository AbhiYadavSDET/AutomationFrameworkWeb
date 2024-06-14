package utilities;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    private static ExtentReports extent;

    public static synchronized ExtentReports extentReportGenerator() {
        if (extent == null) {
            // Generate a timestamped file name for the report
            String timeStamp = new SimpleDateFormat("dd_MM_YYYY_HH_mm_ss").format(new Date());
            String fileName = "ExtentReport_" + timeStamp + ".html";

            // Custom path for Extent report directory inside the project structure
            String reportDirectory = System.getProperty("user.dir") + "/testReport/";

            // Creating testReport directory if it doesn't exist
            File directory = new File(reportDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Set the file path for the report
            String reportFilePath = reportDirectory + fileName;

            // Initialize ExtentSparkReporter with the report file path
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportFilePath);
            reporter.config().setReportName("Web Automation Testing Report");
            reporter.config().setDocumentTitle("Test Cases Result");

            // Initialize ExtentReports and attach the reporter
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Abhishek Yadav");
        }

        return extent;
    }
}
