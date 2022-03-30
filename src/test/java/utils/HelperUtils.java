package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * HelperUtils class contains all the helper/util/common methods which are used for performing different
 * operations across all the UI pages.
 *
 * @author siddharth_patil
 */
public class HelperUtils {

    /**
     * This method takes amount with given currency symbol as a combined string retrieved as it is from the webPage
     * and then separates the amount from currency symbol, converts the string amount value into Float as returns.
     *
     * @param amountString : Amount with Currency in String format.
     * @return : Object of type {@link java.lang.Float}
     */
    public float getAmount(final String amountString) {
        String newAmount = amountString.replace('£', ' ').trim();
        String finalAmount = newAmount.replace("\"", "").trim();
        return Float.parseFloat(finalAmount);
    }

    /**
     * This method is used to retrieve the file text with multiple attempts.
     * Returns the text successfully if found in 10 attempts, or else returns a null value;
     *
     * @param by :
     * @return : Object of type {@link java.lang.String}
     * @throws ActionFailedException : Exception thrown if the text is not available on the UI.
     */
    public String getTextWithRetry(final By by) throws ActionFailedException {
        int numberOfAttempts = 0;
        String text;
        while (numberOfAttempts < 10) {
            text = SeleniumInitializer.driver.findElement(by).getText();
            if (null != text && !text.isEmpty())
                return text;
            numberOfAttempts++;
        }
        throw new ActionFailedException("The text is not available even after multiple retries.");
    }

    /**
     * This method performs a click operation for given element.
     * It also waits for 10 seconds until the element to be clickable.
     *
     * @param element    : WebElement
     * @param searchText : Text to be searched.
     */
    public void performSearchOperation(final By element, final String searchText) {
        waitUntilElementIsClickable(element, 10);
        SeleniumInitializer.driver.findElement(element).sendKeys(searchText);
    }

    /**
     * This method performs a click operation for given element.
     * It also waits for 10 seconds until the element to be clickable.
     *
     * @param element : element to be clicked.
     */
    public void performClickOperation(final By element) {
        waitUntilElementIsClickable(element, 10);
        SeleniumInitializer.driver.findElement(element).click();
    }

    /**
     * This method puts the execution on hold for given duration.
     * It waits for the given duration of time in seconds.
     *
     * @param durationInSeconds : Wait duration in seconds
     * @throws InterruptedException : Exception thrown {@link java.lang.InterruptedException}
     */
    public void waitForSomeTime(final int durationInSeconds) throws InterruptedException {
        Sleeper.SYSTEM_SLEEPER.sleep(Duration.ofSeconds(durationInSeconds));
    }

    /**
     * This method waits and checks if the element is clickable.
     * It waits for maximum given duration in seconds.
     *
     * @param by                : BY
     * @param durationInSeconds : Duration in seconds for which the element should be polled until it is clickable.
     */
    public void waitUntilElementIsClickable(final By by, final int durationInSeconds) {
        WebDriverWait wait = new WebDriverWait(SeleniumInitializer.driver, Duration.ofSeconds(durationInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(SeleniumInitializer.driver.findElement(by)));
    }

    /**
     * This method waits and checks if the element is visible/located.
     * It waits for maximum given duration in seconds.
     *
     * @param by                :
     * @param durationInSeconds : duration of wait
     */
    public void waitUntilElementVisible(final By by, final int durationInSeconds) {
        WebDriverWait wait = new WebDriverWait(SeleniumInitializer.driver, Duration.ofSeconds(durationInSeconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
