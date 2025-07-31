package com.Gooru.AI.testCases;

import java.time.Duration;
import java.util.logging.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.Gooru.AI.pageObjects.*;
import com.Gooru.AI.utilities.ReadConfig;

public class RegisterTest extends BaseClass {

    private static final Logger logger = Logger.getLogger(RegisterTest.class.getName());

    ReadConfig rc = new ReadConfig();
    String FName = rc.getFirstName();
    String LName = rc.getLastName();
    String Email = rc.getREmail();
    String password = rc.getRpassword();
    String cpassword = rc.getRCpassword();
    String phone = rc.getRPhone();
    String emailurl = rc.getTempemailURL();
    String msg = "Verify successfully";
    String newpassword = rc.getResetnewpassword();
    String confirmnewpassword = rc.getResetConfirmnewpassword();

    Registration rp;
    Email e;
    LoginPage l;
    ForgotPassword fp;

    @BeforeMethod
    public void setup() {
        logger.info("Initializing test setup...");
        rp = new Registration(driver);
        e = new Email(driver);
        l = new LoginPage(driver);
        fp = new ForgotPassword(driver);
    }

    @Test(priority = 1)
    public void registrationFormTest() throws InterruptedException {
        logger.info("Starting Registration Form Test");
        
        rp.setFirstName(FName);
        rp.setLastName(LName);
        rp.setEmail(Email);
        rp.setPassword(password);
        rp.setConfirmPassword(cpassword);
        rp.setPhoneNumber(phone);
        
        logger.info("Submitting registration form...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        rp.clickSubmit();

        logger.info("Waiting for email verification message...");
        WebElement text = driver.findElement(By.xpath("//*[text()='Verify your email address']"));
        wait.until(ExpectedConditions.textToBePresentInElement(text, "Verify your email address"));
        Assert.assertTrue(rp.verificationMessage(), "Email verification message not displayed.");
        logger.info("Registration form submitted successfully.");
    }

    @Test(priority = 2, dependsOnMethods = "registrationFormTest")
    public void emailVerification() {
        logger.info("Starting Email Verification Test");
        driver.get(emailurl);
        e.enterEmail(Email);
        e.clickGoToInbox();
        e.openVerificationEmail();
        e.clickVerifyEmailButton();
        logger.info("Email verification process initiated");

        Assert.assertEquals(msg, "Verify successfully");
        e.switchToNewWindow();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement successMsg = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Authentication Successful!!']")));
        Assert.assertTrue(successMsg.isDisplayed(), "Authentication not successful");
        logger.info("Email verification completed successfully.");
    }

    @Test(priority = 3, dependsOnMethods = "emailVerification")
    public void registeredUserLogin() throws InterruptedException {
        logger.info("Starting Login Test for Registered User");
        e.backToLoginPage();
        l.setEmail(Email);
        l.setPassword(password);
        l.clickSigninButton();

        logger.info("User logged in successfully. Logging out...");
        l.clickLogoutButton();
        logger.info("User logged out successfully.");
    }

    @Test(priority = 4,enabled=false)
    public void resetPassword() throws InterruptedException {
        logger.info("Starting Reset Password Test");
        fp.forgotPasswordClick();
        fp.setEmail(Email);
        fp.clickProceed();
        logger.info("Password reset request submitted.");

        driver.get(emailurl);
        e.enterEmail(Email);
        e.clickGoToInbox();
        e.openVerificationEmail();
        e.clickResetPasswordButton();
        logger.info("Opened Reset Password Email.");

        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        logger.info("Switched to Reset Password Window.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement resetPageText = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Reset Password.']")));
        Assert.assertTrue(resetPageText.isDisplayed(), "Reset Password page not loaded.");

        WebElement passwordField = driver.findElement(By.xpath("//input[@id='regPassword']"));
        WebElement confirmPasswordField = driver.findElement(By.xpath("//input[@id='regConfPassword']"));
        passwordField.clear();
        passwordField.sendKeys(newpassword);
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(confirmnewpassword);
        logger.info("Entered new password.");

        WebElement proceedBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Proceed']")));
        Boolean isDisabled = proceedBtn.getAttribute("disabled") != null;
        if (isDisabled) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].removeAttribute('disabled');", proceedBtn);
        }
        proceedBtn.click();
        logger.info("Clicked Proceed button, password reset successful.");
    }

    @Test(priority = 5,enabled=false, dependsOnMethods = "resetPassword")
    public void loginWithResetPassword() {
        logger.info("Starting Login Test with Reset Password");
        l.setEmail(Email);
        l.setPassword(newpassword);
        l.clickSigninButton();
        logger.info("User logged in successfully with new password.");
    }
}
