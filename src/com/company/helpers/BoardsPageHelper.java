package com.company.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BoardsPageHelper extends PageBase{
    public BoardsPageHelper(WebDriver driver) {
        super(driver);
    }

    public void waitUntilPageIsLoaded(){
        //------Wait the Home page loading and print 'Boards' button -------
        waitUntilElementIsClickable(By.xpath("//button[@aria-label = 'Open boards menu']"),20);
        System.out.println("Name of the button 'Boards': " + driver
                .findElement(By.xpath("//button[@aria-label = 'Open boards menu']")).getText());
    }

    public String getNameBoardsButton(){
        waitUntilElementIsClickable(By
                .xpath("//button[@aria-label = 'Open boards menu']"),5);
        WebElement boardsButton = driver.findElement(By
                .xpath("//button[@aria-label = 'Open boards menu']"));
        return boardsButton.getText();
    }
}
