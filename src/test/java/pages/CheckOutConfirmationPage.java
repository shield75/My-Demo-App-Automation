package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class CheckOutConfirmationPage extends PageFactory{
    public CheckOutConfirmationPage(AndroidDriver driver) {
        super(driver);
    }

    public final String CHECKOUT_COMPLETE_MESSAGE = "Checkout Complete";

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Checkout Complete\"]")
    public WebElement checkoutCompleteMessage;

    @AndroidFindBy(accessibility = "Continue Shopping button")
    public WebElement continueShoppingButton;

}
