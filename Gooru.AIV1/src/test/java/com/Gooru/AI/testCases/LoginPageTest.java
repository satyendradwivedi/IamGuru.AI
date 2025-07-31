package com.Gooru.AI.testCases;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Gooru.AI.pageObjects.ChatBot;
import com.Gooru.AI.pageObjects.LoginPage;
import com.Gooru.AI.utilities.ReadConfig;

public class LoginPageTest extends BaseClass {

	LoginPage lp;
    ChatBot cb;
	// Configuration details
	ReadConfig rc = new ReadConfig();
	String email = rc.getEmail();
	String password = rc.getPassword();
	String usernametxt = rc.getusr2();
	String passwordtxt = rc.getpass2();
	String gemail = rc.getGemail();
	String gpass = rc.getGPassword();

	@BeforeMethod

	public void setUp() {
		lp = new LoginPage(driver);
		cb=new ChatBot(driver);

	}

	// Test case for valid login
	@Test(priority = 1, enabled = true)
	public void loginWithValidData() throws InterruptedException, IOException {
	

		if (email.equalsIgnoreCase("smithlee@yopmail.com") && password.equalsIgnoreCase("Test@1234")) {
			lp.setEmail(email);
			logger.info("Email  is provided: " + email);
			lp.setPassword(password);
			logger.info("Password is provided");
			lp.clickSigninButton();
			//Assert.assertTrue(true, "Login with valid credentials successful.");
			//lp.clickLogoutButton();
		} else {
			logger.error("Email or password is missing");
			Assert.fail("Email or Password is required");
		}
	}

	// Test case for invalid login
	@Test(priority = 2, enabled = false)
	public void loginWithInvalidData() {
		
		lp.setEmail(email);
		logger.info("Invalid email is provided: " + usernametxt);
		lp.setPassword(passwordtxt);
		logger.info("Invalid password is provided");
		lp.clickSigninButton();

		String validationMessage = lp.valmessage();
		System.out.println(validationMessage);
		Assert.assertEquals(validationMessage, "Invalid username or password.",
				"Validation message mismatch for invalid credentials.");
	}

	// Test case for empty username and password
	@Test(priority = 3, enabled = false)
	public void loginWithEmptyUsernameAndPassword() {
		
		//lp.setUserName("");
		logger.info("Empty username is provided");
		//lp.setPassword("");
		logger.info("Empty password is provided");
		lp.clickSigninButton();

		String usernameValidation = lp.valmessageemptyuser();
		String passwordValidation = lp.valmessageemptypass();

		logger.info("Validation messages received: Username - " + usernameValidation + ", Password - "
				+ passwordValidation);
		Assert.assertEquals(usernameValidation, "Email is required", "Username validation message mismatch");
		Assert.assertEquals(passwordValidation, "Password is required", "Password validation message mismatch");
	}

	// Test case for Google Login
	@Test(priority = 4, enabled = false)
	public void loginWithGoogle() {

		// login with Google icon
		lp.gLogin();
		lp.setGoogleemail(gemail);
		lp.setGooglPassword(gpass);
	}
	@Test(priority = 3, enabled = true)
	public void chatBot() {

		// login with Google icon
		//cb.chatQuestion(email);
		//cb.clickButton();
		
	}
	
}