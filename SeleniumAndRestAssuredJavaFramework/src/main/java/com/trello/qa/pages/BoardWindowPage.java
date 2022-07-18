package com.trello.qa.pages;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.trello.qa.base.TestBase;

public class BoardWindowPage extends TestBase {
	
	LoginPage login = new LoginPage();
	Actions action = new Actions(driver);
	
	@FindBy(xpath="//span[@class='placeholder']")
	WebElement addListPlaceholder;
	
	@FindBy(xpath="//input[@class='list-name-input']")
	WebElement listTitleTxtField;
	
	@FindBy(xpath="//input[@value='Add list']")
	WebElement addListBtn;
	
	@FindBy(xpath="//span[text()='Add a card']")
	WebElement addCardLink;
	
	@FindBy(xpath="//textarea[@placeholder='Enter a title for this card…']")
	WebElement cardTxtField;
	
	@FindBy(xpath="//input[@value='Add card']")
	WebElement addCardBtn;
	
	@FindBy(xpath="//a[@class='js-close-list' and text()='Archive this list']")
	WebElement listActionArchive;
	
	@FindBy(xpath="//div[@id='content']/div[@class='board-wrapper']")
	WebElement boardWindow;
	
	@FindBy(xpath="//a[@aria-label='Back to home']")
	WebElement homeIcon;
	
	@FindBy(xpath="//span[@class='icon-sm icon-edit list-card-operation dark-hover js-open-quick-card-editor js-card-menu']")
	WebElement editCardIcon;
	
	@FindBy(xpath="//span[text()='Move']")
	WebElement editCardMoveAction;
	
	@FindBy(xpath="//select[@class='js-select-list']")
	WebElement listDropdown;
	
	@FindBy(xpath="//input[@value='Move']")
	WebElement moveBtn;
	
	@FindBy(xpath="//div[@class='card-detail-window u-clearfix']")
	WebElement cardDetailsWindow;
	
	@FindBy(xpath="//span[text()='Checklist']")
	WebElement checklistAction;
	
	@FindBy(id="id-checklist")
	WebElement checklistTitle;
	
	@FindBy(xpath="//input[@value='Add']")
	WebElement addChecklistBtn;
	
	@FindBy(xpath="//textarea[@placeholder='Add an item']")
	WebElement addAnItemTextArea;
	
	@FindBy(xpath="//a[@class='icon-md icon-close dialog-close-button js-close-window']")
	WebElement closeCardDetailsWindow;
	
	@FindBy(xpath="//div[@title='Checklist items']")
	WebElement checklistBadge;
	
	@FindBy(xpath="//textarea[@placeholder='Add a more detailed description…']")
	WebElement descriptionTextArea;
	
	@FindBy(xpath="//input[@value='Save']")
	WebElement saveBtn;
	
	@FindBy(xpath="//div[@title='This card has a description.']")
	WebElement descriptionBadge;
	
	@FindBy(xpath="//a[@title='Labels']")
	WebElement labelAction;
	
	
	@FindBy(xpath="//span[text()='Watch']")
	WebElement watchAction;
	
	@FindBy(xpath="//span[@class='on']")
	WebElement watchCheckMark;
	
	@FindBy(xpath="//div[@title='You are watching this card.']")
	WebElement watchIconText;
	
	@FindBy(xpath="//div[@class='list-header-extras']")
	WebElement listActionIcon;
	
	@FindBy(xpath="//a[@class='js-move-cards' and text()='Move all cards in this list…']")
	WebElement moveAllCardsAction;
	

	
	private WebDriverWait wait = new WebDriverWait(driver, 7, 50);

//Initializing the page object
	public BoardWindowPage() {
		PageFactory.initElements(driver, this);
	}
	
	
//Move cards between Source list and destination list
	public void moveCard(String cardName, String srcList, String destList) throws InterruptedException {
		

		action.moveToElement(driver.findElement(By.xpath("//span[text()='"+cardName+"']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+cardName+"']/../..//span[@class='icon-sm icon-edit list-card-operation dark-hover js-open-quick-card-editor js-card-menu']"))).click();

		editCardMoveAction.click();
		Select se = new Select(listDropdown);
		se.selectByVisibleText(destList);
		
		moveBtn.click();

		WebElement element1 = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[text()='"+destList+"']/../..//span[@class='list-card-title js-card-name']")));
		JavascriptExecutor js= (JavascriptExecutor) driver; 
		String strtext=(String) js.executeScript("return arguments[0].lastChild.textContent;", element1);

		Assert.assertEquals(strtext, cardName, "Card moved between List successfully");
	
	}
	
	
//Close the workspace board window
	public void closeBoardWindow() {
		
		 wait.until(ExpectedConditions.visibilityOf(boardWindow));
		 homeIcon.click();
		 wait.until(ExpectedConditions.visibilityOf(login.boardPage));

	}
	
//archive all list	
	
	public void archiveAllLists() {
        List<WebElement> listMenus = new ArrayList<WebElement>(driver.findElements(By.xpath("//div[@class='list-header-extras']")));
        int elementSize = listMenus.size();
        listMenus.forEach(this::archiveList);
  
        if (elementSize >= 5) {
            archiveAllLists();
        }
    }
	
//Archive single list	
	public void archiveList(WebElement element) {
	
        element.click();
        listActionArchive.click();
    }
	
	
	
// create card inside list	
	public void createDemoCard(String listName, String cardNameTitle) {
				

		WebElement element = driver.findElement(By.xpath("//textarea[text()='"+listName+"']/../..//span[text()='Add a card']"));
		
		if(element.isDisplayed()){
			element.click();
		}
		wait.until(ExpectedConditions.visibilityOf(cardTxtField));
		cardTxtField.sendKeys(cardNameTitle);	
		addCardBtn.click();
		
				
	}
	
	
// create list inside board	
	public void createDemoList(String demoListName) {
		
		if(addListPlaceholder.isDisplayed()) {
			 wait.until(ExpectedConditions.visibilityOf(addListPlaceholder)).click();
		}		
		 wait.until(ExpectedConditions.visibilityOf(listTitleTxtField));
		 listTitleTxtField.sendKeys(demoListName);	
		 addListBtn.click();				
	}
	

//add description to card	
public void addDescriptionToCard(String cardName) {
		
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + cardName + "']"))).click();
	wait.until(ExpectedConditions.visibilityOf(cardDetailsWindow));
	wait.until(ExpectedConditions.visibilityOf(descriptionTextArea)).click();
	descriptionTextArea.sendKeys("Adding more scenario requires docuement");
	saveBtn.click();
	closeCardDetailsWindow.click();
	wait.until(ExpectedConditions.visibilityOf(descriptionBadge));
					
	}

//Add label to card
public void addLabelToCard(String cardName, String labelColor) {
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + cardName + "']"))).click();
	wait.until(ExpectedConditions.visibilityOf(cardDetailsWindow));
	labelAction.click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-color='"+labelColor+"']"))).click();
	closeCardDetailsWindow.click();
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, '"+labelColor+"')]")));
					
	}

//Add watchers on card
public void addWatchCard(String cardName) {
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + cardName + "']"))).click();
	wait.until(ExpectedConditions.visibilityOf(cardDetailsWindow));
	watchAction.click();
	watchCheckMark.isDisplayed();
	//wait.until(ExpectedConditions.visibilityOf(watchCheckMark));
	closeCardDetailsWindow.click();
	wait.until(ExpectedConditions.visibilityOf(watchIconText));
	
					
	}


//move single card between source list and destination ist
	public void moveCardBetweenList(String srcMoveCardList, String destMoveCardList) {
	
	action.moveToElement(driver.findElement(By.xpath("//textarea[text()='"+srcMoveCardList+"']/../..//div[@class='list-header-extras']")));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[text()='"+srcMoveCardList+"']/../..//div[@class='list-header-extras']"))).click();
	wait.until(ExpectedConditions.visibilityOf(moveAllCardsAction));
	moveAllCardsAction.click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='"+destMoveCardList+"']"))).click();

	
	List<WebElement> list = driver.findElements(By.xpath("//textarea[text()='"+destMoveCardList+"']/../..//span[@class='list-card-title js-card-name']"));
	if(list.size() > 0) {
		System.out.println("Cards moved between list"); 
	}
	else
		System.out.println("Cards not moved between list"); 
	
					
	}

//create checklist inside card
	public void createChecklistOnCard(Object object) {
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + object + "']"))).click();
	wait.until(ExpectedConditions.visibilityOf(cardDetailsWindow));
	checklistAction.click();
	checklistTitle.clear();		
	checklistTitle.sendKeys("TestChecklist");
	addChecklistBtn.click();
	wait.until(ExpectedConditions.visibilityOf(addAnItemTextArea));
	addAnItemTextArea.sendKeys("AddItemtotheChecklist");
	addChecklistBtn.click();
	closeCardDetailsWindow.click();
	wait.until(ExpectedConditions.visibilityOf(checklistBadge));
	
}


	
	

}
