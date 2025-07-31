package com.Gooru.AI.testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Gooru.AI.pageObjects.Email;
import com.Gooru.AI.pageObjects.ForgotPassword;
import com.Gooru.AI.pageObjects.LoginPage;
import com.Gooru.AI.pageObjects.Registration;
import com.Gooru.AI.utilities.ReadConfig;

public class ForgotPasswordTest extends BaseClass {
	ReadConfig rc = new ReadConfig();
	
	String Email = rc.getREmail();
	String password = rc.getRpassword();
	String cpassword = rc.getRCpassword();
    String emailurl = rc.getTempemailURL();
	String msg = "Verify successfully";
	
	String newpassword=rc.getResetnewpassword();
	String confirmnewpassword=rc.getResetConfirmnewpassword();

	// Declare Registration and Email objects
	Email e;
	LoginPage l;
	ForgotPassword fp;

	@BeforeMethod
	public void setup() {
		// Initialize the Registration and Email objects before each test
		fp = new ForgotPassword(driver);
		e = new Email(driver);
		l = new LoginPage(driver);

	}

	@Test(priority = 1)
	public void resetPassword() throws InterruptedException {
		// Step 1: ResetPassword

		fp.forgotPasswordClick();
		fp.setConfirmPassword(Email);
		fp.clickProceed();
		driver.get(emailurl);

		driver.get("https://yopmail.com/");

		// Step 2: Enter Email and Go to Inbox
		e.enterEmail(Email);
		e.clickGoToInbox();

		// Step 3: Open GooruAPI Email and Click Verification Link
		e.openVerificationEmail();
		e.clickResetPasswordButton();
		Assert.assertEquals(msg, "Verify successfully");
		// Step 4: Switch to the newly opened tab
		e.switchToNewWindow();

		// Step 5: Wait for authentication success page to load
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement successMsg = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Reset Password.']")));

		//Resetting new password
		fp.setNewPassword(newpassword);
		fp.setConfirmPassword(confirmnewpassword);
		
		fp.clickProceed();
	}
	
	@Test(priority=2,dependsOnMethods="resetPassword")
	
	public void LoginTestResetPassword()
	{
		l.setEmail(Email);
		l.setPassword(newpassword);
		l.clickSigninButton();
		
	}

}
