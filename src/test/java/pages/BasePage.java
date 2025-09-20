package pages;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import manager.DriverManager;
import manager.PageObjectManager;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.HashMap;

public class BasePage extends DriverManager {

    protected AndroidDriver driver;
    protected PageObjectManager pages;
    protected SoftAssert softAssert = new SoftAssert();

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        // Initialize driver
        driver = getDriver();

        // Initialize PageObjectManager with a driver
        pages = new PageObjectManager(driver);
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        // Quit driver after each test
        DriverManager.quitDriver();
    }

    protected void waitForElementVisibility(WebElement element, long timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            throw new TimeoutException("Element not visible after " + timeoutInSeconds + " seconds: " + element, e);
        }
    }

    protected void fluentTapOnElement(WebElement element, long timeoutInSeconds) {
        int attempts = 0;
        Exception lastException = null;

        while (attempts < 10) {
            try {
                // Wait for an element to be visible and clickable
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
                wait.until(ExpectedConditions.visibilityOf(element));
                wait.until(ExpectedConditions.elementToBeClickable(element));

                // Perform the tap
                element.click();
                return; // Success, exit method

            } catch (StaleElementReferenceException e) {
                lastException = e;
                // Element became stale, retry
            } catch (ElementClickInterceptedException e) {
                lastException = e;
                // Element was intercepted, wait briefly and retry
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            } catch (TimeoutException e) {
                lastException = e;
                // Element not clickable within timeout, retry
            }
            attempts++;
        }
        // If we get here, all attempts failed
        throw new RuntimeException("Failed to tap element after " + 10 +
                " attempts. Last error: " + (lastException != null ? lastException.getMessage() : "Unknown"), lastException);
    }

    protected void inputText(WebElement element, String text) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            throw new RuntimeException("Failed to input text: " + text + " into element: " + element, e);
        }
    }

    protected boolean isElementVisible(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOf(element)) != null;
        } catch (Exception e) {
            return false;
        }
    }

    protected Double parsePrice(String text) {
        try {
            // Remove $ and any non-digit/non-dot characters
            String cleaned = text.replaceAll("[^0-9.]", "");
            return Double.parseDouble(cleaned);
        } catch (Exception e) {
            return 0.0;
        }
    }

    protected void navigateBack() {
        try {
            driver.navigate().back();
            // Small wait to allow animation to complete
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to navigate back", e);
        }
    }


    protected String getText(WebElement element) {
        try {
            waitForElementVisibility(element, 10);
            return element.getText().trim();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get text from element: " + element, e);
        }
    }

    protected void scrollDownUntilElementVisible(WebElement element) {
        int maxAttempts = 10;
        int attempts = 0;
        int screenHeight = driver.manage().window().getSize().height;

        while (!isElementVisible(element) && attempts < maxAttempts) {
            // Swipe up a fraction of the screen
            int startX = screenHeight / 2;
            int startY = (int) (screenHeight * 0.7);
            int endY = (int) (screenHeight * 0.3);

            driver.executeScript("mobile: swipeGesture", ImmutableMap.of(
                    "left", startX,
                    "top", startY,
                    "width", 10,
                    "height", Math.abs(startY - endY),
                    "direction", "up",
                    "percent", 0.7
            ));
            attempts++;
        }

        if (!isElementVisible(element)) {
            throw new RuntimeException("Element not visible after scrolling");
        }

        // Nudge the element to bottom
        int elementY = element.getLocation().getY();
        int elementHeight = element.getSize().getHeight();
        int moveDistance = screenHeight - elementY - elementHeight - 10; // 10px margin

        if (moveDistance > 0) {
            driver.executeScript("mobile: swipeGesture", ImmutableMap.of(
                    "left", screenHeight / 2,
                    "top", elementY,
                    "width", 10,
                    "height", moveDistance,
                    "direction", "down",
                    "percent", 0.5
            ));
        }
    }

    public void scrollDownHard() {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;

        int startX = width / 2;
        int startY = (int) (height * 0.8);
        int endY = (int) (height * 0.2);

        HashMap<String, Object> args = new HashMap<>();
        args.put("left", 0);            // entire screen width
        args.put("top", 0);             // entire screen height
        args.put("width", width);
        args.put("height", height);
        args.put("startX", startX);
        args.put("startY", startY);
        args.put("endX", startX);
        args.put("endY", endY);
        args.put("direction", "up");
        args.put("percent", 0.75);

        driver.executeScript("mobile: swipeGesture", args);
    }
}
