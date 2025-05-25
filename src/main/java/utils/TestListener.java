package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class TestListener extends TestListenerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test started: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: {}", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: {}", result.getName());
        try {
            AppiumDriver driver = utils.DriverUtils.getDriver();
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = "screenshots/" + result.getName() + ".png";
            FileUtils.copyFile(srcFile, new File(screenshotPath));
            logger.info("Screenshot saved to: {}", screenshotPath);
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: ", e);
        }
    }
} 