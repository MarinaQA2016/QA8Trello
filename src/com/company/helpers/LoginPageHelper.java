package com.company.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageHelper extends PageBase{

    public LoginPageHelper(WebDriver driver) {
        super(driver);
    }

    public void openLoginPage() {
        WebElement loginIcon = driver.findElement(By.xpath("//a[contains(text(),'Log in')]"));
        loginIcon.click();
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(By.id("user"),10);
        waitUntilElementIsClickable(By.id("login"),20);
    }

    public void enterLoginPassNotAttl(String login, String password) {
        this.enterLoginNotAttl(login);
        this.enterPasswordNotAttl(password);
        this.clickLoginInButtonNotAttl();
    }

    public void enterLoginPasswordAttl(String login, String password) {
        this.enterLoginNotAttl(login);
        this.clickLoginAttl();
        this.enterPasswordAttl(password);
        this.submitAttl();
    }

    public void enterLoginNotAttl(String value) {
        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField,value);

    }

    public void enterPasswordNotAttl(String value) {
        waitUntilElementIsClickable(By.id("password"),10);
        WebElement passwordField = driver.findElement(By.id("password"));
        fillField(passwordField,value);
        //to be sure that loginField and passwordField are already filled in
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void clickLoginInButtonNotAttl() {
        waitUntilElementIsClickable(By.id("login"),20);
        driver.findElement(By.id("login")).click();

    }

    public String getErrorNotAttlMessage(){
        waitUntilElementIsVisible(By.cssSelector("#error >.error-message"),20);
        WebElement errorMessage = driver
                .findElement(By.cssSelector("#error >.error-message"));
        System.out.println("Error-message: " + errorMessage.getText());
        return errorMessage.getText();
    }

    public void clickLoginAttl() {
        waitUntilElementIsClickable(By.xpath("//input[@value = 'Log in with Atlassian']"),10);
        driver.findElement(By.id("login")).click();
    }

    public void enterPasswordAttl(String value) {
        waitUntilElementIsClickable(By.id("password"),10);
        WebElement passwordField = driver.findElement(By.id("password"));
        fillField(passwordField,value);

    }

    public void submitAttl() {
        waitUntilElementIsClickable(By.id("login-submit"),10);
        driver.findElement(By.id("login-submit")).click();
    }

    public String getErrorAttlMessage() {
        waitUntilElementIsVisible(By.id("login-error"),10);
        return driver.findElement(By.id("login-error")).getText();
    }
}
