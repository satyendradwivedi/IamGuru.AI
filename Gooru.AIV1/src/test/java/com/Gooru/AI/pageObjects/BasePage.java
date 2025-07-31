package com.Gooru.AI.pageObjects;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

public WebDriver driver;
	
	public BasePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	// Method to switch to the newly opened tab
	public void switchToNewWindow() {
	    String originalWindow = driver.getWindowHandle();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
	    
	    // Wait until a new window is available
	    wait.until(ExpectedConditions.numberOfWindowsToBe(2));

	    Set<String> allWindows = driver.getWindowHandles();
	    for (String window : allWindows) {
	        if (!window.equals(originalWindow)) {
	            driver.switchTo().window(window);
	            return;
	        }
	    }
	    throw new RuntimeException("No new window detected.");
	}


}
