package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ProductPage extends PageFactory{
    public ProductPage(AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"counter plus button\"]/android.widget.ImageView")
    public WebElement counterPlusButton;

    @AndroidFindBy(accessibility = "Add To Cart button")
    public WebElement addToCartButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"counter amount\"]/android.widget.TextView")
    public WebElement counterAmount;



}
