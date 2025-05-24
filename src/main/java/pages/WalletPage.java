package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.HelperUtil;
import utils.Log;

public class WalletPage extends BasePage {

    private static final Log log = Log.getLogger(WalletPage.class);

    @FindBy(id = "android:id/button1")
    private WebElement okBtn;

    @FindBy(xpath = "//android.view.View[@resource-id='CreateNewWalletButton']")
    private WebElement createWalletBtn;

    @FindBy(xpath = "//android.view.View[@content-desc='Back']") 
    private WebElement backBtn;

    @FindBy(xpath = "//android.view.View[@index='11']")
    private WebElement deleteBtn;

    @FindBy(xpath = "//android.widget.TextView[@resource-id='buttonTitle']")
    private WebElement enableNotificationsBtn;

    @FindBy(xpath = "//android.widget.TextView[@resource-id='secondaryAction']")
    private WebElement skipBtn;

    public void clickCreateWalletBtn() {
        log.info("Clicking OK button");
        okBtn.click();
        log.info("Clicking Create Wallet button");
        createWalletBtn.click();
        log.info("Asserting 'Create passcode' text is visible");
        HelperUtil.assertVisibleText(driver, "Create passcode");
    }

    public void clickBackBtn() {
        log.info("Clicking Back button");
        backBtn.click();
        log.info("Asserting 'Create new wallet' button is visible");
        HelperUtil.assertVisibleElementByText(driver, "android.widget.TextView", "Create new wallet");
    }

    public void clickDeleteBtn() {
        log.info("Clicking Delete button");
        deleteBtn.click();
    }

    public void enterPasscode(String passcode) {
        log.info("Entering passcode");
        HelperUtil.enterPasscode(driver, passcode, "//android.widget.TextView[@text='%s']");
    }

    public void enterConfirmPasscode(String confirmPasscode) {
        log.info("Entering confirm passcode");
        HelperUtil.enterPasscode(driver, confirmPasscode, "//android.widget.TextView[@text='%s']");
    }

    public void handleNotificationsPrompt(boolean enable) {
        log.info("Handling notifications prompt. Enable: " + enable);
        HelperUtil.assertVisibleText(driver, "Enable Notifications");
        if (enable) {
            log.info("Clicking Enable Notifications button");
            enableNotificationsBtn.click();
        } else {
            log.info("Clicking Skip button");
            skipBtn.click();
        }
    }
}
