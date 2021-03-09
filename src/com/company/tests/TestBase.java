package com.company.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    WebDriver driver;

    @BeforeMethod
    public void startAppl() throws InterruptedException {
        driver = new ChromeDriver();
        //----Driver initialization. Open Trello application
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--lang=" + "rus");
        //driver = new ChromeDriver(options);
        driver.get("https://trello.com/");
        Thread.sleep(5000);
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    public void fillField(WebElement element, String value) {
        element.clear();
        element.click();
        element.sendKeys(value);
    }
}
