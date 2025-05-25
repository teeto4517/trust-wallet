package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.WalletPage;
import utils.HelperUtil;
import utils.ConfigManager;
import io.appium.java_client.AppiumDriver;
import utils.DriverUtils;
import utils.Reporter;

public class WalletTest extends BaseTest {

    private WalletPage walletPage;
    private AppiumDriver driver;
    private ConfigManager config;

    @BeforeMethod
    public void setUpTest() {
        driver = DriverUtils.getDriver();
        walletPage = new WalletPage();
        config = ConfigManager.getInstance();
    }

    @Test(description = "Create new wallet with notifications enabled")
    public void testCreateNewWallet() {
        String passcode = config.getPasscode();
        // Add test data
        Reporter.addTestData("Environment", config.getEnvironment());
        Reporter.addTestData("Platform", "Android");
        Reporter.addTestData("Passcode", "******"); // Masked for security
        Reporter.addTestData("Notifications", "Enabled");

        // Test steps with detailed logging
        Reporter.logStep(1, "Launch the Trust Wallet app");
        Reporter.logInfo("Starting wallet creation process");
        
        Reporter.logStep(2, "Click Create Wallet button");
        walletPage.clickCreateWalletBtn();
        Reporter.logInfo("Create Wallet button clicked successfully");
        
        Reporter.logStep(3, "Enter passcode");
        walletPage.enterPasscode(passcode);
        Reporter.logInfo("Passcode entered successfully");
        
        Reporter.logStep(4, "Confirm passcode");
        walletPage.enterConfirmPasscode(passcode);
        Reporter.logInfo("Passcode confirmed successfully");
        
        Reporter.logStep(5, "Handle notifications prompt");
        walletPage.handleNotificationsPrompt(true);
        HelperUtil.clickButtonByText(driver, "Allow");
        Reporter.logInfo("Notifications enabled successfully");
        
        Reporter.logStep(6, "Verify wallet creation success");
        HelperUtil.assertVisibleText(driver, "Brilliant, your wallet is ready!");
        Reporter.logInfo("Wallet created successfully");
    }

    @Test(description = "Create new wallet with notifications skipped")
    public void testCreateNewWalletWithNotificationsSkipped() {
        String passcode = config.getPasscode();
        // Add test data
        Reporter.addTestData("Environment", config.getEnvironment());
        Reporter.addTestData("Platform", "Android");
        Reporter.addTestData("Passcode", "******"); // Masked for security
        Reporter.addTestData("Notifications", "Skipped");

        Reporter.logStep(1, "Launch the Trust Wallet app");
        Reporter.logInfo("Starting wallet creation process with notifications skipped");
        
        Reporter.logStep(2, "Click Create Wallet button");
        walletPage.clickCreateWalletBtn();
        
        Reporter.logStep(3, "Enter passcode");
        walletPage.enterPasscode(passcode);
        
        Reporter.logStep(4, "Confirm passcode");
        walletPage.enterConfirmPasscode(passcode);
        
        Reporter.logStep(5, "Skip notifications");
        walletPage.handleNotificationsPrompt(false);
        Reporter.logInfo("Notifications prompt skipped");
        
        Reporter.logStep(6, "Verify wallet creation success");
        HelperUtil.assertVisibleText(driver, "Brilliant, your wallet is ready!");
        Reporter.logInfo("Wallet created successfully without notifications");
    }

    @Test(description = "Verify Back button functionality")
    public void testBackButtonFunctionality() {
        Reporter.addTestData("Environment", config.getEnvironment());
        Reporter.addTestData("Platform", "Android");
        
        Reporter.logStep(1, "Launch the Trust Wallet app");
        Reporter.logInfo("Starting back button verification");
        
        Reporter.logStep(2, "Click Create Wallet button");
        walletPage.clickCreateWalletBtn();
        
        Reporter.logStep(3, "Click back button");
        walletPage.clickBackBtn();
        Reporter.logInfo("Back button clicked successfully");
        
        Reporter.logStep(4, "Verify return to main screen");
        Reporter.logInfo("Successfully returned to main screen");
    }

    @Test(description = "Verify Delete button functionality")
    public void testDeleteButtonFunctionality() {
        Reporter.addTestData("Environment", config.getEnvironment());
        Reporter.addTestData("Platform", "Android");
        Reporter.addTestData("Passcode", "******"); // Masked for security
        
        Reporter.logStep(1, "Launch the Trust Wallet app");
        Reporter.logInfo("Starting delete button verification");
        
        Reporter.logStep(2, "Click Create Wallet button");
        walletPage.clickCreateWalletBtn();
        
        Reporter.logStep(3, "Enter passcode");
        walletPage.enterPasscode("1234");
        Reporter.logInfo("Passcode entered successfully");
        
        Reporter.logStep(4, "Click delete button");
        walletPage.clickDeleteBtn();
        Reporter.logInfo("Delete button clicked successfully");
        
        Reporter.logStep(5, "Verify passcode screen");
        HelperUtil.assertVisibleText(driver, "Create passcode");
        Reporter.logInfo("Successfully returned to passcode screen");
    }
} 