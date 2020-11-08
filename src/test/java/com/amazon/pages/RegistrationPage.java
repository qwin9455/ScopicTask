package com.amazon.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

	private WebDriver driver;
	
	@FindBy(id = "ap_customer_name")
	private WebElement ap_customer_name;
	
	@FindBy(id = "ap_email")
	private WebElement ap_email;
	
	@FindBy(id = "ap_password")
	private WebElement ap_password;
	
	@FindBy(id = "ap_password_check")
	private WebElement ap_password_check;
	
	@FindBy(id = "continue")
	private WebElement continueButton;
	
	@FindBy(className = "a-spacing-small")
	private WebElement heading;
	
	@FindBy(xpath="//div[@role='alert' and not(contains(@style,'display: none'))]/div/div[@class='a-alert-content']")
	private List<WebElement> alertMessages;
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public RegistrationPage setAp_customer_name(String name) {
		ap_customer_name.clear();
		ap_customer_name.sendKeys(name);
		
		return this;
	}
	public RegistrationPage setAp_customer_name(String name, boolean isSubmit) {
		setAp_customer_name(name);
		if (isSubmit)
			clickContinue();
		
		return this;
	}

	public RegistrationPage setAp_email(String email) {
		ap_email.clear();
		ap_email.sendKeys(email);
		
		return this;
	}
	public RegistrationPage setAp_email(String email, boolean isSubmit) {
		setAp_email(email);
		if (isSubmit)
			clickContinue();
		
		return this;
	}

	public RegistrationPage setAp_password(String password) {
		ap_password.clear();
		ap_password.sendKeys(password);
		
		return this;
	}
	public RegistrationPage setAp_password(String password, boolean isSubmit) {
		setAp_password(password);
		if (isSubmit)
			clickContinue();
		
		return this;
	}

	public RegistrationPage setAp_password_check(String passwordConfirm) {
		ap_password_check.clear();
		ap_password_check.sendKeys(passwordConfirm);
		
		return this;
	}
	public RegistrationPage setAp_password_check(String passwordConfirm, boolean isSubmit) {
		setAp_password_check(passwordConfirm);
		if (isSubmit)
			clickContinue();
		
		return this;
	}
	
	public List<String> getAlertMessages(){
		List<String> alertMsgs = new ArrayList<String>();
		for (WebElement e : alertMessages) {
			alertMsgs.add(e.getText());
		}
		return alertMsgs;
	}

	public void clickContinue() {
		continueButton.click();
	}
	
	public boolean isPageOpened() {
		return heading.getText().toString().equals("Create account");
	}
	
}
