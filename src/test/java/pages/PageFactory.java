package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.time.Duration;

public class PageFactory {
    AndroidDriver driver;

    public PageFactory(AndroidDriver driver){
        this.driver = driver;
        org.openqa.selenium.support.PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }
}
