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

	@Test(description = "User should see an alert message if the email address is not provided", dependsOnMethods = "shouldOpenLoginPageCorrectly")
	public void shouldDisplayAnAlertMessageWhenEmailIsEmpty() {
		String alertMessage = loginPage.setAp_email("", true).getEmailMissingAlertMessage();
		Assert.assertEquals(alertMessage, "Enter your email or mobile phone number");
	}

	@Test(description = "User should not login if the email address does not exist", dataProvider = "emailaddresses", dataProviderClass = DataProviderClass.class, dependsOnMethods = "shouldOpenLoginPageCorrectly")
	public void shouldNotLoginUserWhenEmailDoesNotExist(String emailAddress) {
		String errorMessage = loginPage.setAp_email(emailAddress, true).getErrorMessage();
		Assert.assertTrue(loginPage.isPageOpened());
		Assert.assertEquals(errorMessage, "We cannot find an account with that email address");
	}
}
