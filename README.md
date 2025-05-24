# Sample Mobile Automation Framework

> **Note:** The APK file is not included in this repository due to GitHub's 100 MB file size limit. To run tests locally, place your APK file (e.g., `androidAPP.apk`) in the directory `src/apps/android/`. Update your test configuration or driver setup if your APK has a different name or location.

This project is a Java-based mobile automation framework using Appium, Selenium, and TestNG. It follows the Page Object Model (POM) design pattern and includes robust logging and reporting features.

## Features
- Appium + Selenium for Android automation
- TestNG for test management
- Page Object Model (POM) structure
- SLF4J + Logback for logging
- TestNG listeners for reporting and screenshots on failure
- Utility helpers for common actions and assertions

## Project Structure
```
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── pages         # Page objects (e.g., WalletPage)
│   │   │   └── utils         # Utilities (DriverUtils, HelperUtil, Log, TestListener)
│   │   │
│   │   └── test
│   │       └── java
│   │           └── tests        # Test classes (e.g., WalletTest, BaseTest)
│   ├── pom.xml                   # Maven dependencies
│   ├── src/testng.xml            # TestNG suite configuration
│   ├── wallet-creation-test-cases.md # Test case documentation
│   └── README.md                 # This file
```

## Prerequisites
- Java 8+
- Maven
- Node.js & Appium (`npm install -g appium`)
- Android SDK & emulator/device

## Setup
1. **Clone the repository:**
   ```sh
   git clone <repo-url>
   cd sample-mobile-framework
   ```
2. **Install dependencies:**
   ```sh
   mvn clean install
   ```
3. **Start the Appium server:**
   ```sh
   appium server -p 4725 -a 127.0.0.1 -pa /wd/hub
   ```
   (Or use your preferred Appium server settings, but update `DriverUtils.java` accordingly.)
4. **Configure your Android emulator or device.**

## Running Tests
- To run all tests:
  ```sh
  mvn clean test
  ```
- To run a specific test class:
  ```sh
  mvn clean test -Dtest=WalletTest
  ```

## Logging & Reporting
- **Logs:**
  - Uses SLF4J with Logback. Logs are output to the console by default.
  - You can customize logging by adding a `logback.xml` in `src/main/resources`.
- **TestNG Listener:**
  - Logs test events (start, pass, fail) and takes screenshots on failure (saved in the `screenshots/` directory).
- **Reports:**
  - TestNG HTML and XML reports are generated in `target/surefire-reports/` after each run.

## Page Object Model
- Page objects are in `src/main/java/pages/`.
- Example: `WalletPage` encapsulates all actions and elements for the wallet creation flow.
- Use `@FindBy` with `id` or `accessibility id` for stable locators.

## Utilities
- `DriverUtils`: Handles Appium driver setup and teardown.
- `HelperUtil`: Common actions (waits, assertions, passcode entry, etc.).
- `Log`: Wrapper for SLF4J logging.
- `TestListener`: TestNG listener for logging and screenshots.

## Appium Server
- The framework expects Appium to be running at `http://127.0.0.1:4725/wd/hub` (see `DriverUtils.java`).
- You can change the server address/port in `DriverUtils.java` if needed.

## Test Cases
- See `wallet-creation-test-cases.md` for detailed functional and UI/UX test cases.

## Contributing
Pull requests are welcome! Please follow the existing code style and add tests for new features.