package com.company.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CurrentBoardTests extends TestBase{
    @BeforeMethod
    public void initTests() throws InterruptedException {
        // ---------Press login button  ---
        WebElement loginIcon = driver.findElement(By.xpath("//a[contains(text(),'Log in')]"));
        loginIcon.click();
        Thread.sleep(7000);
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

        //----Open QA Haifa8 board ----
        WebElement qaHaifa8Board = driver.findElement(By
                .xpath("//a[@class = 'board-tile'][.//@title='QA Haifa8']"));
        qaHaifa8Board.click();
        Thread.sleep(3000);

    }
    @Test
    public void createNewList() throws InterruptedException {
        WebElement addListButton = driver.findElement(By.xpath("//span[@class='placeholder']/.."));
        addListButton.click();
        Thread.sleep(2000);
        WebElement titleListField = driver.findElement(By.xpath("//input[@name = 'name']"));
        fillField(titleListField,"titleList");
        WebElement submitButton = driver.findElement(By.cssSelector(".js-save-edit"));
        submitButton.click();
        WebElement cancelEditList = driver.findElement(By.cssSelector(".js-cancel-edit"));
        cancelEditList.click();
    }


}
