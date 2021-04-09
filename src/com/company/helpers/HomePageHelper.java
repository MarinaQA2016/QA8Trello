package com.company.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageHelper extends PageBase{
    @FindBy(xpath = "//a[contains(text(),'Log in')]")
    WebElement logInIcon;


    public HomePageHelper(WebDriver driver) {
        super(driver);
    }

    public HomePageHelper waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(logInIcon,40);
        return this;
    }

    public String getPageTitle(){
        return driver.getTitle();
    }



}
