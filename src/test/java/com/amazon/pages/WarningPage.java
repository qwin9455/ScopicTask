package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Optional;

public class WarningPage {

	private WebDriver driver;

	@FindBy(xpath = "//div[@class='a-section auth-pagelet-container']//div[@class='a-alert-content']/p")
	WebElement warningMessage;
	
	@FindBy(xpath = "//div[@class='a-section auth-pagelet-container']//div[@class='a-alert-content']//b")
	WebElement emailAddressExist;
	
	
	
	public WarningPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getEmailAddressExist() {
		return emailAddressExist.getText();
	}
	
	public String getWarningMessage() {
		return warningMessage.getText();
	}
	
}
