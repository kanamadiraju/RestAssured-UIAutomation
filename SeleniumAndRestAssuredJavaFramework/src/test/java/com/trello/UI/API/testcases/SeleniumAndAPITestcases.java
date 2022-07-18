package com.trello.UI.API.testcases;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.trello.API.testcases.RestAssuredAPITestcases;
import com.trello.qa.Analyzer.CustomListener;
import com.trello.qa.base.TestBase;
import com.trello.qa.pages.BoardWindowPage;
import com.trello.qa.pages.BoardsPage;
import com.trello.qa.pages.LoginPage;

import io.restassured.path.json.JsonPath;

@Listeners(CustomListener.class)
public class SeleniumAndAPITestcases extends TestBase {
	
	LoginPage loginPage;
	BoardsPage boardPage;
	BoardWindowPage boardWindowPage;
	RestAssuredAPITestcases restAssured = new RestAssuredAPITestcases();
		
		public SeleniumAndAPITestcases() {
			super();
		}
			

		@BeforeTest
		public void setUp() {
			initialization();
			loginPage = new LoginPage();
			boardPage = new BoardsPage();
			boardWindowPage = new BoardWindowPage();
			restAssured = new RestAssuredAPITestcases();
			loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

		}
		
		@Test
		public void TC01() throws InterruptedException {
			
			//Creating new board using API
			JsonPath newBoard= restAssured.createNewBoard(prop.getProperty("boardName"));
			System.out.println("Board ID is: "+newBoard.get("id"));
			System.out.println("Board Name is: "+newBoard.get("name"));
		    Assert.assertNotNull(newBoard, "Not Created.");
		    Assert.assertEquals(newBoard.get("name"), prop.getProperty("boardName"), "Not matching");
		    
		    //create new list using API
			JsonPath newList = restAssured.createList(newBoard.get("id"), prop.getProperty("listName1"));
			System.out.println("List ID is: "+newBoard.get("id"));
			System.out.println("List Name is: "+newBoard.get("name"));
			Assert.assertNotNull(newList, "Not Created.");
			Assert.assertEquals(newList.get("name"), prop.getProperty("listName1"), "Not matching");
			
			//create new card using API
			JsonPath newCard1 = restAssured.createCardonList(newList.get("id"), prop.getProperty("cardName1"));
			System.out.println("Card ID1 is: "+newCard1.get("id"));
			System.out.println("Card  Name1 is: "+newCard1.get("name"));
			Assert.assertEquals(newCard1.get("name"), prop.getProperty("cardName1"), "Not matching");
			
			JsonPath newCard2 = restAssured.createCardonList(newList.get("id"), prop.getProperty("cardName2"));
			System.out.println("Card ID2 is: "+newCard2.get("id"));
			System.out.println("Card Name2 is: "+newCard2.get("name"));
			Assert.assertEquals(newCard2.get("name"), prop.getProperty("cardName2"), "Not matching");

			
			//Open the board using Selenium created via API
			boardPage.openBoard(newBoard.get("name"));
			
			//Archive all list and boards using selenium
			boardWindowPage.archiveAllLists();
			
			//close workspace board using selenium
			boardWindowPage.closeBoardWindow();			
			
			//Delete the board using API
			restAssured.deleteBoard(newBoard.get("id"));
		
			
		}
		
		@Test
		public void TC02() throws InterruptedException {
			
			//Creating new board using API
			JsonPath newBoard= restAssured.createNewBoard(prop.getProperty("boardName"));
			System.out.println("Board ID is: "+newBoard.get("id"));
			System.out.println("Board Name is: "+newBoard.get("name"));
		    Assert.assertNotNull(newBoard, "Not Created.");
		    Assert.assertEquals(newBoard.get("name"), prop.getProperty("boardName"), "Not matching");
		    
		    //create new list using API
			JsonPath newList = restAssured.createList(newBoard.get("id"), prop.getProperty("listName1"));
			System.out.println("List ID is: "+newBoard.get("id"));
			System.out.println("List Name is: "+newBoard.get("name"));
			Assert.assertNotNull(newList, "Not Created.");
			Assert.assertEquals(newList.get("name"), prop.getProperty("listName1"), "Not matching");
			
			//create new card using API
			JsonPath newCard1 = restAssured.createCardonList(newList.get("id"), prop.getProperty("cardName1"));
			System.out.println("Card ID1 is: "+newCard1.get("id"));
			System.out.println("Card  Name1 is: "+newCard1.get("name"));
			Assert.assertEquals(newCard1.get("name"), prop.getProperty("cardName1"), "Not matching");
			

			
			//Open the board using Selenium created via API
			boardPage.openBoard(newBoard.get("name"));
			
			//Create checklist on card using selenium
			boardWindowPage.createChecklistOnCard(newCard1.get("name"));
			
			//close workspace board using selenium
			boardWindowPage.closeBoardWindow();		
			
			//Delete the board using API
			restAssured.deleteBoard(newBoard.get("id"));
						
			
		}
		
		@Test
		public void TC03() throws InterruptedException {
		
			
			JsonPath newBoard= restAssured.createNewBoard(prop.getProperty("boardName"));
			JsonPath newList1 = restAssured.createList(newBoard.get("id"), prop.getProperty("listName1"));
			JsonPath newList2 = restAssured.createList(newBoard.get("id"), prop.getProperty("listName2"));
			JsonPath newCard1 = restAssured.createCardonList(newList1.get("id"), prop.getProperty("cardName1"));
			JsonPath newCard2 = restAssured.createCardonList(newList1.get("id"), prop.getProperty("cardName2"));		
			

			//Open the board using Selenium created via API
			boardPage.openBoard(newBoard.get("name"));
			
			//Move cards between list using selenium
			boardWindowPage.moveCardBetweenList(prop.getProperty("listName1"), prop.getProperty("listName2"));
					
			
			//close workspace board using selenium
			boardWindowPage.closeBoardWindow();		
			
			//Delete the board using API
			restAssured.deleteBoard(newBoard.get("id"));
			
			
		}
		
		@Test
		public void TC04() throws InterruptedException {
		
			//Create board, list and card using API
			JsonPath newBoard= restAssured.createNewBoard(prop.getProperty("boardName"));
			JsonPath newList1 = restAssured.createList(newBoard.get("id"), prop.getProperty("listName1"));
			JsonPath newList2 = restAssured.createList(newBoard.get("id"), prop.getProperty("listName2"));
			JsonPath newCard1 = restAssured.createCardonList(newList1.get("id"), prop.getProperty("cardName1"));
			JsonPath newCard2 = restAssured.createCardonList(newList1.get("id"), prop.getProperty("cardName2"));
			JsonPath newCard3 = restAssured.createCardonList(newList1.get("id"), prop.getProperty("cardName3"));
			
			//Open the board using Selenium created via API
			boardPage.openBoard(newBoard.get("name"));
			
			//Add Description and labels on card using selenium
			boardWindowPage.addLabelToCard(prop.getProperty("cardName1"), "green");
			boardWindowPage.addDescriptionToCard(prop.getProperty("cardName1"));
			
			//close workspace board using selenium
			boardWindowPage.closeBoardWindow();	
			
			//Delete the board using API
			restAssured.deleteBoard(newBoard.get("id"));
			
			
		}
		
		@Test
		public void TC05() throws InterruptedException {
		
			//Create board, list and card using API
			JsonPath newBoard= restAssured.createNewBoard(prop.getProperty("boardName"));
			JsonPath newList1 = restAssured.createList(newBoard.get("id"), prop.getProperty("listName1"));
			JsonPath newList2 = restAssured.createList(newBoard.get("id"), prop.getProperty("listName2"));
			JsonPath newCard1 = restAssured.createCardonList(newList1.get("id"), prop.getProperty("cardName1"));
			JsonPath newCard2 = restAssured.createCardonList(newList1.get("id"), prop.getProperty("cardName2"));
			JsonPath newCard3 = restAssured.createCardonList(newList1.get("id"), prop.getProperty("cardName3"));
			
			//Open the board using Selenium created via API
			boardPage.openBoard(newBoard.get("name"));
			
			//Add Actions on card using selenium
			boardWindowPage.createChecklistOnCard(newCard1.get("name"));
			boardWindowPage.addLabelToCard(prop.getProperty("cardName1"), "green");
			boardWindowPage.addDescriptionToCard(prop.getProperty("cardName2"));
			boardWindowPage.addWatchCard(prop.getProperty("cardName3"));
			
			//close workspace board using selenium
			boardWindowPage.closeBoardWindow();	
			
			//Delete the board using API
			restAssured.deleteBoard(newBoard.get("id"));
			
			
		}
		
		@AfterTest
		public void tearDown() {
	
			driver.quit();
		}
		
}		
