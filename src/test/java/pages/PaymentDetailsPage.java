package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class PaymentDetailsPage extends PageFactory{
    public PaymentDetailsPage(AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBy(accessibility = "Full Name* input field")
    public WebElement fullNameInputField;

    @AndroidFindBy(accessibility = "Card Number* input field")
    public WebElement cardNumberInputField;

    @AndroidFindBy(accessibility = "Expiration Date* input field")
    public WebElement expirationDateInputField;

    @AndroidFindBy(accessibility = "Security Code* input field")
    public WebElement securityCodeInputField;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Review Order\"]")
    public WebElement reviewOrderButton;

}
