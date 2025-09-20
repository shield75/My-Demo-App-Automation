package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class CheckOutPage extends PageFactory{
    public CheckOutPage(AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Checkout\"]")
    public WebElement checkoutPageTitle;

    @AndroidFindBy(accessibility = "Full Name* input field")
    public String fullNameInputField;

    @AndroidFindBy(accessibility = "Address Line 1* input field")
    public String address1InputField;

    @AndroidFindBy(accessibility = "Address Line 2 input field")
    public String address2InputField;

    @AndroidFindBy(accessibility = "City* input field")
    public String cityInputField;

    @AndroidFindBy(accessibility = "State/Region input field")
    public String stateRegionInputField;

    @AndroidFindBy(accessibility = "Zip Code* input field")
    public String zipCodeInputField;

    @AndroidFindBy(accessibility = "Country* input field")
    public String countryInputField;

    @AndroidFindBy(accessibility = "To Payment button")
    public String toPaymentButton;

}
