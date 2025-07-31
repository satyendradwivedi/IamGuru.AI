package com.Gooru.AI.pageObjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Gooru.AI.utilities.ClickActions;
import com.Gooru.AI.utilities.JavaScriptUtils;

public class ChatBot extends BasePage {
	private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(20);
	private static final String UPLOAD_PATH = "C:\\Users\\Gursewak\\eclipse-workspace\\Gooru.AI\\resources\\upload files\\";
	
	private final ClickActions ca;
	private final JavaScriptUtils ju;
	private final WebDriverWait wait;

	public ChatBot(WebDriver driver) {
		super(driver);
		this.ca = new ClickActions(driver);
		this.ju = new JavaScriptUtils(driver);
		this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
	}

	@FindBy(xpath = "//button[normalize-space()='Technology']")
	private WebElement category;
	
	@FindBy(xpath = "//button[normalize-space()='ML Algorithms Explained']")
	private WebElement topics1;
	
	@FindBy(xpath = "//i[@class='mdi mdi-send']")
	private WebElement enter;
	
	
	@FindBy(xpath="//button[@type='submit' and contains(@class, 'btn') and contains(@class, 'btn-primary') and contains(@class, 'btn-rounded') and contains(@class, 'px-2')]\r\n"
			+ "")
	private WebElement penter;
	
	
	
	@FindBy(xpath = "//textarea[contains(@class, 'custbot-textarea')]")
	private WebElement shortbio;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submitEnter;
	
	@FindBy(xpath = "//button[@class='close-btn']")
	private WebElement banner;
	
	@FindBy(xpath = "//button[normalize-space()='Chatbot']")
	private WebElement chatbot;
	
	@FindBy(xpath = "//button[normalize-space()='English']")
	private WebElement language;
	
	@FindBy(xpath = "//input[@placeholder='Enter text here']")
	private WebElement textArea;
	
	@FindBy(xpath = "//span[@class='upload-text']")
	private WebElement uploadImage;
	
	@FindBy(xpath = "//button[@class='btn btn-primary btn-rounded']")
	private WebElement saveImage;
	
	@FindBy(xpath = "//input[@placeholder='Enter ShortName']")
	private WebElement platformName;
	
	@FindBy(xpath = "//input[@placeholder='Enter SocialMediaURL']")
	private WebElement platformUrl;
	
	@FindBy(xpath = "//*[contains(text(), 'Yes')]")
	private WebElement yesPopup;
	
	@FindBy(xpath = "//button[normalize-space()='Skip']")
	private WebElement skipBtn;
   
  

	public void profile100Complete(String profileDisplay, String display, String pName, String pUrl, String quality) throws AWTException {
		initializeChatbot();
		setText(profileDisplay);
		setText(display);
		uploadImageAndSubmit("Sage image.jpeg");
		uploadImageAndSubmit("python-programming-language.jpg");
		setPlatformDetails(pName, pUrl);
		setQualityInfo(profileDisplay, quality);
	}

	private void initializeChatbot() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		ju.javaScriptClick(banner);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loader-overlay")));
		
		WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".swal2-confirm")));
		ju.javaScriptClick(confirmBtn);
		
		chatbot.click();
		language.click();
		enter.click();
	}

	@SuppressWarnings("deprecation")
	private void setText(String text) {
    wait.until(ExpectedConditions.elementToBeClickable(textArea));
    ju.javaScriptClick(textArea);
    ju.javaScriptSendKeys(textArea, text);

    // Wait until the text is present in the text area
    wait.until(driver -> textArea.getAttribute("value").equals(text));

    // Wait for the submit button to be enabled and clickable
    wait.until(ExpectedConditions.elementToBeClickable(penter));
    ju.javaScriptClick(penter);
}


	private void uploadImageAndSubmit(String imageName) throws AWTException {
		uploadImage.click();
		
		File file = new File(UPLOAD_PATH + imageName);
		StringSelection selection = new StringSelection(file.getAbsolutePath());
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		
		Robot robot = new Robot();
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		wait.until(ExpectedConditions.elementToBeClickable(saveImage));
		ju.scrollToElement(saveImage);
		ju.javaScriptClick(saveImage);
		
		submitEnter.click();
	}

	private void setPlatformDetails(String pName, String pUrl) {
		platformName.sendKeys(pName);
		platformUrl.sendKeys(pUrl);
		submitEnter.click();
		yesPopup.click();
	}

	private void setQualityInfo(String profileDisplay, String quality) {
		textArea.sendKeys(profileDisplay);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and not(@disabled)]"))).click();
		textArea.sendKeys(quality);
		submitEnter.click();
	}

	public void selectCategory() {
		category.click();
		enter.click();
	}
	
	public void selectTopics() {
		ju.javaScriptClick(topics1);
		ju.javaScriptClick(submitEnter);
	}

	public void learnerBio(String bio) {
		ju.javaScriptSendKeys(shortbio, bio);
		shortbio.sendKeys(Keys.ENTER);
		submitEnter.click();
	}

	public boolean profileComplete() {
		return banner.isDisplayed();
	}
	
	public void clickSkip() {
		wait.until(ExpectedConditions.elementToBeClickable(skipBtn));
		ju.javaScriptClick(skipBtn);
	}

	public boolean isProfileComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	
}



	



