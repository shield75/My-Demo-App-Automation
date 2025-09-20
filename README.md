# My Demo App — Mobile Test Automation (Appium + TestNG)

End-to-end mobile UI test automation project for the Sauce Labs "My Demo App" (React Native) Android application. The suite automates a positive purchase flow and related verifications such as login, catalog sorting, cart management, checkout, payment details, and order review. Reporting is powered by Allure.

## Stack
- Language: Java (source/target 16)
- Build tool / package manager: Maven
- Test framework: TestNG
- Mobile automation: Appium Java Client (Android, UiAutomator2)
- WebDriver: Selenium (transitively used with Appium)
- Reporting: Allure (allure-testng + allure-maven)
- Logging: SLF4J (simple backend in test scope)

## Requirements
- Java JDK 16+ (JAVA_HOME configured)
- Maven 3.8+
- Android SDK installed; ANDROID_HOME or ANDROID_SDK_ROOT configured
  - Android platform-tools in PATH (adb available)
- Appium Server 2.x running locally
  - UiAutomator2 driver installed for Appium 2 (e.g., `appium driver install uiautomator2`)
- A connected Android device or emulator matching the capabilities configured in `src/test/resources/capabilities.properties`
- Internet connectivity for dependency download on first run

## Project Structure
- pom.xml — Maven project configuration (dependencies, plugins, TestNG suite path)
- src/main/java/org/example — Placeholder (no production code used by tests yet)
- src/test/java
  - manager
    - DriverManager.java — Creates and manages a ThreadLocal AndroidDriver (Appium 2.x, UiAutomator2)
    - PageObjectManager.java — Lazy-initialized Page Object registry
  - pages — Page Object Model classes + BasePage with common utilities and TestNG lifecycle (@BeforeSuite/@AfterTest)
  - testCases
    - AutomationTestCase.java — Main end-to-end/regression scenario using data provider
  - utilities
    - CapabilitiesReader.java — Loads `capabilities.properties`
    - Data/LoginData.java — TestNG @DataProvider for login credentials
- src/test/resources
  - capabilities.properties — Device/App under test capabilities (platformName, udid, deviceName, appPackage, appActivity, automationName)
  - testng.xml — TestNG suite descriptor (referenced by surefire plugin)
- allure-results — Allure raw results (generated after a run)
- target — Maven build output (classes, test-classes, allure site if generated)

## Configuration
- src/test/resources/capabilities.properties controls Appium session capabilities:
  - platformName=Android
  - udid=<your_device_udid>
  - deviceName=<your_device_name>
  - appPackage=com.saucelabs.mydemoapp.rn
  - appActivity=com.saucelabs.mydemoapp.rn.MainActivity
  - automationName=UiAutomator2

Notes:
- The Appium server endpoint is currently hardcoded as `http://127.0.0.1:4723/wd/hub` in `manager/DriverManager.java`.
- You can change any capability via the properties file without recompiling.

TODO:
- Make Appium server URL configurable via system property or environment variable instead of hardcoding.
- Consider adding support for setting `platformVersion`, `app` (APK path), timeouts, etc., via properties.

## How to Run
1) Start prerequisites
- Ensure an Android device/emulator is online (`adb devices` should list it) and matches `capabilities.properties`.
- Start Appium Server 2.x locally, default host/port 127.0.0.1:4723.
- Optional: Clear/prepare app state as needed.

2) Execute tests with Maven
- Clean and run tests (uses TestNG suite configured in pom.xml):
  - Windows PowerShell:
    - `mvn -q -DskipTests=false clean test`

3) View Allure report
- Option A: Use Allure CLI (if installed) to serve the report from generated results:
  - `allure serve allure-results`
- Option B: Use the Maven Allure plugin to generate a static site:
  - `mvn allure:report`
  - Open `target/site/allure-maven-plugin/index.html` in your browser.

## Test Execution Details
- TestNG is used as the runner. The surefire plugin in `pom.xml` points to:
  - `src/test/resources/testng.xml`
- Lifecycle:
  - BasePage.@BeforeSuite initializes the AndroidDriver via DriverManager and wires PageObjectManager.
  - BasePage.@AfterTest quits the driver after tests complete.
- Data:
  - Login credentials provided by `utilities.Data.LoginData` data provider (`loginData`).
- Primary scenario:
  - `testCases.AutomationTestCase#automationTestCase` covering login, optional sorting validation, adding items to cart, checkout, payment, order review, and logout. Assertions use TestNG SoftAssert.

## Scripts/Commands
Common commands you might need during development:
- Run tests: `mvn clean test`
- Run a single test class (if not using suite): TODO — current surefire config is bound to suiteXmlFiles; consider adding a profile or CLI override
- Generate Allure report: `mvn allure:report`
- Serve Allure results via CLI: `allure serve allure-results`

## Environment Variables
While not strictly required by the code, the following are standard for Android/Appium setups:
- ANDROID_HOME or ANDROID_SDK_ROOT — path to Android SDK
- JAVA_HOME — path to JDK 16+
- PATH — should include `%ANDROID_HOME%\platform-tools` (adb) and optionally Allure CLI if used

Potential future env vars (not yet supported by code):
- APPIUM_SERVER_URL — to override hardcoded URL (TODO)

## Entry Points
- Maven Surefire runs the TestNG suite defined in `src/test/resources/testng.xml`.
- Individual tests are regular TestNG @Test methods. The main E2E flow is `AutomationTestCase#automationTestCase`.

## Tests
- Location: `src/test/java/testCases` with supporting pages under `src/test/java/pages`.
- To add tests: create new TestNG classes under `testCases` and corresponding Page Objects.
- Reporting: Allure annotations are used (Epic, Feature, Story, Owner, Severity, Step, Description). Results appear in `allure-results` after execution.

## Known Gaps / TODOs
- testng.xml is referenced in `pom.xml` but was not present in the reviewed file list. Ensure it exists at `src/test/resources/testng.xml` and includes the suite that runs `testCases.AutomationTestCase`.
- Appium server URL and potential proxy/timeouts are hardcoded; move to properties/env.
- No CI configuration provided; consider adding GitHub Actions or similar with device farm/cloud emulators.
- Device capabilities currently tied to a specific physical device; add a README section for using an emulator or parameterizing UDID.

## License
No explicit LICENSE file found in the repository.

TODO: Add a LICENSE file (e.g., MIT, Apache-2.0) to clarify usage and contributions.


## Submission Summary (PDF-ready)
A concise, submission-ready write-up (thought process, approach/methodology, steps, achievements, and links) is available here:
- docs/Submission_Summary.md

To export to PDF, open the Markdown in a viewer (e.g., GitHub) and use Print → Save as PDF, or use pandoc:
- `pandoc -s docs/Submission_Summary.md -o docs/Submission_Summary.pdf`
