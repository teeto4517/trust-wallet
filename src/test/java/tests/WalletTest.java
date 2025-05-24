package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.WalletPage;
import utils.HelperUtil;
import io.appium.java_client.AppiumDriver;
import utils.DriverUtils;

public class WalletTest extends BaseTest {

    private WalletPage walletPage;
    private AppiumDriver driver;

    @BeforeMethod
    public void setUpTest() {
        driver = DriverUtils.getDriver();
        walletPage = new WalletPage();
    }

    @Test(description = "Create new wallet with notifications enabled")
    public void testCreateNewWallet() {
        walletPage.clickCreateWalletBtn();
        walletPage.enterPasscode("123456");
        walletPage.enterConfirmPasscode("123456");
        walletPage.handleNotificationsPrompt(true);
        HelperUtil.clickButtonByText(driver, "Allow");
        HelperUtil.assertVisibleText(driver, "Brilliant, your wallet is ready!");
    }

    @Test(description = "Create new wallet with notifications skipped")
    public void testCreateNewWalletWithNotificationsSkipped() {
        walletPage.clickCreateWalletBtn();
        walletPage.enterPasscode("123456");
        walletPage.enterConfirmPasscode("123456");
        walletPage.handleNotificationsPrompt(false);
        HelperUtil.assertVisibleText(driver, "Brilliant, your wallet is ready!");
    }

    @Test(description = "Verify Back button functionality")
    public void testBackButtonFunctionality() {
        walletPage.clickCreateWalletBtn();
        walletPage.clickBackBtn();
    }

    @Test(description = "Verify Delete button functionality")
    public void testDeleteButtonFunctionality() {
        walletPage.clickCreateWalletBtn();
        walletPage.enterPasscode("1234");
        walletPage.clickDeleteBtn();
        HelperUtil.assertVisibleText(driver, "Create passcode");
    }
    
} 