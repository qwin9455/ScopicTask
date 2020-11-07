package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Optional;

public class LoginPage {

	private WebDriver driver;

	@FindBy(id = "ap_email")
	WebElement ap_email;

	@FindBy(id = "continue")
	WebElement continueButton;

	@FindBy(id = "createAccountSubmit")
	WebElement createAccountSubmitButton;

	@FindBy(className = "a-spacing-small")
	WebElement heading;
	
	@FindBy(xpath = "//div[@id='auth-error-message-box' and not(contains(@style,'display: none'))]//span[@class='a-list-item']")
	WebElement errorMessageBox;
	
	@FindBy(xpath="//div[@role='alert' and not(contains(@style,'display: none'))]/div/div[@class='a-alert-content']")
	WebElement emailMissingAlert;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	public LoginPage setAp_email(String email) {
		ap_email.clear();
		ap_email.sendKeys(email);
		
		return this;
	}
	public LoginPage setAp_email(String email, boolean isSubmit) {
		setAp_email(email);
		if (isSubmit) {
			clickContinue();
		}
		
		return this;
	}

	public String getErrorMessage() {
		return errorMessageBox.getText();
	}
	public String getEmailMissingAlertMessage() {
		return emailMissingAlert.getText();
	}
	
	public void clickContinue() {
		continueButton.click();
	}
	public RegistrationPage clickCreateAccount() {
		createAccountSubmitButton.click();
		return new RegistrationPage(driver);
	}
	public boolean isPageOpened() {
		return heading.getText().toString().equals("Sign-In") && ap_email.isDisplayed() && continueButton.isDisplayed();
	}
}
