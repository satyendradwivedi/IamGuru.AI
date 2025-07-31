package com.Gooru.AI.pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Gooru.AI.utilities.ClickActions;
import com.Gooru.AI.utilities.JavaScriptUtils;

public class Email extends BasePage {
    ClickActions ca;
    JavaScriptUtils ju;
    JavascriptExecutor je;
 
    public Email(WebDriver driver) {
        super(driver);
        ca = new ClickActions(driver);
    }

    @FindBy(xpath = "//input[@id='login']")
    private WebElement emailInput;

    @FindBy(xpath = "//i[@class='material-icons-outlined f36']")
    private WebElement goToInboxButton;

    @FindBy(xpath = "//span[contains(text(),'IamGuru')]")
    private WebElement gooruAPIMail;
  

    @FindBy(xpath = "//a[normalize-space()='Verify Your Email']")
    private WebElement verifyEmailButton;
    
    @FindBy(xpath="//*[@id=\"mail\"]/div/div/div[2]/a")
    private WebElement ResetPassword;
    
    @FindBy(xpath = "//*[text()='Back to Login']")
    private WebElement BacktoLogin;
    /**
     * Enters the temporary email into Yopmail input field.
     */
    public void enterEmail(String yopmailAddress) {
    	emailInput.clear();
        emailInput.sendKeys(yopmailAddress);
    }

    /**
     * Clicks the button to navigate to inbox.
     */
    public void clickGoToInbox() {
        ca.clickElement(goToInboxButton);
    }

    /**
     * Tries to open the verification email, handling potential delays.
     */
    public void openVerificationEmail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

        // 🚀 Switch to the inbox iframe before locating emails
        driver.switchTo().frame("ifinbox");

        for (int i = 0; i < 3; i++) { // Retry 3 times
            try {
                wait.until(ExpectedConditions.elementToBeClickable(gooruAPIMail)).click();
                driver.switchTo().defaultContent();  // Reset back to main page
                return;
            } catch (Exception e) {
                System.out.println("Retry fetching email attempt: " + (i + 1));
                driver.navigate().refresh(); // Refresh inbox
                try {
                    Thread.sleep(5000); // Wait 5 seconds before retrying
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        throw new RuntimeException("Email not received after 3 retries.");
    }

    /**
     * Clicks the "Verify Your Email" button inside the email.
     */
    public void clickVerifyEmailButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // 🚀 Switch to the email content iframe
        driver.switchTo().frame("ifmail");

        wait.until(ExpectedConditions.elementToBeClickable(verifyEmailButton)).click();

        // 🚀 Switch back to main page
        driver.switchTo().defaultContent();
    }
    public void clickResetPasswordButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));

        // 🚀 Switch to the email content iframe
        driver.switchTo().frame("ifmail");

        wait.until(ExpectedConditions.elementToBeClickable(ResetPassword)).click();

        // 🚀 Switch back to main page
        driver.switchTo().defaultContent();
    }
    
    public void verificationsuccess(String msg)
    
    {
    	System.out.println(msg);
    	
    }
    
    public void backToLoginPage()
    {
    	ca.clickElement(BacktoLogin);
    }
}
