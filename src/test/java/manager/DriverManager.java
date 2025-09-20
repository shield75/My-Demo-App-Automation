package manager;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import utilities.CapabilitiesReader;

import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class DriverManager {
    // Updated to use Appium 2.x default endpoint
    private static final String APPIUM_URL = "http://127.0.0.1:4723";
    private static final ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

    public static AndroidDriver getDriver() {
        if (driver.get() == null) {
            try {
                Properties props = CapabilitiesReader.getAllProperties();
                
                // Create UiAutomator2Options with mandatory capabilities
                UiAutomator2Options options = new UiAutomator2Options()
                    .setDeviceName(props.getProperty("deviceName"))
                    .setUdid(props.getProperty("udid"))
                    .setAppPackage(props.getProperty("appPackage"))
                    .setAppActivity(props.getProperty("appActivity"))
                    .setAutomationName(props.getProperty("automationName"));

                // Create new endpoint for Appium 2.x
                URL appiumEndpoint = new URL(APPIUM_URL + "/wd/hub");
                
                // Initialize driver with explicit wait
                driver.set(new AndroidDriver(appiumEndpoint, options));
                driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                
                System.out.println("Driver initialized successfully");
                
            } catch (Exception e) {
                System.err.println("Failed to initialize driver: " + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException("Could not start new session", e);
            }
        }
        return driver.get();
    }

    public static void quitDriver() {
        try {
            if (driver.get() != null) {
                driver.get().quit();
                driver.remove();
                System.out.println("Driver terminated successfully");
            }
        } catch (Exception e) {
            System.err.println("Error while quitting driver: " + e.getMessage());
        }
    }
}