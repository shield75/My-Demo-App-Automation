package testCases;

import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utilities.Data.LoginData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Epic("Regression Test")
@Feature("Full App Automation - Positive Flow")
public class AutomationTestCase extends BasePage {

    public final String prizeAscending = "Price-Ascending";
    public final String prizeDescending = "Price-Descending";
    public final String nameAscending = "Name-Ascending";
    public final String nameDescending = "Name-Descending";
    public int counter = 0;
    public int item = 0;


    @Test(description = "Full e-commerce flow: Login -> Sorting -> Cart -> Checkout -> Logout"
    , dataProvider = "loginData", dataProviderClass = LoginData.class)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Automate end-to-end purchase scenario with product sorting validation")
    @Owner("Anisur Rahman")
    @Tag("Regression")
    @Description("""
                 1. Open the app
                 2. Tap on the menu button
                 3.Tap on the Log out option
                 4. Tap on the Log Out button from the confirmation modal
                 5. Tap on the Ok button
                 6. Login with valid Username and Password
                 7. Verify that after successfully login checkout page appears
                 8. Tap on the menu button
                 9. Tap op the Catalog option
                 10. Tap on the sorting button
                 11. Select any sorting option
                 12. Verify that sorting functionality is working
                 13. Tap on the 2nd product
                 14. Verify that specific product page has appeared
                 15. Tap on the plus button for increasing the quantity = 3
                 16. Verify that quantity is showing the right value
                 17. Tap on the Add to Card button
                 18. Verify that correct number is showing cart badge
                 19. Navigate back to catalog page
                 20. Tap on the 3rd product
                 21. Verify that specific product page has appeared
                 22. Tap on the plus button for increasing the quantity = 2
                 23. Verify that quantity is showing the right value
                 24. Tap on the Add to Card button
                 25. Verify that correct number is showing cart badge
                 26. Navigate back to the Catalog page
                 27. Tap on the 5th product
                 28. Verify that specific product page has appeared
                 29. Tap on the Add to Cart button
                 30. Verify that correct number is showing cart badge
                 31. Tap on the cart icon
                 32. Verify that cart page has appeared
                 33. Verify that all the added products are showing with correct quantity
                 34. Decrease 1st product
                 35. Verify that quantity has decreased for the 1st product
                 36. Increase 3rd product
                 37. Verify that quantity has increased for the 3rd product
                 38. Remove the 2nd product
                 39. Verify that 2nd product has been removed
                 40. Verify total items and total price
                 41. Tap on the proceed to checkout button
                 42. Give valid shipping address information
                 43 Tap on the To Payment button
                 44. Give valid payment details
                 45. Tap on the Review order button
                 46. Verify that proper Delivery Address and Payment Details information is showing
                 47. Verify total items and total price(with Delivery Charge)
                 48. Tap on the Place Order button
                 49. Verify order confirmation
                 50. Log out
                 51. Verify that login page appeared after successfully log out
                 """)
    public void automationTestCase(String username, String password) throws InterruptedException {

        verifyLogin(username, password);
       /* verifySortingFunctionality(nameAscending);
        verifySortingFunctionality(nameDescending);
        verifySortingFunctionality(prizeAscending);
        verifySortingFunctionality(prizeDescending);*/
        verifyProductPage(2);
        verifyQuantity(3);
        verifyCartBadgeCount();
        verifyProductPage(3);
        verifyQuantity(2);
        verifyCartBadgeCount();
        verifyProductPage(4);
        verifyQuantity(1);
        verifyCartBadgeCount();
        verifyCartPage();
        verifyCartPageDetails();
        softAssert.assertAll();
    }

    @Step("Verify that after successfully login catalog page appears")
    public void verifyLogin(String username, String password){
        waitForElementVisibility(pages.getCatalogPage().openMenu, 10);
        fluentTapOnElement(pages.getCatalogPage().openMenu,5);
        fluentTapOnElement(pages.getCatalogPage().logOutOption,5);
        fluentTapOnElement(pages.getCatalogPage().confirmationModalLogOutButton,5);
        fluentTapOnElement(pages.getCatalogPage().okButton,5);

        inputText(pages.getLoginPage().usernameInputField, username);
        inputText(pages.getLoginPage().passwordInputField, password);
        fluentTapOnElement(pages.getLoginPage().loginButton,5);
        softAssert.assertTrue(isElementVisible(pages.getCheckOutPage().checkoutPageTitle), "Login failed");
    }

    @Step("Verify that sorting functionality is working")
    public void verifySortingFunctionality(String sortingOption) {
        fluentTapOnElement(pages.getCatalogPage().openMenu, 5);
        fluentTapOnElement(pages.getCatalogPage().catalogOption, 5);
        waitForElementVisibility(pages.getCatalogPage().sortButton, 5);
        fluentTapOnElement(pages.getCatalogPage().sortButton, 5);

        // Apply the selected sorting option
        switch (sortingOption.toLowerCase()) {
            case "name-ascending" -> fluentTapOnElement(pages.getCatalogPage().sortByNameAscending, 5);
            case "name-descending" -> fluentTapOnElement(pages.getCatalogPage().sortByNameDescending, 5);
            case "price-ascending" -> fluentTapOnElement(pages.getCatalogPage().sortByPriceAscending, 5);
            case "price-descending" -> fluentTapOnElement(pages.getCatalogPage().sortByPriceDescending, 5);
            default -> throw new IllegalArgumentException("Invalid sorting option: " + sortingOption);
        }

        // Capture the items after sorting
        List<String> itemNames = pages.getCatalogPage().storeItems
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        List<Double> itemPrices = pages.getCatalogPage().storeItemPrices
                .stream()
                .map(el -> parsePrice(el.getText()))
                .collect(Collectors.toList());

        // Verification based on a sorting option
        switch (sortingOption.toLowerCase()) {
            case "name-ascending" -> {
                List<String> expected = new ArrayList<>(itemNames);
                expected.sort(String.CASE_INSENSITIVE_ORDER);
                softAssert.assertEquals(itemNames, expected, "Name ascending sort failed!");
            }
            case "name-descending" -> {
                List<String> expected = new ArrayList<>(itemNames);
                expected.sort(String.CASE_INSENSITIVE_ORDER.reversed());
                softAssert.assertEquals(itemNames, expected, "Name descending sort failed!");
            }
            case "price-ascending" -> {
                List<Double> expected = new ArrayList<>(itemPrices);
                expected.sort(Double::compareTo);
                softAssert.assertEquals(itemPrices, expected, "Price ascending sort failed!");
            }
            case "price-descending" -> {
                List<Double> expected = new ArrayList<>(itemPrices);
                expected.sort(Collections.reverseOrder());
                softAssert.assertEquals(itemPrices, expected, "Price descending sort failed!");
            }
        }
    }

    @Step("Verify that specific product page has appeared")
    public void verifyProductPage(int productIndex){
        fluentTapOnElement(pages.getCatalogPage().openMenu, 5);
        fluentTapOnElement(pages.getCatalogPage().catalogOption, 5);
        //
        // fluentTapOnElement(pages.getCatalogPage().sortButton,5);
        //fluentTapOnElement(pages.getCatalogPage().sortByNameAscending,5);
        fluentTapOnElement(pages.getCatalogPage().storeItems.get(productIndex-1),5);
        waitForElementVisibility(pages.getProductPage().addToCartButton, 5);
        softAssert.assertTrue(isElementVisible(pages.getProductPage().addToCartButton), "Specific product details page is not visible");
    }

    @Step("Verify that quantity is showing the right value")
    public void verifyQuantity(int quantity){
        if (quantity > 1 ){
            for (int i = 1; i < quantity; i++) {
                fluentTapOnElement(pages.getProductPage().counterPlusButton,5);
            }
        }
        softAssert.assertEquals(pages.getProductPage().counterAmount.getText(), String.valueOf(quantity), "Quantity is not showing the right value");
        counter = counter + quantity;
    }

    @Step("Verify that correct number is showing cart badge")
    public void verifyCartBadgeCount(){
        fluentTapOnElement(pages.getProductPage().addToCartButton,5);
        item = item + 1;
        softAssert.assertEquals(getText(pages.getCommonElements().cartBadgeCount), String.valueOf(counter), "Cart badge is not showing the right value");
        navigateBack();
    }

    @Step("Verify that cart page has appeared")
    public void verifyCartPage(){
        fluentTapOnElement(pages.getCommonElements().cartBadge, 5);
        softAssert.assertTrue(isElementVisible(pages.getCartPage().cartPageTitle), "Cart page is not visible");
    }

    @Step("Verify that all the added products are showing with correct quantity")
    public void verifyCartPageDetails() throws InterruptedException {
        scrollDownHard();
        Thread.sleep(5000);
        softAssert.assertEquals(pages.getCartPage().productLabels.size(), item, "Not all added products are present in cart");
    }

}
