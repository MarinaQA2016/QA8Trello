package com.company.tests;


import com.company.helpers.BoardsPageHelper;
import com.company.helpers.LoginPageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;


    @BeforeMethod
    public void initTests()  {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver,BoardsPageHelper.class);
        loginPage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
    }

    @Test
    public void loginNegativeLoginIncorrect() {
        loginPage.enterLoginPassNotAttl("123","psw");

        Assert.assertTrue(loginPage.getErrorNotAttlMessage()
                .contains("There isn't an account"),"The error-message" +
                "doesn't contain 'There isn't an account'");

    }

    @Test
    public void loginNegativeLoginEmpty() {
        loginPage.enterLoginPassNotAttl("","psw");

        Assert.assertTrue(loginPage.getErrorNotAttlMessage()
                .contains("Missing"),"The error-message isn't correct");

    }



    @Test
    public void loginPositive()  {
        loginPage.enterLoginPasswordAttl(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded();

        Assert.assertEquals("Boards", boardsPage
                .getNameBoardsButton());
    }



    @Test
    public void negativePasswordIncorrect()  {
        loginPage.enterLoginPasswordAttl(LOGIN,"incorrect")
                 .getErrorAttlMessage();

        Assert.assertTrue(loginPage.getErrorAttlMessage()
                .contains("email"));
    }





}
