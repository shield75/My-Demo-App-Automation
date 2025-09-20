package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class CommonElements extends PageFactory{
    public CommonElements(AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"cart badge\"]/android.widget.TextView")
    public WebElement cartBadgeCount;

    @AndroidFindBy(accessibility = "cart badge")
    public WebElement cartBadge;

    @AndroidFindBy(accessibility = "total number")
    public WebElement totalItems;

    @AndroidFindBy(accessibility = "total price")
    public WebElement totalPrice;
}
