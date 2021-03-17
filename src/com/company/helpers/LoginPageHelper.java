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

    public void enterLoginNotAttl(String value) {
        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField,value);

    }

    public void enterPasswordNotAttl(String value) {
        waitUntilElementIsClickable(By.id("password"),10);
        WebElement passwordField = driver.findElement(By.id("password"));
        fillField(passwordField,value);
    }
}
