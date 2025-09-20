package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ReviewOrderPage extends PageFactory{
    public ReviewOrderPage (AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"checkout delivery address\"]/android.widget.TextView")
    public List<WebElement> deliveryAddressInformation;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"checkout payment info\"]/android.widget.TextView")
    public List<WebElement> paymentInformation;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"$5.99\"]")
    public WebElement totalPrice;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Place Order\"]")
    public WebElement placeOrderButton;



}
