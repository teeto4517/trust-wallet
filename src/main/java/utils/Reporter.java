package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.appium.java_client.AppiumDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporter implements ITestListener {
    private static final ExtentReports extent = new ExtentReports();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final String SCREENSHOT_DIR = "test-output/screenshots/";
    private static boolean systemInfoSet = false;

    static {
        // Create screenshots directory if it doesn't exist
        try {
            Files.createDirectories(Paths.get(SCREENSHOT_DIR));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Configure ExtentSparkReporter
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/extent-report.html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Trust Wallet Test Report");
        spark.config().setReportName("Trust Wallet Automation Results");
        spark.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");
        extent.attachReporter(spark);
    }

    private static void setSystemInfoFromDriver() {
        if (systemInfoSet) return;
        try {
            AppiumDriver driver = utils.DriverUtils.getDriver();
            if (driver != null) {
                String platformName = String.valueOf(driver.getCapabilities().getCapability("platformName"));
                String deviceName = String.valueOf(driver.getCapabilities().getCapability("deviceName"));
                String platformVersion = String.valueOf(driver.getCapabilities().getCapability("platformVersion"));
                String automationName = String.valueOf(driver.getCapabilities().getCapability("automationName"));
                String appPackage = String.valueOf(driver.getCapabilities().getCapability("appPackage"));
                extent.setSystemInfo("Platform Name", platformName);
                extent.setSystemInfo("Device Name", deviceName);
                extent.setSystemInfo("Platform Version", platformVersion);
                extent.setSystemInfo("Automation Name", automationName);
                extent.setSystemInfo("App Package", appPackage);
                systemInfoSet = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        setSystemInfoFromDriver();
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName())
                .assignCategory(result.getTestClass().getRealClass().getSimpleName());
        test.set(extentTest);
        logInfo("Starting test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test failed: " + result.getMethod().getMethodName());
        test.get().log(Status.FAIL, "Error: " + result.getThrowable());
        
        // Capture screenshot on failure
        try {
            String screenshotPath = captureScreenshot(result.getMethod().getMethodName());
            test.get().addScreenCaptureFromPath(screenshotPath);
        } catch (IOException e) {
            logError("Failed to capture screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
        test.get().log(Status.SKIP, "Reason: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    private String captureScreenshot(String testName) throws IOException {
        AppiumDriver driver = DriverUtils.getDriver();
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = SCREENSHOT_DIR + testName + "_" + timestamp + ".png";
        Files.copy(srcFile.toPath(), Paths.get(screenshotPath));
        return screenshotPath;
    }

    // Custom log methods
    public static void logInfo(String message) {
        test.get().log(Status.INFO, message);
    }

    public static void logError(String message) {
        test.get().log(Status.FAIL, message);
    }

    public static void logWarning(String message) {
        test.get().log(Status.WARNING, message);
    }

    public static void logStep(int stepNumber, String stepDescription) {
        test.get().log(Status.INFO, "Step " + stepNumber + ": " + stepDescription);
    }

    // Method to add test data to report
    public static void addTestData(String key, String value) {
        test.get().info("Test Data - " + key + ": " + value);
    }
} 