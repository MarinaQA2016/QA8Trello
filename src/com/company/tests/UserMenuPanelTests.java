package com.company.tests;

import com.company.helpers.BoardsPageHelper;
import com.company.helpers.CurrentBoardHelper;
import com.company.helpers.LoginPageHelper;
import com.company.helpers.UserMenuPanelPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserMenuPanelTests extends TestBase{
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    UserMenuPanelPage userMenuPanelPage;

    @BeforeMethod
    public void initTests()  {
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);
        userMenuPanelPage = new UserMenuPanelPage(driver);

        loginPage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.enterLoginPasswordAttl(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        userMenuPanelPage.openUserMenu();
        userMenuPanelPage.waitUntilPageIsLoaded();
    }

    @Test
    public void verifyEmailOnPanel(){
        Assert.assertEquals(userMenuPanelPage.getEmailFromMenu(),LOGIN,"The email is not correct");
    }
}
