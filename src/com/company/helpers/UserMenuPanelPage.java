package com.company.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserMenuPanelPage extends PageBase{
    public UserMenuPanelPage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilPageIsLoaded(){
        waitUntilElementIsClickable(By.xpath("//a[@data-test-id ='header-member-menu-profile']"),10);
    }
    public void openUserMenu(){
        WebElement userMenuButton = driver.findElement(By.xpath("//button[@aria-label='Open member menu']"));
        userMenuButton.click();
        waitUntilElementIsClickable(By.xpath("//a[@data-test-id='header-member-menu-profile']"),10);
    }
    public String getEmailFromMenu(){
        WebElement emailOnMenu = driver.findElement(By.xpath("//nav//span[contains(text(),'@')]"));
        return emailOnMenu.getText();
    }
}
