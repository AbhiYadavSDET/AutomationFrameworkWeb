package utilities;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ExtentReporterNG {

    private static ExtentReports extent;

    public static synchronized ExtentReports extentReportGenerator() {
        if (extent == null) {
            // Generate a timestamped file name for the report
            String timeStamp = new SimpleDateFormat("YYYY_MM_dd_HH_mm_ss").format(new Date());

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
            reporter.config().setTheme(Theme.STANDARD);
            reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

            // Additional configurations
            reporter.config().setTimelineEnabled(true); // Enable timeline view
            reporter.config().setOfflineMode(true); // Enable offline mode for viewing the report without internet
            reporter.config().setEncoding("UTF-8");

            // Ensure dashboard opens first
            reporter.viewConfigurer().viewOrder().as(
                    new ViewName[] {
                            ViewName.DASHBOARD,
                            ViewName.TEST,
                            ViewName.CATEGORY,
                            ViewName.AUTHOR,
                            ViewName.DEVICE,
                            ViewName.EXCEPTION,
                            ViewName.LOG
                    }).apply();

            // Initialize ExtentReports and attach the reporter
            extent = new ExtentReports();

            extent.attachReporter(reporter);
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("User Name", System.getProperty("user.name"));
            extent.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
        }

        return extent;
    }
}
