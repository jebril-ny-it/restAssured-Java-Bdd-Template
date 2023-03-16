# **PROJECT-NAME QA AUTOMATION** - Built leveraging Java RestAssured TestNG with cucumber BDD layer and the help of pico container for dependency Injection

**Installation Steps**

**1**
Please set up JAVA_HOME path and MAVEN_HOME path under environment variables

**2**
clone repo

**3**
Install IDE required plugins - Gherkin and Cucumber java etc..

**4**
Run the below outlined prep commands in sequential order

****

**For clean Install execute the below command via terminal**
mvn clean install -DskipTests

**For clean test execute the below command via terminal**
mvn clean test -DDefaultENV=DEV1 -Dcucumber.filter.tags=@JEBRIL -Dtest.suite.filename=TestNG_BDD_ProjectName_Testing.xml

****

****Explanation****

**-DDefaultENV=DEV1**
Pass what env you would like to execute against via terminal or via CICD pipeline, eg: DEV1, QA1, UAT1.
Once the env is passed, user.properties data will be picked up from the respective prop directory. 
This will help resolve duplicate tests, and allow you to run dynamically

**-Dcucumber.filter.tags=@JEBRIL**
Pass the cucumber tag you would like to execute via terminal or via CICD pipeline


**-Dtest.suite.filename=TestNG_BDD_ProjectName_Testing.xml**
TestNG xml file - This will be used when executing via CICD pipeline, not required for tetsing in your local box

****Contributor****
@Jebril, @EMDY 