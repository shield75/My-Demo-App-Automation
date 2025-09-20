# Project Submission Summary — My Demo App Automation (Android)

GitHub repository: https://github.com/shield75/My-Demo-App-Automation.git

Author: Anisur Rahman
Date: 2025-09-21
Platform: Android
Primary Tools: Java, Maven, TestNG, Appium Java Client (UiAutomator2), Selenium, Allure, Appium Inspector, Appium Server 2.x (installed via npm)

---

## 1) Thought Process
- Start from the business flow the stakeholder cares about: a successful shopper journey from login to logout.
- Use Page Object Model to keep UI interactions maintainable and readable.
- Keep configuration externalized (capabilities.properties) so device/app details don’t require code changes.
- Make the runner stable and observable: TestNG for structure + Allure for rich reporting.
- Implement robust interaction helpers (waits, fluent taps, safe text entry) to mitigate mobile UI timing issues.
- Validate not only navigation but also data integrity (quantities, totals, and order review details).

## 2) Approach and Methodology
- Test Design: One main end-to-end regression scenario exercising the full purchase flow with soft assertions to accumulate validation points before failing.
- Architecture: Page Object Model (pages/*) + a central PageObjectManager + DriverManager for lifecycle and capabilities binding.
- Configuration: src/test/resources/capabilities.properties provides device/app under test parameters; Appium endpoint local default.
- Tooling:
  - Appium 2.x server (CLI) with UiAutomator2 driver for Android.
  - Appium Inspector for element discovery and locator verification.
  - Allure for reporting, with annotations (Epic, Feature, Story, Owner, Severity, Step, Description) for clarity.
- Execution: TestNG suite invoked by Maven Surefire; results collected into allure-results and optionally rendered to a report site.

## 3) Steps Taken to Complete the Tasks
1. Environment setup
   - Installed Java JDK 16+, Maven, Android SDK (adb), Node.js, and Appium Server 2.x.
   - Installed Appium UiAutomator2 driver: `appium driver install uiautomator2`.
   - Verified device/emulator connectivity via `adb devices`.
2. Project configuration
   - Declared dependencies in pom.xml: TestNG, Appium Java Client, Selenium, Allure, and SLF4J.
   - Configured Maven Surefire to run `src/test/resources/testng.xml` and to store results in `allure-results`.
3. Capability management
   - Centralized device/app capabilities in `src/test/resources/capabilities.properties` (platformName, udid, deviceName, appPackage, appActivity, automationName).
4. Driver lifecycle
   - Implemented DriverManager to create an AndroidDriver with UiAutomator2Options and an implicit wait.
5. Page Object Model
   - Built BasePage with reusable methods (explicit waits, fluent tap, inputText, price parsing, scrolls, etc.).
   - Implemented page objects for Login, Catalog, Product, Cart, Checkout, Payment, Review, Confirmation, and shared elements.
   - Added PageObjectManager for lazy initialization of page classes.
6. Test case authoring
   - Wrote `AutomationTestCase` with a data provider for login credentials.
   - Encoded a full purchase flow with intermediate validations and Allure steps.
7. Reporting
   - Added Allure integration (dependency + annotations). Reports generated from allure-results via CLI or Maven plugin.
8. Validation & Iteration
   - Used Appium Inspector to validate locators and interactions against the real app.
   - Stabilized interactions by adding waits and retry logic.

## 4) Final Achievements
- Automates a realistic end-to-end shopping scenario on Android including:
  - Login, catalog navigation, sorting checks, multi-item cart operations, checkout, payment details input, order review and confirmation, and logout.
- Clean separation of concerns using Page Object Model for maintainability.
- Reusable utilities for robust and readable interactions.
- Friendly reporting with Allure (narrative + screenshots-ready).
- Easy configuration via properties without code changes.

## 5) Test Case Flow (as executed)
Task:
1. Log in
2. Go to Catalog
3. Sort by Name - Any
4. Sort by Price - Any
5. Add 2nd product to cart - qty 3
6. Add 3rd Product to Cart - qty 2
7. Add 5th Product to Cart - qty 1
8. Go to Cart
9. Decrease 1st Product
10. Increase 3rd Product
11. Remove 2nd Product
12. Proceed to Checkout
13. Complete the checkout process
14. Log Out

Note: The concrete steps and assertions are implemented in `src/test/java/testCases/AutomationTestCase.java` and POM classes under `src/test/java/pages`.

## 6) Tools and How They Were Used
- Appium Server 2.x (CLI) — runs the automation server.
  - Install via npm: `npm install -g appium`
  - Install Android driver: `appium driver install uiautomator2`
  - Start server (default): `appium`
- Appium Inspector — used to inspect the app, validate locators and actions.
- Java + Maven — language and build system.
- TestNG — test runner and structure.
- Selenium / Appium Java Client — device automation APIs.
- Allure — results and reporting.

## 7) Setup & Run (Quick Start)
1) Prerequisites
- JDK 16+, Maven 3.8+, Android SDK (adb in PATH)
- Node.js and Appium 2.x installed
- A device/emulator connected and unlocked

2) Configure capabilities
- Edit `src/test/resources/capabilities.properties`:
```
platformName=Android
udid=<your_device_udid>
deviceName=<your_device_name>
appPackage=com.saucelabs.mydemoapp.rn
appActivity=com.saucelabs.mydemoapp.rn.MainActivity
automationName=UiAutomator2
```

3) Run
- Start Appium server: `appium`
- Execute: `mvn clean test`
- Generate report: `mvn allure:report` then open `target/site/allure-maven-plugin/index.html`

## 8) Special Features
- Robust interaction helpers (fluent tap, explicit waits) to reduce flakiness.
- Soft assertions to continue validations within a single scenario.
- Page Object Model with centralized manager for reuse.
- Allure annotations for story-level traceability and rich reports.
- Ready to extend for additional scenarios (e.g., negative login, network resiliency). 

(Images/Screenshots: Not included at this time.)

## 9) Exporting this Document to PDF
If you need a PDF for submission, use any of these options:
- VS Code: Open this file and press Ctrl+P → type “Export to PDF” (with Markdown PDF extension) or use a Markdown extension that supports PDF export.
- Browser: View the Markdown in a viewer (e.g., GitHub web UI), then use the browser’s Print → Save as PDF.
- Pandoc (if installed): `pandoc -s docs/Submission_Summary.md -o docs/Submission_Summary.pdf`

## 10) References
- Repository: https://github.com/shield75/My-Demo-App-Automation.git
- Appium: https://appium.io
- TestNG: https://testng.org
- Allure: https://docs.qameta.io/allure/