package com.Gooru.AI.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Gooru.AI.utilities.ClickActions;
import com.Gooru.AI.utilities.JavaScriptUtils;
import java.time.Duration;
import java.util.Set;

public class ForgotPassword extends BasePage {
    
    private ClickActions ca;
    private JavaScriptUtils ju;
    private WebDriverWait wait;
    
    public ForgotPassword(WebDriver driver) {
        super(driver);
        ca = new ClickActions(driver);
        ju = new JavaScriptUtils(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @FindBy(xpath = "//a[normalize-space()='Forgot Password?']")
    private WebElement forgotPassword;
    
    @FindBy(xpath = "//input[@id='loginEmail']")
    private WebElement email;
    
    @FindBy(xpath = "//*[text()='Proceed']")
    private WebElement proceedbtn;
    
    @FindBy(xpath = "//input[@id='regPassword']")
    private WebElement newPassword;
    
    @FindBy(xpath = "//input[@id='regConfPassword']")
    private WebElement cpassword;

    // Click "Forgot Password"
    public void forgotPasswordClick() {
        ju.javaScriptClick(forgotPassword);
    }

    public void setEmail(String uemail) {
        email.clear();
        email.sendKeys(uemail);
    }
    
    public void setNewPassword(String newPass) {
        newPassword.clear();
        ju.javaScriptSendKeys(newPassword, newPass);
    }

    public void setConfirmPassword(String cPass) {
        cpassword.clear();
       ju.javaScriptSendKeys(cpassword, cPass);
    }

    public void clickProceed() {
        proceedbtn.click();
    }
    
    public void switchToResetPasswordWindow() {
        String mainWindow = driver.getWindowHandle();

        WebDriverWait windowWait = new WebDriverWait(driver, Duration.ofSeconds(60));
        boolean windowAppeared = windowWait.until(driver -> driver.getWindowHandles().size() > 1);

        if (!windowAppeared) {
            throw new RuntimeException("New window for password reset did not open.");
        }

        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }
    
    public void waitForResetPasswordPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Reset Password.']")));
    }
    
    public void enableAndClickProceed() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedbtn));
        ju.removeAttribute(proceedbtn, "disabled"); // Ensure it's enabled
        proceedbtn.click();
    }
}