package com.Gooru.AI.pageObjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Gooru.AI.utilities.ClickActions;
import com.Gooru.AI.utilities.JavaScriptUtils;

public class Dashboard extends BasePage {

	ClickActions ca;
	JavaScriptUtils ju;

	public Dashboard(WebDriver driver) {
		super(driver);
		ca = new ClickActions(driver);
		ju = new JavaScriptUtils(driver);
	}

	// Elements
	@FindBy(xpath = "//input[@placeholder=\"What's on your mind?\"]")
	private WebElement post;

	@FindBy(xpath = "//input[@id='postTitle']")
	private WebElement title;

	@FindBy(xpath = "//div[contains(@class, 'ProseMirror') and contains(@class, 'NgxEditor__Content')]")
	private WebElement desc;

	@FindBy(xpath = "//button[@type='button'][normalize-space()='Document']")
	private WebElement document;

	@FindBy(xpath = "//button[@type='button'][normalize-space()='Photo/Video']")
	private WebElement photovideo;

	@FindBy(xpath = "//input[@type='file']")
	private WebElement uploadInput;
	
	@FindBy(xpath = "//input[@id='select_date']")
	private WebElement selectDate;
	
	@FindBy(xpath = "//input[@placeholder='Select date']")
	private WebElement selectDateCal;
	
	@FindBy(xpath = "//span[contains(text(),'›')]")
	private WebElement nextbtn;
	
	//span[contains(text(),'›')]
	
	@FindBy(xpath = "//input[@placeholder='HH']")
	private WebElement hh;
	
	@FindBy(xpath = "//input[@placeholder='MM']")
	private WebElement mm;
	
	@FindBy(xpath = "//button[normalize-space()='Schedule Post']")
	private WebElement schedulePost;
	
	
	
	//input[@placeholder='HH']

	@FindBy(xpath = "//button[normalize-space()='Publish']")
	private WebElement publish;

	// Main method to create a post with single file
	public void clickPostpdf(String titletxt, String desctxt) throws AWTException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		// Click on the post input box
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(post));
		try {
			element.click();
		} catch (org.openqa.selenium.ElementClickInterceptedException ex) {
			System.out.println("Standard click failed, using JavaScript click.");
			ju.javaScriptClick(post);
		}

		// Enter title and description
		title.sendKeys(titletxt);
		desc.sendKeys(desctxt);

		// Wait for and click the 'Document' button
		WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(document));
		try {
			element2.click();
		} catch (org.openqa.selenium.ElementClickInterceptedException ex) {
			System.out.println("Standard click failed, using JavaScript click.");
			ju.javaScriptClick(document);
		}

		// === Upload Files using Robot ===

		File file = new File("C:\\Users\\Gursewak\\eclipse-workspace\\Gooru.AI\\resources\\upload files\\80mb.pdf");
		String filePath = '"' + file.getAbsolutePath() + '"';

		StringSelection selection = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

		Robot robot = new Robot();
		robot.delay(1000);

		// Paste file path
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);

		robot.delay(500);

		// Press Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		// Publish the post using JavaScript click
		ca.clickUsingJavaScript(publish,10);
		robot.delay(4000);

	}
	
	
	public void uploadvideo(String titletxt, String desctxt) throws AWTException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		// Open post modal
		WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(post));
		try {
			element1.click();
		} catch (org.openqa.selenium.ElementClickInterceptedException ex) {
			System.out.println("Standard click failed, using JavaScript click.");
			ju.javaScriptClick(post);
		}

		title.sendKeys(titletxt);
		desc.sendKeys(desctxt);

		// Click Photo/Video
		WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(photovideo));
		try {
			element2.click();
		} catch (org.openqa.selenium.ElementClickInterceptedException ex) {
			ju.javaScriptClick(photovideo);
		}

		// File path
		File file = new File("C:\\Users\\Gursewak\\eclipse-workspace\\Gooru.AI\\resources\\upload files\\Live Streaming 1.mp4");
		String filePath1 = '"' + file.getAbsolutePath() + '"';

		StringSelection selection = new StringSelection(filePath1);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

		// Use Robot to paste file path and press Enter
		Robot robot = new Robot();
		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(20000);

		// ✅ Wait for upload to complete — adjust this based on your UI
		// Example: wait until the progress bar is gone or preview thumbnail is visible
		// Replace below with your actual locator
		//By uploadFinishedIndicator = By.cssSelector(".video-thumbnail"); // <- adjust as needed
		//wait.until(ExpectedConditions.visibilityOfElementLocated(uploadFinishedIndicator));

		// Publish
		ca.clickUsingJavaScript(publish,10);
		Thread.sleep(20000);
	}
	
	public void schedulePost(String titletxt, String desctxt, String date, String ht, String mt) throws Exception {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // Step 1: Open Post Modal
	    try {
	        WebElement postBtn = wait.until(ExpectedConditions.elementToBeClickable(post));
	        postBtn.click();
	    } catch (ElementClickInterceptedException ex) {
	        ju.javaScriptClick(post);
	    }

	    // Step 2: Fill Title and Description
	    wait.until(ExpectedConditions.visibilityOf(title)).sendKeys(titletxt);
	    desc.sendKeys(desctxt);

	    // Step 3: Click "Select Date" Radio Button
	    try {
	        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", selectDate);
	        wait.until(ExpectedConditions.elementToBeClickable(selectDate)).click();
	    } catch (ElementClickInterceptedException e) {
	        ju.javaScriptClick(selectDate);
	    }

	    // Step 4: Handle Date Field - Bypass Popup and Trigger Angular Binding
	    try {
	        wait.until(ExpectedConditions.elementToBeClickable(selectDateCal)).click();
	    } catch (ElementClickInterceptedException e) {
	        ju.javaScriptClick(selectDateCal);
	    }

	    // Set date and dispatch input/change events
	    js.executeScript("arguments[0].value='" + date + "';", selectDateCal);
	    js.executeScript(
	        "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
	        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
	        selectDateCal
	    );
	    selectDateCal.sendKeys(Keys.TAB);

	    // Step 5: Enter Time (HH:MM) and Dispatch Change Events
	    hh.clear();
	    hh.sendKeys(ht);
	    js.executeScript(
	        "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
	        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
	        hh
	    );

	    mm.clear();
	    mm.sendKeys(mt);
	    js.executeScript(
	        "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
	        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
	        mm
	    );
	    mm.sendKeys(Keys.TAB);  // Optional to ensure blur

	    // Step 6: Click Schedule Post
	    wait.until(ExpectedConditions.elementToBeClickable(schedulePost)).click();

	    System.out.println("Scheduled post with title: " + titletxt);
	}
	}
