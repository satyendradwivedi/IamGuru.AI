package com.Gooru.AI.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Gooru.AI.utilities.ClickActions;
import com.Gooru.AI.utilities.JavaScriptUtils;

public class Messaging extends BasePage {
	
	private final ClickActions ca;
	private final JavaScriptUtils ju;
	private final WebDriverWait wait;
	private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(30);

	public Messaging(WebDriver driver) {
		super(driver);
		this.ca = new ClickActions(driver);
		this.ju = new JavaScriptUtils(driver);
		this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
	}

	@FindBy(xpath = "//span[normalize-space()='Messaging']")
	public WebElement Messaging;
	
	@FindBy(xpath = "//button[normalize-space()='View all']")
	private WebElement VewAllGuru;
	
	public By clickViewAllGuru() {
        try {
            ca.clickElement(VewAllGuru);
        } catch (Exception e) {
            // Add logging or error handling
            System.out.println("Error clicking View All Guru button: " + e.getMessage());
            throw e;
        }
		return null;
	}
	
	@FindBy(xpath = "//div[@class='col-md-6']")
	private List<WebElement> AllGurulist;
	
	// Method to get all Guru names from the list
	public List<String> getAllGuruNames() {
		List<String> guruNames = AllGurulist.stream()
			.map(guru -> guru.getText().trim())
			.toList();
		System.out.println("Guru Names: " + guruNames);
		return guruNames;
		
	}
	@FindBy(xpath = "//button[@class='btn btn-md d-flex gap-1 btn-primary']")
	public WebElement InnerCircleButton;
	
	
	
	
	
	
	
	
	

}
