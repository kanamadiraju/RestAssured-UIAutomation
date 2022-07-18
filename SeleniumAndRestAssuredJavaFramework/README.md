# Page Object model Test automation framework using RestAssured and Selenium with Java, TestNG and Maven-
Framework contians Selenium Test cases and REST API Test cases
TestNG is used as test framework.


# Libraries used:
Maven
TestNG
Selenium Webdriver
RestAssured
Extent Reports

### Steps to clone execute the tests
git clone 'repo path'
cd SeleniumJavaFramework
mvn clean install -DtestngFile=testng_API.xml
mvn clean install -DtestngFile=testng_UI.xml
mvn clean test -DtestngFile=testng_UIandAPI.xml



# Precondition:
1. User should alreday have an account with trello sites
2. Before execution make sure Maven is installed in your system
3. Provide Username, Password,  key and Token(for an API test required) in config.Properties file


# Trello Login:
username: 
password: 
key:
token:

-------------------------------------------------------------------------------------------------------------------------
# Usage
The project is broken into modules for UI, API and UI+API.
Each of these modules can be utilised independently of the others using maven profiles.

To run UI acceptance tests only, navigate to test-automation-quickstart directory and run:

mvn clean install -DtestngFile=testng_UI.xml

To run API acceptance tests only, navigate to test-automation-quickstart directory and run:

mvn clean install -DtestngFile=testng_API.xml

To run combination of UI and API tests only, navigate to test-automation-quickstart directory and run:

mvn clean install -DtestngFile=testng_UIandAPI.xml

-------------------------------------------------------------------------------------------------------------------------
# Selenium Tests:
SeleniumUIAutomationTestcases.java - Contains UI automation test cases

# API Tests:
RestAssuredAPITestcases.java - Contains API Test cases

# Selenium+API combination Tests
SeleniumAndAPITestcases.java - Contains Testcases created by having combination of selenium and API test cases

-------------------------------------------------------------------------------------------------------------------------
# Reporting

Reports for each module are written into their respective /target > surefire-reports > Mon_Jun_27_20_06_02_IST_2022.html

-------------------------------------------------------------------------------------------------------------------------
# Framework Included

Page Object pattern: locator & Page respective methods
Listener: Screenshot, Retry Mechanism
TestBase: driver Initializations
TestData : config.properties
Report: Extent Report
TestCases:SeleniumUIAutomationTestcases, RestAssuredAPITestcases and SeleniumAndAPITestcases
POM: locator & Page respective methods

-------------------------------------------------------------------------------------------------------------------------
# Note: 
1. Implemented IAnnotationTransformer, if any of the testcases fails during execution, Failed testcases reexcute automatically based on the retry count 

2. Failed Test cases sreenshot taken and stored the same under Project > Screenshot folder