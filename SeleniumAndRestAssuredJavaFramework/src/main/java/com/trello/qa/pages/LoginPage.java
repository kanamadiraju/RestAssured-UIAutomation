package com.trello.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.trello.qa.base.TestBase;

public class LoginPage extends TestBase {
	
	
	//Page factor - Object Repository
	@FindBy(id="user")
	WebElement username;
	
	@FindBy(id="login")
	WebElement loginWithAtlassian;
	
	@FindBy(xpath = "//input[@value='Log in with Atlassian']")
	WebElement atlassianLogin;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(id="login-submit")
	WebElement login;
	
	@FindBy(xpath="//img[@class='trello-main-logo']")
	WebElement trelloLogo;
	
	@FindBy(xpath="//div[@class='member-boards-view']")
	WebElement boardPage;
	
	private WebDriverWait wait = new WebDriverWait(driver, 7, 50);
	
	//Initializing the page object
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	//Actions
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
//validate trello image in loginpage	
	public boolean validateTrelloImage() {
		return trelloLogo.isDisplayed();
	}
	
	
//Login trello site	
	public void login(String uname, String pwd) {

		username.sendKeys(uname);
	    wait.until(ExpectedConditions.visibilityOf(atlassianLogin));
	    atlassianLogin.submit();
	    password.sendKeys(pwd);
	    login.submit();
	    wait.until(ExpectedConditions.visibilityOf(boardPage));

	}


}
