package com.company.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CurrentBoardHelper extends PageBase{
    String boardName;

    public CurrentBoardHelper(WebDriver driver, String boardName) {
        super(driver);
        this.boardName = boardName;
    }

    public void openCurrentBoardPage(){
        WebElement qaHaifa8Board = driver.findElement(By
                .xpath("//a[@class = 'board-tile'][.//@title='" + boardName + "']"));
        qaHaifa8Board.click();
    }

    public void waitUntilPageIsLoaded(){
        waitUntilElementIsClickable(By.cssSelector(".mod-show-menu"),10);
    }

    public int getListsQuantity(){
        return driver.findElements(By.cssSelector(".list-header")).size();
    }

    public void addNewList(String nameList) {
        WebElement addListButton = driver.findElement(By.xpath("//span[@class='placeholder']/.."));
        addListButton.click();
        waitUntilElementIsClickable(By.xpath("//input[@name = 'name']"),10);
        WebElement titleListField = driver.findElement(By.xpath("//input[@name = 'name']"));
        fillField(titleListField,nameList);
        WebElement submitButton = driver.findElement(By.cssSelector(".js-save-edit"));
        submitButton.click();
        WebElement cancelEditList = driver.findElement(By.cssSelector(".js-cancel-edit"));
        cancelEditList.click();
        waitUntilAllElementsArePresent(By.cssSelector(".list-header"),15);
    }

    public int getCardsQuantity(){
        return driver.findElements(By.cssSelector(".js-card-details")).size();
    }

    public String getAddListButtonName(){
        return driver.findElement(By.xpath("//span[@class='placeholder']")).getText();
    }

    public void changeLastListName(String listName) {
        // ----- Define the index of the last list --------------
        int lastList = getListsQuantity()-1;

        //-------- Click on the header--------------
        WebElement lastHeader = driver.findElements(By.cssSelector(".list-header")).get(lastList);

        lastHeader.click();
        waitUntilElementIsClickable(By.cssSelector(".js-list-name-input"),10);

        //------- Change the header -----------------
        WebElement lastNameList = driver.findElements(By.cssSelector(".js-list-name-input")).get(lastList);
        lastNameList.sendKeys(listName);
        lastNameList.sendKeys(Keys.ENTER);
        driver.navigate().refresh();
        waitUntilAllElementsArePresent(By.cssSelector(".list-header"),15);
    }


    public String getLastListName(){
        int listsQuantity = getListsQuantity();
        if (listsQuantity == 0) return "-- No lists --";
        else return driver.findElements(By.cssSelector(".list-header")).get(listsQuantity-1).getText();
    }

    public void addNewCard() {
        //------ Define "Add Card" button and click it ------
        waitUntilElementIsClickable(By.cssSelector(".open-card-composer"),10);
        WebElement addNewCard = driver.findElement(By.cssSelector(".open-card-composer"));
        addNewCard.click();

        //-------Define title field of the card and fill in it ----
        waitUntilElementIsClickable(By.cssSelector(".js-card-title"),10);
        WebElement cardTitle = driver.findElement(By.cssSelector(".js-card-title"));
        fillField(cardTitle,"new card");

        //----- Define 'Add Card' button and click it -----------
        WebElement submitCard = driver.findElement(By.cssSelector(".js-add-card"));
        submitCard.click();

        //------ Click X-button -----------
        waitUntilElementIsClickable(By.cssSelector(".js-cancel"),10);
        driver.findElement(By.cssSelector(".js-cancel")).click();
    }

    public void deleteAnyList() {
        // ------ Define openListMenu and click -----------
        WebElement openListManu = driver.findElement(By.cssSelector(".js-open-list-menu"));
        openListManu.click();

        //-------- Wait and click 'Archive List' menu option ----
        waitUntilElementIsClickable(By.xpath("//*[@class = 'js-close-list']/.."),10);
        WebElement deleteListOption = driver.findElement(By.xpath("//*[@class = 'js-close-list']/.."));
        deleteListOption.click();

        //------- Wait 'Archive List' option disappears ---------
        waitUntilElementDisappears(By.xpath("//*[@class = 'js-close-list']/.."),10);
    }
}
