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

    @Test
    public void loginPositive() throws InterruptedException {
        // ---------Press login button  ---
        driver.findElement(By.xpath("//a[contains(text(),'Log in')]")).click();
        Thread.sleep(2000);


        //---- Fill in login-field and press "login with Attlassian"----
        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField,"marinaqatest2019@gmail.com");
        Thread.sleep(2000);
        driver.findElement(By.id("login")).click();
        Thread.sleep(2000);

        //----- Fill in password field and press login-submit button-----------
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("marinaqa");
        Thread.sleep(2000);
        driver.findElement(By.id("login-submit")).click();

        //------Wait the Home page loading and print 'Boards' button -------
        Thread.sleep(20000);
        System.out.println("Name of the button 'Boards': " + driver
                .findElement(By.xpath("//button[@aria-label = 'Open Boards Menu']")).getText());
    }

    @Test
    public void negativePasswordIncorrect() throws InterruptedException {
        // ---------Press login button  ---
        driver.findElement(By.xpath("//a[contains(text(),'Log in')]")).click();
        Thread.sleep(2000);


        //---- Fill in login-field and press "login with Attlassian"----
        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField,"marinaqatest2019@gmail.com");
        Thread.sleep(2000);
        driver.findElement(By.id("login")).click();
        Thread.sleep(2000);

        //----- Fill in password field and press login-submit button-----------
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("incorrect");
        Thread.sleep(2000);
        driver.findElement(By.id("login-submit")).click();

        //------Wait the error-message and print it -------
        Thread.sleep(5000);
        System.out.println("Error-message: " + driver
                .findElement(By.id("login-error")).getText());
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
