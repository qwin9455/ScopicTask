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
	private WebElement ap_email;
	
	@FindBy(id = "ap_password")
	private WebElement ap_password;

	@FindBy(id = "continue")
	private WebElement continueButton;
	
	@FindBy(id = "signInSubmit")
	private WebElement signInSubmitButton;
	
	@FindBy(id = "createAccountSubmit")
	private WebElement createAccountSubmitButton;

	@FindBy(className = "a-spacing-small")
	private WebElement heading;
	
	@FindBy(xpath = "//div[@id='auth-error-message-box' and not(contains(@style,'display: none'))]//span[@class='a-list-item']")
	private WebElement errorMessageBox;
	
	@FindBy(xpath="//div[@role='alert' and not(contains(@style,'display: none'))]/div/div[@class='a-alert-content']")
	private WebElement alertMessage;
	
	
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
	
	public HomePage setAp_password(String password) {
		ap_password.clear();
		ap_password.sendKeys(password);
		
		return new HomePage(driver);
	}
	
	public HomePage setAp_password(String password, boolean isSubmit) {
		setAp_password(password);
		if (isSubmit) {
			clickSignIn();
		}
		
		return new HomePage(driver);
	}

	public String getErrorMessage() {
		return errorMessageBox.getText();
	}
	
	public String getAlertMessage() {
		return alertMessage.getText();
	}
	
	public void clickContinue() {
		continueButton.click();
	}
	
	public void clickSignIn() {
		signInSubmitButton.click();
	}
	
	public RegistrationPage clickCreateAccount() {
		createAccountSubmitButton.click();
		return new RegistrationPage(driver);
	}
	
	public boolean isMandatoryFieldsDisplayed() {
		return ap_email.isDisplayed() && continueButton.isDisplayed();
	}
	
	public boolean isPageOpened() {
		return heading.getText().toString().equals("Sign-In");
	}
}
