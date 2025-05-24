package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.HelperUtil;

public class WalletPage extends BasePage {

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
        okBtn.click();
        createWalletBtn.click();
        HelperUtil.assertVisibleText(driver, "Create passcode");
    }

    public void clickBackBtn() {
        backBtn.click();
        HelperUtil.assertVisibleElementByText(driver, "android.widget.TextView", "Create new wallet");
    }

    public void clickDeleteBtn() {
        deleteBtn.click();
    }

    public void enterPasscode(String passcode) {
        HelperUtil.enterPasscode(driver, passcode, "//android.widget.TextView[@text='%s']");
    }

    public void enterConfirmPasscode(String confirmPasscode) {
        HelperUtil.enterPasscode(driver, confirmPasscode, "//android.widget.TextView[@text='%s']");
    }

    public void handleNotificationsPrompt(boolean enable) {
        if (enable) {
            HelperUtil.assertVisibleText(driver, "Enable Notifications");
            enableNotificationsBtn.click();
        } else {
            HelperUtil.assertVisibleText(driver, "Enable Notifications");
            skipBtn.click();
        }
    }

}
