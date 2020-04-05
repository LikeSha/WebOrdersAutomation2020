package com.weborders.tests;

import com.weborders.pages.LoginPage;
import org.testng.annotations.Test;
import static org.testng.Assert.*;


public class LoginTests extends AbstractBaseTest {

    @Test
    public void login(){
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        assertEquals(loginPage.getPageLogoText(),"Web Orders");
    }
}
