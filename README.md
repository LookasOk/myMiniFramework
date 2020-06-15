## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Maven](#maven)

## General info
### MyMiniFramework
Selenium, Rest Assured based framework.

###### TestGenerateApiKey.java - test runs a simple scenario of logging in, creating an API key and then changing it's name.  
###### TestApi.java - tests a couple of positive / negative test flows of the created API checking returned values and response codes.
###### TestReturnedJsonBody.java - Checks returned Json body against schema provided
	
## Technologies
Project is created with:
* Java version: 1.8
* Selenium version: 3.141.59
* Rest_Assured version: 4.3.0
* TestNG version: 6.14.3
	
## Setup
To run this project Java 1.8, Apache Maven is required.  
Account at https://home.openweathermap.org/users/sign_in is required.

After cloning the repository you will have to open src/test/java/pages/LoginPage.java using any IDE or simple text editor and 
add your login credentials at these lines: 
```
28. enterEmailField.sendKeys("Your email");  
29. passwordField.sendKeys("Your password");
```
At the moment there is a valid api key saved at src/test/java/utils/testData/apiKey.txt. However if the api key doesn't work
a new one will be generated and saved to apiKey.txt after running TestGenerateApiKey.java. A newly created key wont't work
for some time (up to 10 minutes).  
While api key at apiKey.txt is not active tests: TestApi.java and TestReturnedJsonBody.java will fail.

## Maven
Maven commands to run tests.  
Open git bash at repository root folder.

```
$ 'mvn clean test -Dtest=TestNameHere test'       -> runs single test class
$ 'mvn clean test -DsuiteXmlFile=testNGSuite.xml' -> runs specified testNG suite
$ 'mvn clean test'                                -> runs all tests with @Test annotation
```
