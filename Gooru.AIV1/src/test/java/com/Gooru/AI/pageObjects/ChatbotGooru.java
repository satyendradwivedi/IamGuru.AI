package com.Gooru.AI.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Gooru.AI.utilities.ClickActions;
import com.Gooru.AI.utilities.JavaScriptUtils;

public class ChatbotGooru extends BasePage {

	private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(20);

	private final ClickActions ca;
	private final JavaScriptUtils ju;
	private final WebDriverWait wait;

	public ChatbotGooru(WebDriver driver) {
		
		super(driver);
		this.ca = new ClickActions(driver);
		this.ju = new JavaScriptUtils(driver);
		this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
	}

	// Define locators and methods for interacting with the chatbot interface
	
	@FindBy(xpath = "//button[@class='btn btn-primary become-gooru-btn']")
	public WebElement BeaGoorubutton;
	
	@FindBy(xpath = "//input[@placeholder='Enter text here']")
	public WebElement chatInputField;
	public void enterChatInput(String input) {
		try {
			wait.until(ExpectedConditions.visibilityOf(chatInputField));
			chatInputField.clear();
			chatInputField.sendKeys(input);
		} catch (Exception e) {
			System.out.println("Error entering chat input: " + e.getMessage());
			throw e;
		}
	}
	
	
	@FindBy(xpath = "//i[@class='mdi mdi-send']")
	public WebElement sendButton;
	
	public void clickSendButton() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(sendButton));
			ca.clickElement(sendButton);
		} catch (Exception e) {
			System.out.println("Error clicking send button: " + e.getMessage());
			throw e;
		}
	}
	
	
	
	public void clickBeaGooruButton() {
		try {
			ca.clickElement(BeaGoorubutton);
		} catch (Exception e) {
			System.out.println("Error clicking Bea Gooru button: " + e.getMessage());
			throw e;
		}
	}
	
	@FindBy(xpath = "//button[normalize-space()='Yes']")
	private WebElement yesButton;
	
	public void clickYesButton() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(yesButton));
			ca.clickElement(yesButton);
		} catch (Exception e) {
			System.out.println("Error clicking Yes button: " + e.getMessage());
			throw e;
		}
	}
	
	@FindBy(xpath = "//button[normalize-space()='Accept']")
	public WebElement AcceptButton;
	
	public void clickAcceptButton() {
		ju.javaScriptClick(AcceptButton);
	}
	
	@FindBy(xpath = "//span[normalize-space()='Course Management']")
	private WebElement courseManagementTbutton;
	
	// Assert that the Course Management button is displayed
	public boolean assertCourseManagementButtonDisplayed() {
		try {
			wait.until(ExpectedConditions.visibilityOf(courseManagementTbutton));
			return courseManagementTbutton.isDisplayed();
		} catch (Exception e) {
			System.out.println("Error asserting Course Management button visibility: " + e.getMessage());
			return false;
		}
	}



	
	
	
}