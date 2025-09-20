package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageFactory{

    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"Username input field\"]")
    public WebElement usernameInputField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"Password input field\"]")
    public WebElement passwordInputField;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Login button\"]")
    public WebElement loginButton;
}
