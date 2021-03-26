package com.company.tests;

import com.company.helpers.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CurrentBoardTests extends TestBase{
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardHelper qa8HaifaBoard;

    @BeforeMethod
    public void initTests()  {
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);
        qa8HaifaBoard = new CurrentBoardHelper(driver,"QA Haifa8");

        loginPage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.enterLoginPasswordAttl(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        qa8HaifaBoard.openCurrentBoardPage();
        qa8HaifaBoard.waitUntilPageIsLoaded();

    }
    @Test
    public void createNewList()  {

        int listsBefore = qa8HaifaBoard.getListsQuantity();
        qa8HaifaBoard.addNewList("titleList");

        int listsAfter = qa8HaifaBoard.getListsQuantity();
        Assert.assertEquals(listsBefore+1,listsAfter,
                "The quantity of lists after adding is not the quantity before adding plus one");
    }

    @Test
    public void changeListName()  {
        String newHeader = "newLastList2";
        if(qa8HaifaBoard.getAddListButtonName().equals("Add a list")){
            qa8HaifaBoard.addNewList("NewList");
        }
        qa8HaifaBoard.changeLastListName(newHeader);

        Assert.assertEquals(qa8HaifaBoard.getLastListName(), newHeader);
    }

    @Test
    public void addNewCard(){
        if(qa8HaifaBoard.getAddListButtonName().equals("Add a list")){
            qa8HaifaBoard.addNewList("NewList");
        }

        int cardsBefore = qa8HaifaBoard.getCardsQuantity();
        qa8HaifaBoard.addNewCard();
        int cardsAfter = qa8HaifaBoard.getCardsQuantity();

        Assert.assertEquals(cardsBefore+1,cardsAfter,
                "The quantity of cards after adding is not equal to cards before adding plus one");

    }

    @Test
    public void deleteAnyList(){
        if(qa8HaifaBoard.getAddListButtonName().equals("Add a list")){
            qa8HaifaBoard.addNewList("NewList");
        }
        int listsBefore = qa8HaifaBoard.getListsQuantity();
        qa8HaifaBoard.deleteAnyList();
        int listsAfter = qa8HaifaBoard.getListsQuantity();

        Assert.assertEquals(listsBefore-1,listsAfter,
                "The quantity of lists after deletion is not list before deletion minus one");
    }


}
