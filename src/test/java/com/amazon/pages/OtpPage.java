package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Optional;

public class OtpPage {

	private WebDriver driver;

	@FindBy(name = "code")
	private WebElement otp;
	
	@FindBy(id = "a-autoid-0-announce")
	private WebElement continueButton;
	
	
	
	public OtpPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public OtpPage setOtp(String otpString) {
		otp.clear();
		otp.sendKeys(otpString);
		
		return this;
	}
	public void clickContinue() {
		continueButton.click();
	}	
	
	public boolean isPageOpened() {
		return otp.isDisplayed() && continueButton.isDisplayed();
	}
}
