package com.trello.selenium.testcases;


import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.trello.qa.Analyzer.CustomListener;
import com.trello.qa.base.TestBase;
import com.trello.qa.pages.BoardWindowPage;
import com.trello.qa.pages.BoardsPage;
import com.trello.qa.pages.LoginPage;

import junit.framework.Assert;

@Listeners(CustomListener.class)
public class SeleniumUIAutomationTestcases extends TestBase {
	
	LoginPage loginPage;
	BoardsPage boardPage;
	BoardWindowPage boardWindowPage;
		
		public SeleniumUIAutomationTestcases() {
			super();
		}
			

		@BeforeTest
		public void setUp() {

			initialization();
			loginPage = new LoginPage();
			boardPage = new BoardsPage();
			boardWindowPage = new BoardWindowPage(); 
			loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		}
		
		@Test(priority=-1)
		public void createBoardTest() {
	
			boardPage.createBoard(prop.getProperty("boardName"));
			boolean boardName = boardPage.validateCreatedBoard(prop.getProperty("boardName"));
			Assert.assertTrue(boardName);
			boardWindowPage.closeBoardWindow();
			
		}
		
		@Test
		public void createListTest() {
			
			boardPage.openBoard(prop.getProperty("boardName"));
			boardWindowPage.createDemoList(prop.getProperty("listName1"));
			boardWindowPage.closeBoardWindow();
		
		}
		
		
		@Test 
		public void addCardTest() {
			
			boardPage.openBoard(prop.getProperty("boardName"));
			boardWindowPage.archiveAllLists();
			boardWindowPage.createDemoList(prop.getProperty("listName1"));
			boardWindowPage.createDemoCard(prop.getProperty("listName1"), prop.getProperty("cardName1"));
			boardWindowPage.closeBoardWindow();
			
		}
		
		@Test 
		public void archiveListTest() {
			
			boardPage.openBoard(prop.getProperty("boardName"));
			boardWindowPage.archiveAllLists();
			boardWindowPage.closeBoardWindow();
						
		}
		
		@Test
		public void moveSingleCardTest() throws InterruptedException {
			
			boardPage.openBoard(prop.getProperty("boardName"));
			boardWindowPage.archiveAllLists();
			boardWindowPage.createDemoList(prop.getProperty("listName1"));
			boardWindowPage.createDemoList(prop.getProperty("listName2"));
			boardWindowPage.createDemoCard(prop.getProperty("listName1"), prop.getProperty("cardName1"));			
			boardWindowPage.createDemoCard(prop.getProperty("listName1"), prop.getProperty("cardName2"));			
			boardWindowPage.createDemoCard(prop.getProperty("listName1"), prop.getProperty("cardName3"));
			boardWindowPage.moveCard(prop.getProperty("cardName2"), prop.getProperty("listName1"), prop.getProperty("listName2"));
			boardWindowPage.closeBoardWindow();
												
		}
		
		@Test 
		public void moveAllCardsBetweenListTest() throws InterruptedException {
			
			boardPage.openBoard(prop.getProperty("boardName"));
			boardWindowPage.archiveAllLists();
			boardWindowPage.createDemoList(prop.getProperty("listName1"));
			boardWindowPage.createDemoList(prop.getProperty("listName2"));
			boardWindowPage.createDemoCard(prop.getProperty("listName1"), prop.getProperty("cardName1"));
			boardWindowPage.createDemoCard(prop.getProperty("listName1"), prop.getProperty("cardName2"));
			boardWindowPage.createDemoCard(prop.getProperty("listName1"), prop.getProperty("cardName3"));
			boardWindowPage.moveCardBetweenList(prop.getProperty("listName1"), prop.getProperty("listName2"));
			boardWindowPage.closeBoardWindow();
												
		}
		
		
		@Test 
		public void addCardActions() throws InterruptedException {
			
			boardPage.openBoard(prop.getProperty("boardName"));	
			boardWindowPage.archiveAllLists();
			boardWindowPage.createDemoList(prop.getProperty("listName1"));
			boardWindowPage.createDemoList(prop.getProperty("listName2"));
			boardWindowPage.createDemoList(prop.getProperty("listName3"));
			boardWindowPage.createDemoList(prop.getProperty("listName4"));
			
			
			boardWindowPage.createDemoCard(prop.getProperty("listName2"), prop.getProperty("cardName1"));			
			boardWindowPage.createDemoCard(prop.getProperty("listName2"), prop.getProperty("cardName2"));
			boardWindowPage.createDemoCard(prop.getProperty("listName2"), prop.getProperty("cardName3"));
			

			
			boardWindowPage.createChecklistOnCard(prop.getProperty("cardName1"));
			
			boardWindowPage.addLabelToCard(prop.getProperty("cardName1"), "green");
			boardWindowPage.addDescriptionToCard(prop.getProperty("cardName1"));
			boardWindowPage.addWatchCard(prop.getProperty("cardName1"));
			
			boardWindowPage.closeBoardWindow();
												
		}
		

		
		@AfterTest
		public void tearDown() {
				driver.quit();
		}
		

}
