package utilities.Data;

import org.testng.annotations.DataProvider;

public class LoginData {
    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {
        return new Object[][] {
                {"bob@example.com", "10203040"},
        };
    }
}
