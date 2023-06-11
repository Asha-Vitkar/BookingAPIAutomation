# HotelBookingAPIAutomation

**Introduction**

This project contains test automation for create,update,get and delete APIs. BDD testing approach is used.
To automate this booking APIs, RestAssured, Cucumber-junit are used.

**Prerequisites**

 Java Set up
 
 Maven
 
 Eclipse
 
 Cucumber plugin installed in eclipse
 
 **Steps**
 
 Download the project.
 
 Perform mvn clean install to get all required dependencies in the project.

****** **Project Strucrure** ******

 All the feature files available under src/test/resources/features 
 
 For Every APIs like create -createBooking,patch -updateBooking etc feature files are created
 
 e2eBooking.feature file contain all APIs (Create,Update,get and delete) scenarios and used for regression.
 
 Respective stepdefs file available under the src/test/java - com.org.booking.stefdefs package.
 
 To perform serialization and deserialization, pojo classes are used which are available 
 under the com.org.booking.pojo package.     

 src/main/java- com.org.booking.utils package contains classes used to perform some common functions 
 like token generation, reading properties files etc.

config.properties file used to store base url details and user credential details

To run feature file com.org.booking.testrunner package contains two Test Runner files

Use RegressionTestRunner file to perform regression test

Use SanityTestRunner to run individual APIs(create,patch,get and delete) feature files

After running the TestRunner file reports are generated under the test-output folder with .html extension

You will see reports like below

![image](https://github.com/Asha-Vitkar/BookingAPIAutomation/assets/72799553/1b87a231-f548-4d7d-bd4b-9b49fb4b7ca0)

 ![RegressionReport](https://github.com/Asha-Vitkar/BookingAPIAutomation/assets/72799553/c0540c34-21ef-4122-9f33-a075d86404a8)

