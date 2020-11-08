package com.amazon.tests;

import org.testng.annotations.Test;

import com.amazon.pages.HomePage;
import com.amazon.pages.LoginPage;
import com.amazon.pages.RegistrationPage;
import com.amazon.parameters.DataProviderClass;

import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class LoginTests {
	String path = "C:\\webdrivers\\chromedriver.exe";
	WebDriver driver;

	HomePage homePage;
	LoginPage loginPage;
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

	@Test(description = "User should see the login page correctly")
	public void shouldOpenLoginPageCorrectly() {
		loginPage = homePage.clickSignIn();
		Assert.assertTrue(loginPage.isPageOpened());
	}
	@Test(description = "User should see the login fields", dependsOnMethods = "shouldOpenLoginPageCorrectly")
	public void shouldDisplayAllLoginFields() {
		Assert.assertTrue(loginPage.isMandatoryFieldsDisplayed());
	}

	@Test(description = "User should see an alert message if the email address is not provided", dependsOnMethods = "shouldDisplayAllLoginFields", priority = 0)
	public void shouldDisplayAnAlertMessageWhenEmailIsEmpty() {
		String alertMessage = loginPage.setAp_email("", true).getAlertMessage();
		Assert.assertEquals(alertMessage, "Enter your email or mobile phone number");
	}

	@Test(description = "User should not login if the email address does not exist", dataProvider = "login-email-invalid", dataProviderClass = DataProviderClass.class, dependsOnMethods = "shouldDisplayAllLoginFields", priority = 0)
	public void shouldNotLoginUserWhenEmailDoesNotExist(String emailAddress) {
		String errorMessage = loginPage.setAp_email(emailAddress, true).getErrorMessage();
		Assert.assertTrue(loginPage.isPageOpened());
		Assert.assertEquals(errorMessage, "We cannot find an account with that email address");
	}
	
	@Test(description = "User should not login if password is invalid", dependsOnMethods = "shouldDisplayAllLoginFields", priority = 1)
	public void shouldNotLoginUserWhenPasswordIsInvalid() {
		loginPage.setAp_email("mykatest28@gmail.com", true);
		loginPage.setAp_password("Password", true);
		Assert.assertEquals(loginPage.getErrorMessage(), "Your password is incorrect");
	}
	
	@Test(description = "User should login if the email address already exist", dataProvider = "login-valid", dataProviderClass = DataProviderClass.class, dependsOnMethods = "shouldDisplayAllLoginFields")
	public void shouldLoginUserWhenEmailExist(String emailAddress, String password) {
		loginPage.setAp_email(emailAddress, true);
		homePage = loginPage.setAp_password(password, true);
		
		Assert.assertTrue(homePage.isPageOpened());
	}
}
