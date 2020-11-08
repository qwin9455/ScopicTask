package com.amazon.parameters;

import org.testng.annotations.DataProvider;

/*Sample data only. This does not cover all possible scenarios.
  Data is used to test if automation script is working.*/
public class DataProviderClass {

	@DataProvider(name = "login-email-invalid")
	public static Object[][] getInvalidEmailAddresses() {
		return new Object[][] { { "notexistemail@gmail.com" }, { "invalidformatemail" } };
	}
	
	@DataProvider(name = "login-valid")
	public static Object[][] getValidLoginCredentials() {
		return new Object[][] { { "mykatest28@gmail.com", "Password123" } };
	}
	
	@DataProvider(name = "reg-invalid")
	public static Object[][] getInvalidRegistrationData() {
		return new Object[][] { 
			{ "", "", "", "", new String[] {"Enter your name", "Enter your email", "Enter your password"} },
			{ "", "myka1234@gmail.com", "password123", "password123", new String[] {"Enter your name"} },
			{ "Myka Tolentino", "", "password123", "password123", new String[] {"Enter your email"} },
			{ "Myka Tolentino", "myka1234@gmail.com", "", "password123", new String[] {"Enter your password"} },
			{ "Myka Tolentino", "myka1234@gmail.com", "password123", "", new String[] {"Type your password again"} },
			{ "Myka Tolentino", "myka1234", "password123", "password123", new String[] {"Enter a valid email address"} },
			{ "Myka Tolentino", "myka1234@gmail.com", "password123", "password", new String[] {"Passwords must match"} },
			{ "Myka Tolentino", "myka1234@gmail.com", "pass", "pass", new String[] {"Passwords must be at least 6 characters."} },
			{ "", "", "", "", new String[] {"Enter your name","Enter your email","Enter your password"} },
			{ "", "mykatest28", "pass", "", new String[] {"Enter your name","Enter a valid email address","Passwords must be at least 6 characters.","Type your password again"} },
			{ "Myka Tolentino", "myka1234", "pass", "", new String[] {"Enter a valid email address","Passwords must be at least 6 characters.","Type your password again"} },
			{ "", "myka1234@gmail.com", "pass", "", new String[] {"Enter your name", "Passwords must be at least 6 characters.","Type your password again"} },
			{ "", "myka1234", "password123", "", new String[] {"Enter your name", "Enter a valid email address", "Type your password again"} },
			{ "", "myka1234", "", "password123", new String[] {"Enter your name", "Enter a valid email address", "Enter your password"} },
			{ "Myka Tolentino", "myka1234@gmail.com", "", "", new String[] {"Enter your password"} },
			{ "", "myka1234@gmail.com", "password123", "", new String[] {"Enter your name", "Type your password again"} },
			{ "", "myka1234@gmail.com", "password123", "password1234", new String[] {"Enter your name", "Passwords must match"} },
			{ "", "myka1234", "password123", "password1234", new String[] {"Enter your name", "Enter a valid email address", "Passwords must match"} },
			{ "Myka Tolentino", "myka1234", "password123", "password123", new String[] {"Enter a valid email address"} },
			{ "Myka Tolentino", "myka1234@gmail.com", "password123", "password1234", new String[] {"Passwords must match"} }
		};
	}
	
	@DataProvider(name = "reg-valid")
	public static Object[][] getValidRegistrationData() {
		return new Object[][] { 
			{ "Myka Tolentino", "m@g.com", "password123", "password123", "You indicated you are a new customer, but an account already exists with the e-mail m@g.com" }
		};
	}
}
