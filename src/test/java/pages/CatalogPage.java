package pages;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;


public class CatalogPage extends PageFactory {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"open menu\"]/android.widget.ImageView")
    public WebElement openMenu;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"menu item catalog\"]")
    public WebElement catalogOption;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"menu item log out\"]")
    public WebElement logOutOption;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"android:id/button1\"]")
    public WebElement confirmationModalLogOutButton;

    @AndroidFindBy(id = "android:id/button1")
    public WebElement okButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"sort button\"]/android.widget.ImageView")
    public WebElement sortButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Name - Ascending\"]")
    public WebElement sortByNameAscending;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Name - Descending\"]")
    public WebElement sortByNameDescending;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Price - Ascending\"]")
    public WebElement sortByPriceAscending;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Price - Descending\"]")
    public WebElement sortByPriceDescending;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='store item text']")
    public List<WebElement> storeItems;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"store item price\"]")
    public List<WebElement> storeItemPrices;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Products\"]")
    public WebElement productsTab;

    public CatalogPage(AndroidDriver driver) {
        super(driver);
    }
}
