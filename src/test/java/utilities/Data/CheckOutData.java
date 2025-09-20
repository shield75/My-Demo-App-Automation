package utilities.Data;

import org.testng.annotations.DataProvider;

public class CheckOutData {
    @DataProvider(name = "checkoutData")
    public static Object[][] getCheckoutData() {
        return new Object[][] {
                {
                        "John Doe",           // Full Name
                        "123 Main Street",    // Address Line 1
                        "Apt 4B",             // Address Line 2
                        "New York",           // City
                        "NY",                 // State/Region
                        "10001",              // Zip Code
                        "USA"                 // Country
                }
        };
    }
}
