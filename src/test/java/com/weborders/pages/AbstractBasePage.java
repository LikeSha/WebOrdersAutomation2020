package com.weborders.pages;

import com.weborders.utilities.BrowserUtilities;
import com.weborders.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
It meant to be extended
 */
public abstract class AbstractBasePage {

    protected WebDriver driver = Driver.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driver,20);

    @FindBy(tagName = "h1")
    protected WebElement pageLogo;

    @FindBy(tagName = "h2")
    protected WebElement pageSubtitle;

    public String getPageSubtitleText(){
        BrowserUtilities.waitForPageToLoad(10);
        return pageSubtitle.getText().trim();
    }

    public String getPageLogoText(){
        return pageLogo.getText();
    }

    public AbstractBasePage(){ // we need this constructor method is only for use " FindBy" method in page classes
        PageFactory.initElements(driver,this);
    }// whenever page objects created ,constructor is immediately being called. in other words, whenever any
    // object being created, this line of code will be executed.

    //A constructor in Java is a special method that is used to initialize objects.
    // The constructor is called when an object of a class is created.
    // It can be used to set initial values for object attributes:


    /**
     * Specify component name as a parameter, like : View all products or Orders
     * @param component
     */
    public void navigateTo(String component){
        String locator = "//a[text()='"+component+"']";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator))).click();
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


    How to fork a project ?

    steps :

     1 , go to cybertek shool project to find out project name : WebOrderAutomation2020

     2, click that project

     3, then the project page showing up, go click right top corner " Fork" button ;
     after click just wait few seconds, its copying entire project form cyberteck school remote master branch
     to your own remote master branch. after copy, the new page automatically showing up my account page.
     now this repository has connection bebween parent repository to my own .
     so I can take "pull request " and proposal changes by working on my copy of the project on my own account.

     4, I click " New pull request " then now its able to propose changes. so this is what the meaning of Fork

     5, now the page showing my own account under WebOrder project ,
     I just go lick green button " Clone or download" on right side of page ,
     and copy the url from the pop-up window.

     6, take this url and go to my intelliJ project WebOrdersAutomation2020
      --> click "File"--> click "New"--> click " Project from Version Control..."

     7, in the pop up window , paste that url into the column " URL "
 */