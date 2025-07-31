package com.Gooru.AI.testCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Gooru.AI.pageObjects.LoginPage;
import com.Gooru.AI.pageObjects.Messaging;

public class MessagingTest extends BaseClass {
	
	private LoginPage lp;

	private Messaging messaging;

	@BeforeMethod
	public void setUp() {
		 lp = new LoginPage(driver);
		messaging = new Messaging(driver);
		
		
	}

	@Test(priority = 1)
	public void testViewAllGurus() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.get(baseUrlLogin);
		lp.setEmail("vishalsingh@yopmail.com");
		lp.setPassword("Test@1234");
		lp.clickSigninButton();
		// Wait for loader to disappear and button to be clickable
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Increased timeout
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader-overlay")));
			Thread.sleep(2000); // Add small delay
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Learner']"))).click();
			driver.findElement(By.xpath("//button[@id='remove-item']")).click();
		} catch (Exception e) {
			Assert.fail("Failed to interact with Learner button: " + e.getMessage());
		}
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Messaging']"))).click();
        // Add wait for element to be clickable before clicking
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(messaging.clickViewAllGuru())).click();
        
        // Add wait for guru names to be present
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".guru-name")));
        messaging.getAllGuruNames();
		List<String> guruNames = messaging.getAllGuruNames();
		Assert.assertFalse(guruNames.isEmpty(), "Guru list should not be empty");
	}

	@Test(priority = 2)
	public void testInnerCircleButton() {
		messaging.InnerCircleButton.click();
		// Add assertions or further actions as needed
	}

	

}
