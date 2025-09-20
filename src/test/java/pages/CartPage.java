package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends PageFactory{
    public CartPage(AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"My Cart\"]")
    public WebElement cartPageTitle;

    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"counter minus button\"])")
    public List<WebElement> counterMinusButton;

    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"counter plus button\"])")
    public List<WebElement> counterPlusButton;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"Remove Item\"])")
    public List<WebElement> removeItemButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='product price']")
    public List<WebElement> productPrices;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='product label']")
    public List<WebElement> productLabels;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Proceed To Checkout\"]")
    public WebElement proceedToCheckoutButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"product label\" and @text=\"Sauce Labs Fleece Jacket\"]")
    public WebElement productLabel3;
}
