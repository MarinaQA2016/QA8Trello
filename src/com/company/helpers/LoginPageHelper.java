package com.company.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageHelper extends PageBase{
    @FindBy(xpath = "//a[contains(text(),'Log in')]")
    WebElement logInIcon;
    @FindBy(id = "user")
    WebElement loginField;
    @FindBy(id = "login")
    WebElement loginButton;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(css = "#error >.error-message")
    WebElement errorMessageNotAttl;
    @FindBy(xpath = "//input[@value = 'Log in with Atlassian']")
    WebElement loginAsAttlButton;

    public LoginPageHelper(WebDriver driver) {
        super(driver);
    }

    public void openLoginPage() {
                logInIcon.click();
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(loginField,10);
        waitUntilElementIsClickable(loginButton,20);
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
        fillField(loginField,value);

    }

    public void enterPasswordNotAttl(String value) {
        waitUntilElementIsClickable(passwordField,10);
        fillField(passwordField,value);
        //to be sure that loginField and passwordField are already filled in
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void clickLoginInButtonNotAttl() {
        waitUntilElementIsClickable(loginButton,20);
        loginButton.click();

    }

    public String getErrorNotAttlMessage(){
        waitUntilElementIsVisible(errorMessageNotAttl,20);
        return errorMessageNotAttl.getText();
    }

    public void clickLoginAttl() {
        waitUntilElementIsClickable(loginAsAttlButton,10);
        loginAsAttlButton.click();
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
