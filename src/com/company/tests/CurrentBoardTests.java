package com.company.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CurrentBoardTests extends TestBase{
    @BeforeMethod
    public void initTests() throws InterruptedException {
        // ---------Press login button  ---
        WebElement loginIcon = driver.findElement(By.xpath("//a[contains(text(),'Log in')]"));
        loginIcon.click();
        waitUntilElementIsClickable(By.id("user"),10);

        //---- Fill in login-field and press "login with Attlassian"----
        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField,"marinaqatest2019@gmail.com");
        //Thread.sleep(2000);
        waitUntilElementIsClickable(By.xpath("//input[@value = 'Log in with Atlassian']"),10);
        driver.findElement(By.id("login")).click();
        //Thread.sleep(2000);

        //----- Fill in password field and press login-submit button-----------
        waitUntilElementIsClickable(By.id("password"),10);
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("marinaqa");
        //Thread.sleep(2000);
        waitUntilElementIsClickable(By.id("login-submit"),10);
        driver.findElement(By.id("login-submit")).click();

        //------Wait the Home page loading and print 'Boards' button -------
        //Thread.sleep(20000);
        waitUntilElementIsClickable(By.xpath("//button[@aria-label = 'Open boards menu']"),10);
        System.out.println("Name of the button 'Boards': " + driver
                .findElement(By.xpath("//button[@aria-label = 'Open boards menu']")).getText());

        //----Open QA Haifa8 board ----
        WebElement qaHaifa8Board = driver.findElement(By
                .xpath("//a[@class = 'board-tile'][.//@title='QA Haifa8']"));
        qaHaifa8Board.click();
        waitUntilElementIsClickable(By.cssSelector(".mod-show-menu"),10);

    }
    @Test
    public void createNewList() throws InterruptedException {
        // ----- Define the quantity of lists before  --------------
        int listsBefore = driver.findElements(By.cssSelector(".list-header")).size();

        WebElement addListButton = driver.findElement(By.xpath("//span[@class='placeholder']/.."));
        addListButton.click();
        waitUntilElementIsClickable(By.xpath("//input[@name = 'name']"),10);
        WebElement titleListField = driver.findElement(By.xpath("//input[@name = 'name']"));
        fillField(titleListField,"titleList");
        WebElement submitButton = driver.findElement(By.cssSelector(".js-save-edit"));
        submitButton.click();
        WebElement cancelEditList = driver.findElement(By.cssSelector(".js-cancel-edit"));
        cancelEditList.click();
        // ----- Define the quantity of lists after  --------------
        int listsAfter = driver.findElements(By.cssSelector(".list-header")).size();
        Assert.assertEquals(listsBefore+1,listsAfter,
                "The quantity of lists after adding is not the quantity before adding plus one");
    }

    @Test
    public void changeListName() throws InterruptedException {
        WebElement addList = driver.findElement(By.xpath("//span[@class='placeholder']"));
        //----- If no list (name of the button is 'Add a list'), create the new list ----
        if(addList.getText().equals("Add a list")){
            addList.click();
            waitUntilElementIsClickable(By.xpath("//input[@name = 'name']"),10);
            WebElement newNameList = driver.findElement(By.cssSelector("input[name='name']"));
            fillField(newNameList,"test");
            WebElement saveList = driver.findElement(By.cssSelector("input.js-save-edit"));
            saveList.click();
            waitUntilElementIsClickable(By.cssSelector(".js-cancel-edit"),10);
            WebElement cancelEditList = driver.findElement(By.cssSelector(".js-cancel-edit"));
            cancelEditList.click();
        }
        // ----- Define the index of the last list --------------
        int lastList = driver.findElements(By.cssSelector(".list-header")).size()-1;

        //-------- Click on the header--------------
        WebElement lastHeader = driver.findElements(By.cssSelector(".list-header")).get(lastList);

        lastHeader.click();
        waitUntilElementIsClickable(By.cssSelector(".js-list-name-input"),10);

        //------- Change the header -----------------
        String newHeader = "anotherHeader1";
        WebElement lastNameList = driver.findElements(By.cssSelector(".js-list-name-input")).get(lastList);
        lastNameList.sendKeys(newHeader);
        lastNameList.sendKeys(Keys.ENTER);
        driver.navigate().refresh();
        waitUntilAllElementsArePresent(By.cssSelector(".list-header"),15);
        lastHeader = driver.findElements(By.cssSelector(".list-header")).get(lastList);
        Assert.assertEquals(lastHeader.getText(), newHeader);
    }

    @Test
    public void addNewCard(){
        WebElement addList = driver.findElement(By.xpath("//span[@class='placeholder']"));
        //----- If no list (name of the button is 'Add a list'), create the new list ----
        if(addList.getText().equals("Add a list")){
            addList.click();
            waitUntilElementIsClickable(By.xpath("//input[@name = 'name']"),10);
            WebElement newNameList = driver.findElement(By.cssSelector("input[name='name']"));
            fillField(newNameList,"test");
            WebElement saveList = driver.findElement(By.cssSelector("input.js-save-edit"));
            saveList.click();
            waitUntilElementIsClickable(By.cssSelector(".js-cancel-edit"),10);
            WebElement cancelEditList = driver.findElement(By.cssSelector(".js-cancel-edit"));
            cancelEditList.click();
        }
        waitUntilAllElementsArePresent(By.cssSelector(".list-header"),15);

        // ----- Define the quantity of cards before  --------------
        int cardsBefore = driver.findElements(By.cssSelector(".js-card-details")).size();

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

        //------- Define the quantity of the cards after adding the new card----
        int cardsAfter = driver.findElements(By.cssSelector(".js-card-details")).size();

        Assert.assertEquals(cardsBefore+1,cardsAfter,
                "The quantity of cards after adding is not equal to cards before adding plus one");

    }

    @Test
    public void deleteAnyList(){
        WebElement addList = driver.findElement(By.xpath("//span[@class='placeholder']"));
        //----- If no list (name of the button is 'Add a list'), create the new list ----
        if(addList.getText().equals("Add a list")){
            addList.click();
            waitUntilElementIsClickable(By.xpath("//input[@name = 'name']"),10);
            WebElement newNameList = driver.findElement(By.cssSelector("input[name='name']"));
            fillField(newNameList,"test");
            WebElement saveList = driver.findElement(By.cssSelector("input.js-save-edit"));
            saveList.click();
            waitUntilElementIsClickable(By.cssSelector(".js-cancel-edit"),10);
            WebElement cancelEditList = driver.findElement(By.cssSelector(".js-cancel-edit"));
            cancelEditList.click();
        }
        waitUntilAllElementsArePresent(By.cssSelector(".list-header"),15);

        // ----- Define the quantity of lists before  --------------
        int listsBefore = driver.findElements(By.cssSelector(".list-header")).size();

        // ------ Define openListMenu and click -----------
        WebElement openListManu = driver.findElement(By.cssSelector(".js-open-list-menu"));
        openListManu.click();

        //-------- Wait and click 'Archive List' menu option ----
        waitUntilElementIsClickable(By.xpath("//*[@class = 'js-close-list']/.."),10);
        WebElement deleteListOption = driver.findElement(By.xpath("//*[@class = 'js-close-list']/.."));
        deleteListOption.click();

        //------- Wait 'Archive List' option disappears ---------
        waitUntilElementDisappears(By.xpath("//*[@class = 'js-close-list']/.."),10);

        // ----- Define the quantity of cards after  --------------
        int listsAfter = driver.findElements(By.cssSelector(".list-header")).size();

        Assert.assertEquals(listsBefore-1,listsAfter,
                "The quantity of lists after deletion is not list before deletion minus one");
    }


}
