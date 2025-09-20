package utilities.Data;

import org.testng.annotations.DataProvider;

public class PaymentData {
    @DataProvider(name = "paymentData")
    public static Object[][] getPaymentData() {
        return new Object[][] {
                {
                        "John Doe",          // Full Name
                        "4111111111111111",  // Card Number (fake Visa test card)
                        "12/28",             // Expiration Date
                        "123"                // Security Code (CVV)
                }
        };
    }
}
