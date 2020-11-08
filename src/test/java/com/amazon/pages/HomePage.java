package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	private WebDriver driver;
	@FindBy(id = "nav-logo-sprites")
	private WebElement amazonLogo;
	
	@FindBy(id = "nav-link-accountList")
	private WebElement signIn;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public LoginPage clickSignIn() {
		signIn.click();
		return new LoginPage(driver);
	}
	
	public boolean isPageOpened() {
		return amazonLogo.isDisplayed() && signIn.isDisplayed();
	}

}
