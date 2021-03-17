package com.company.tests;


import com.company.helpers.LoginPageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTests extends TestBase{
    LoginPageHelper loginPage;

    @BeforeMethod
    public void initTests() throws InterruptedException {
        // ---------Press login button  ---
        loginPage = new LoginPageHelper(driver);
        loginPage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
    }

    @Test
    public void loginNegativeLoginIncorrect() throws InterruptedException {
        // -------- Enter login/password -------------
        loginPage.enterLoginNotAttl("123");
        loginPage.enterPasswordNotAttl("pass");


        //to be sure that loginField and passwordField are already filled in
        Thread.sleep(1000);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // -------- Click login button ------------
        waitUntilElementIsClickable(By.id("login"),20);
        driver.findElement(By.id("login")).click();
        waitUntilElementIsVisible(By.cssSelector("#error >.error-message"),20);

        // --------- Print error message ----------
        WebElement errorMessage = driver.findElement(By.cssSelector("#error >.error-message"));
        System.out.println("Error-message: " + errorMessage.getText());

        Assert.assertTrue(errorMessage.getText().contains("There isn't an account"),"The error-message" +
                "doesn't contain 'There isn't an account'");

    }

    @Test
    public void loginPositive() throws InterruptedException {
        //---- Fill in login-field and press "login with Attlassian"----
        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField,"marinaqatest2019@gmail.com");
        waitUntilElementIsClickable(By.xpath("//input[@value = 'Log in with Atlassian']"),10);
        driver.findElement(By.id("login")).click();
        waitUntilElementIsClickable(By.id("password"),10);

        //----- Fill in password field and press login-submit button-----------
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("marinaqa");
        waitUntilElementIsClickable(By.id("login-submit"),10);
        driver.findElement(By.id("login-submit")).click();

        //------Wait the Home page loading and print 'Boards' button -------
        waitUntilElementIsClickable(By.xpath("//button[@aria-label = 'Open Boards Menu']"),10);
        System.out.println("Name of the button 'Boards': " + driver
                .findElement(By.xpath("//button[@aria-label = 'Open Boards Menu']")).getText());
        Assert.assertEquals("Boards", driver
                .findElement(By.xpath("//button[@aria-label = 'Open Boards Menu']")).getText());
    }

    @Test
    public void negativePasswordIncorrect() throws InterruptedException {
        //---- Fill in login-field and press "login with Attlassian"----
        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField,"marinaqatest2019@gmail.com");
        waitUntilElementIsClickable(By.xpath("//input[@value = 'Log in with Atlassian']"),10);
        driver.findElement(By.id("login")).click();
        waitUntilElementIsClickable(By.id("password"),10);

        //----- Fill in password field and press login-submit button-----------
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("incorrect");
        waitUntilElementIsClickable(By.id("login-submit"),10);
        driver.findElement(By.id("login-submit")).click();

        //------Wait the error-message and print it -------
        waitUntilElementIsVisible(By.id("login-error"),10);
        System.out.println("Error-message: " + driver
                .findElement(By.id("login-error")).getText());
        Assert.assertTrue(driver
                .findElement(By.id("login-error")).getText().contains("email"));
    }





}
