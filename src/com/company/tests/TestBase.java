package com.company.tests;

import com.company.helpers.HomePageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    WebDriver driver;
    HomePageHelper homePage;
    public static final String LOGIN = "marinaqatest2019@gmail.com";
    public static final String PASSWORD = "marinaqa";

    @BeforeMethod
    public void startAppl() throws InterruptedException {
        driver = new ChromeDriver();
        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        //----Driver initialization. Open Trello application
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--lang=" + "rus");
        //driver = new ChromeDriver(options);
        driver.get("https://trello.com/");
        homePage.waitUntilPageIsLoaded();

    }



    @AfterMethod
    public void tearDown(){
        driver.quit();
    }



}
