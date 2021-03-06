package com.company.tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests {
    WebDriver driver;

    @BeforeMethod
    public void initTests() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://trello.com/");
        Thread.sleep(5000);
    }
    @Test
    public void applicationTest(){
        System.out.println("Title: " + driver.getTitle());
    }

    @Test
    public void loginNegativeLoginIncorrect() throws InterruptedException {
        // ---------Press login button  ---
        WebElement loginIcon = driver.findElement(By.xpath("//a[contains(text(),'Log in')]"));
        loginIcon.click();
        Thread.sleep(7000);

        // -------- Enter login/password -------------
        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField,"123");
        WebElement passwordField = driver.findElement(By.id("password"));
        fillField(passwordField,"pass");
        Thread.sleep(3000);

        // -------- Click login button ------------
        driver.findElement(By.id("login")).click();
        Thread.sleep(3000);

        // --------- Print error message ----------
        WebElement errorMessage = driver.findElement(By.cssSelector("#error >.error-message"));
        System.out.println("Error-message: " + errorMessage.getText());

    }

    public void fillField(WebElement element, String value) {
        element.clear();
        element.click();
        element.sendKeys(value);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
