package com.amazon.tests;

import org.testng.annotations.Test;

import com.amazon.pages.HomePage;
import com.amazon.pages.LoginPage;
import com.amazon.pages.RegistrationPage;
import com.amazon.pages.WarningPage;
import com.amazon.parameters.DataProviderClass;

import org.testng.annotations.BeforeTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class RegistrationTests {
	String path = "C:\\webdrivers\\chromedriver.exe";
	WebDriver driver;

	HomePage homePage;
	RegistrationPage registrationPage;

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", path);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.amazon.com/");
		driver.manage().window().maximize();

		homePage = new HomePage(driver);
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}

	@Test(description = "User should see the registration page correctly")
	public void shouldOpenRegistrationPageCorrectly() {
		registrationPage = homePage.clickSignIn().clickCreateAccount();
		Assert.assertTrue(registrationPage.isPageOpened());
	}

	
	@Test(description = "User should see the approriate alert messages if field(s) are invalid", dataProvider = "reg-invalid", dataProviderClass = DataProviderClass.class, dependsOnMethods = "shouldOpenRegistrationPageCorrectly")
	public void shouldDisplayAlertMessagesWhenFieldIsInvalid(String name, String emailAddress, String password, String passwordConfirm, String[] messages) {
		List<String> alertMessages = registrationPage.setAp_customer_name(name)
		.setAp_email(emailAddress)
		.setAp_password(password)
		.setAp_password_check(passwordConfirm, true)
		.getAlertMessages();
		Assert.assertEquals(alertMessages, Arrays.asList(messages));
	}
	
	@Test(description = "User should see the warning message if email address is already in use", dataProvider = "reg-valid", dataProviderClass = DataProviderClass.class, dependsOnMethods = "shouldOpenRegistrationPageCorrectly")
	public void shouldDisplayWarningMessageWhenEmailAddressInUse(String name, String emailAddress, String password, String passwordConfirm, String message) {
		registrationPage.setAp_customer_name(name)
		.setAp_email(emailAddress)
		.setAp_password(password)
		.setAp_password_check(passwordConfirm, true);
		
		WarningPage warningPage = new WarningPage(driver);
		
		String warningMessage = warningPage.getWarningMessage();
		String emailExist = warningPage.getEmailAddressExist();
		
		Assert.assertEquals(warningMessage, message);
		Assert.assertEquals(emailExist, emailAddress);
	}
	
}
