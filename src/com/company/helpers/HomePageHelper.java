package com.company.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageHelper extends PageBase{


    public HomePageHelper(WebDriver driver) {
        super(driver);
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(By.xpath("//a[contains(text(),'Log in')]"),40);
    }

    public String getPageTitle(){
        return driver.getTitle();
    }



}
