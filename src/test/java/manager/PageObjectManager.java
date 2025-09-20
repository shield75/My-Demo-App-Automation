package manager;

import io.appium.java_client.android.AndroidDriver;
import pages.*;

public class PageObjectManager {

    private final AndroidDriver driver;

    // Page objects
    private LoginPage loginPage;
    private CatalogPage catalogPage;
    private CheckOutPage checkOutPage;
    private PaymentDetailsPage paymentDetailsPage;
    private CartPage cartPage;
    private ReviewOrderPage reviewOrderPage;
    private CommonElements commonElements;
    private ProductPage productPage;
    private CheckOutConfirmationPage checkOutConfirmationPage;


    // Constructor
    public PageObjectManager(AndroidDriver driver) {
        this.driver = driver;
    }

    // Getter for LoginPage
    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    // Getter for CatalogPage
    public CatalogPage getCatalogPage() {
        if (catalogPage == null) {
            catalogPage = new CatalogPage(driver);
        }
        return catalogPage;
    }

    // Getter for CheckOutPage
    public CheckOutPage getCheckOutPage() {
        if (checkOutPage == null) {
            checkOutPage = new CheckOutPage(driver);
        }
        return checkOutPage;
    }

    // Getter for PaymentDetailsPage
    public PaymentDetailsPage getPaymentDetailsPage() {
        if (paymentDetailsPage == null) {
            paymentDetailsPage = new PaymentDetailsPage(driver);
        }
        return paymentDetailsPage;
    }

    //Getter for cart package
    public CartPage getCartPage(){
        if(cartPage == null){
            cartPage = new CartPage(driver);
        }
        return cartPage;
    }

    //Getter for reviewOrderPage
    public ReviewOrderPage getReviewOrderPage(){
        if(reviewOrderPage == null){
            reviewOrderPage = new ReviewOrderPage(driver);
        }
        return reviewOrderPage;
    }

    //Getter for commonElementsPage
    public CommonElements getCommonElements(){
        if(commonElements == null){
            commonElements = new CommonElements(driver);
        }
        return commonElements;
    }

    //Getter for productPage
    public ProductPage getProductPage(){
        if(productPage == null){
            productPage = new ProductPage(driver);
        }
        return productPage;
    }

    //Getter for CheckoutConfirmationPage
    public CheckOutConfirmationPage getCheckOutConfirmationPage(){
        if(checkOutConfirmationPage == null){
            checkOutConfirmationPage = new CheckOutConfirmationPage(driver);
        }
        return checkOutConfirmationPage;
    }
}
