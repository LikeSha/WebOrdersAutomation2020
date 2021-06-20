package com.weborders.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.weborders.utilities.BrowserUtilities;
import com.weborders.utilities.ConfigurationReader;
import com.weborders.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public abstract class AbstractBaseTest {

    protected WebDriver driver = Driver.getDriver() ;

    protected static ExtentReports extentReports;
    protected static ExtentHtmlReporter extentHtmlReporter;
    protected static ExtentTest extentTest;


    @BeforeTest
    public void beforeTest(){
        extentReports = new ExtentReports();
        String reportPath = "";
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            reportPath = System.getProperty("user.dir") + "\\test-output\\report.html";
        } else {
            reportPath = System.getProperty("user.dir") + "/test-output/report.html";
        }
        extentHtmlReporter = new ExtentHtmlReporter(reportPath);
        extentReports.attachReporter(extentHtmlReporter);
        extentHtmlReporter.config().setReportName("WebOrders Automation");

    }

    @AfterTest
    public void afterTest(){
        extentReports.flush();
    }

    @BeforeMethod
    public void setup(){

        driver.get(ConfigurationReader.getProperty("url"));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(ITestResult testResult) {
        if(testResult.getStatus()==ITestResult.FAILURE){
            String screenshotLocation = BrowserUtilities.getScreenshot(testResult.getName());
            try {
                extentTest.fail(testResult.getName());//TEST NAME THAT FAILED
                extentTest.addScreenCaptureFromPath(screenshotLocation);//screenshot as an evidence
                extentTest.fail(testResult.getThrowable());//error message
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to attach screenshot");
            }
        }else if(testResult.getStatus()==ITestResult.SUCCESS){
            extentTest.pass(testResult.getName());
        }else if(testResult.getStatus()==ITestResult.SKIP){
            extentTest.skip(testResult.getName());
        }
        BrowserUtilities.wait(3);
        Driver.closeDriver();
    }




}
/*
What is framework ? your project ,collection of classes of your tests with libraries.
Place where you develop new tests.

Framework is a set of pre-written code libraries designed to be used by developers.
A programming language is a specified method of communication  between the programmer and computer.

1. Create new maven project---> WebOrdersAutomation2020
      project name/artifact id: WebOrderAutomation2020
      group id: com.weborders

default archetype ( archetype is a skeleton of your project ,some fructure, floders already created )

2. Add dependencies:
    selenium WebDriver
    Webdrivernamager
    testNG
    extent report
    slf4j---intellij logger

    build plugin, to fix java version issue

       2.1  Enable auto import

3. Create configuration.properties file
     web applicaation URL
     credentials
     explicit wait time ( optional , I will show)

4. Create .gitignore file and add there:

      .idea
      *.iml
      target
      test-output
      .DS_Store
      anything that you don't wanna push

5. Create packages:
go to src-->test-->java

       create com.weborders
               pages
               tests
               utilities

6. Add utilities:

    Driver class
    Browser utilities
    DateTimeUtilities
    ConfigurationReader

7. Create an AbstractTestBase class

    7.1
      add before method

      @BeforeMethod
      public void setup(){
          //open broswer
          String URL = ConfigurationReader.getProperty("qa3");
          Driver.getDriver().get(URL);
          maximize window
          wait = new WebDriverWait(Driver.getDriver(),15);
          actions = new Actions(Driver.getDriver());
      }

    7.2 Add after method:

      @AfterMethod
      public void teardown(){
         close browser
         take a screenshot if test failed
      }

    7.3 Setup extent report

       @BeforeTest
       public void veforeTest(){
            setup report
       }

    7.4 Generate report

        @AfterTest
        public void afterTest(){
           flush report
        }


8. Create AbstractBasePage class under pages:

    8.1 Initialize page factory
    8.2 Put some common method/webelements/locators here. Let other page classes extend it.

9. Create git repository:

    9.1
      git init
      git add .
      git commit -m "first commit"

    9.2 Go to github, create repository with same name
    9.3 Execute steps for exiting repository. There are 2 steps : connect local repo with remote and push everything.
    Then next time you will just need to execute :

        -git commit -a -m "commit message"
        -git push

        To get updates : git pull

10. Create suite runners:

      10.1 right on the project-->new file--testng.xml
      10.2 Add base setup:

                 <!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >

           <suite name="Regression">
               <test name="Regression" parallel="classes" thread-count="2">
                   <parameter name="browser" value="chrome"/>
                    <classes>
                       <class name="com.weborders.tests.LoginTests"/>
                       <class name="com.weborders.tests.SmokeTest"/>
                   </classes>
               </test>
           </suite>


ABOVE ALL FINISH BUILD UP A SIMPLE PROJECT FRAMEWORK

#####################################################################################################

in java ,there are 4 access modifiers:

    private  ------------ to encapsulate variables/methods/constructors --
    make everyting invisible outside of the class
    package private(default)--visible within same package
    protected--visible within same package and in the child class
    public--visible enverywhere

    Page Object Model--it's about elements that you have on the page ,
    and how do you store them in java classes as web elemeents.
    There should be corresondence between page class and page.

    git remote add origin https://github.com/YOU_GIT_USER_NAME/WebOrdersAutomation2020.git

    git push -u origin master

    FORK  means you copy a remote project into another remote project ,it means,
    I create a remote branch in my project weborders2020 .
    and I copy cyberteck school weborders2020 project from the github.
    it means I fork it. After clikced fork button on github,
    now this repository has connection with I can proposed change in my own project
    which was I just copied ( forked ) by doing pull request. I am able to propose to change .
    click " clone or download"---> copy the URL in the popup window.( this URL is under my username )--->
    go to inteliJ,  click file-->new--> Project from Version Control-->
    paste the URL I just copied from github-->click "Clone" right bottom corner of the window
 */