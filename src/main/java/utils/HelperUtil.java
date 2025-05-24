package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

public class HelperUtil {

    private static WebDriverWait getWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static WebElement waitForElementToBeVisible(WebDriver driver, By locator) {
        return getWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator) {
        return getWait(driver).until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Clicks each digit of the passcode using a locator template (e.g., "//android.widget.Button[@text='%s']")
    public static void enterPasscode(WebDriver driver, String passcode, String numberButtonXpathTemplate) {
        for (char digit : passcode.toCharArray()) {
            By locator = By.xpath(String.format(numberButtonXpathTemplate, digit));
            waitForElementToBeClickable(driver, locator).click();
        }
    }

    public static void clickButtonByText(WebDriver driver, String buttonText) {
        By locator = By.xpath(String.format("//android.widget.Button[@text='%s']", buttonText));
        waitForElementToBeClickable(driver, locator).click();
    }

    public static void assertVisibleText(WebDriver driver, String text) {
        By locator = By.xpath(String.format("//*[contains(@text, '%s') or contains(@label, '%s') or contains(@content-desc, '%s')]", text, text, text));
        WebElement element = waitForElementToBeVisible(driver, locator);
        if (element == null || !element.isDisplayed()) {
            throw new AssertionError("Text not visible: " + text);
        }
    }


    public static void assertVisibleElementByText(WebDriver driver, String elementType, String text) {
        By locator = By.xpath(String.format("//%s[@text='%s']", elementType, text));
        WebElement element = waitForElementToBeVisible(driver, locator);
        if (element == null || !element.isDisplayed()) {
            throw new AssertionError(elementType + " with text not visible: " + text);
        }
    }
}
