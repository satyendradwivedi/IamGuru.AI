package com.Gooru.AI.testCases;
import java.awt.AWTException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.Gooru.AI.pageObjects.ChatBot;
import com.Gooru.AI.pageObjects.ChatbotGooru;
import com.Gooru.AI.pageObjects.Content;
import com.Gooru.AI.pageObjects.CourseManagement;
import com.Gooru.AI.pageObjects.Dashboard;
import com.Gooru.AI.pageObjects.Email;
import com.Gooru.AI.pageObjects.Explore;
import com.Gooru.AI.pageObjects.ForgotPassword;
import com.Gooru.AI.pageObjects.LoginPage;
import com.Gooru.AI.pageObjects.Registration;
import com.Gooru.AI.pageObjects.TimeZone;
import com.Gooru.AI.utilities.ReadConfig;
import com.Gooru.AI.utilities.TestDataGenerator;
import com.Gooru.AI.testCases.*;

public class Regression extends BaseClass {
	
	private ForgotPassword fp;
	private LoginPage lp;
	private Content ct;
	private Dashboard db;
	private ChatBot cb;
	private TimeZone tz;
	private Explore exp;
	private Registration rp;
	private Email e;
	private ChatbotGooru cg;
	private CourseManagement cm;

	private ReadConfig rc = new ReadConfig();
	private String emailurl = rc.getTempemailURL();
	private String title = rc.getTile();
	private String description = rc.getDescription();
	private String date = rc.getDate();
	private String hour = rc.getHour();
	private String minute = rc.getMinute();
	
	private String FName = TestDataGenerator.generateFirstName();
	private String LName = TestDataGenerator.generateLastName();
	private String Email = TestDataGenerator.generateEmail();
	private String password = TestDataGenerator.generatePassword();
	private String phone = TestDataGenerator.generatePhoneNumber();
	private String dob = TestDataGenerator.generateDOB();
	private String bio = TestDataGenerator.generateLearnerBio();
	private String platformName = TestDataGenerator.getPlatformName();
	private String platformUrl = TestDataGenerator.getPlatformUrl();
	private String quality = TestDataGenerator.getQuality();
	
	//Gooru Data Store
	private String generation = rc.genetation();
	private String gooruTitle = rc.goorutitle();
	//Course Management Data
	private String courseTitle = rc.getCourseTitle();
	private String courseDescription = rc.getCourseDescription();

	@BeforeMethod
	public void setUp() {
		lp = new LoginPage(driver);
		ct = new Content(driver);
		db = new Dashboard(driver);
		rp = new Registration(driver);
		e = new Email(driver);
		fp = new ForgotPassword(driver);
		cb = new ChatBot(driver);
		tz = new TimeZone(driver);
		exp = new Explore(driver);
		cg = new ChatbotGooru(driver);
		cm= new CourseManagement(driver);
	}

	@Test(priority = 1, enabled = true)
	public void registrationFormTest() {
		logger.info("Starting Registration Form Test");

		rp.setFirstName(FName);
		rp.setLastName(LName);
		rp.setEmail(Email);
		rp.setPassword(password);
		rp.setConfirmPassword(password);
		rp.setPhoneNumber(phone);
		rp.setDOB(dob);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='checkbox']")));
		rp.clickAgreeTerms();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Submit']")));
		rp.clickSubmit();

		Assert.assertTrue(rp.verificationMessage(), "Email verification message not displayed.");
		logger.info("Registration completed successfully.");
	}

	@Test(priority = 2, enabled = true, dependsOnMethods = "registrationFormTest")
	public void emailVerification() {
		logger.info("Starting Email Verification Test");

		driver.get(emailurl);
		e.enterEmail(Email);
		e.clickGoToInbox();
		e.openVerificationEmail();
		e.clickVerifyEmailButton();
		e.switchToNewWindow();

		WebElement successMsg = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Authentication Successful!!']")));
		Assert.assertTrue(successMsg.isDisplayed(), "Authentication not successful");
		logger.info("Email verification completed.");
	}

	@Test(priority = 3, enabled = true, dependsOnMethods = "emailVerification")
	public void registeredUserLogin() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		logger.info("Starting Login Test for Registered User");

		e.backToLoginPage();
		lp.setEmail(Email);
		logger.info("Email is:"+Email);
		lp.setPassword(password);
		logger.info("Password is:"+password);
		lp.clickSigninButton();
		
		tz.timeZoneConfirm();
		logger.info("User logged in successfully.");
	}

	@Test(priority = 4, enabled = true, dependsOnMethods = "registeredUserLogin")
	public void profileCompleteMandatory() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		logger.info("Completing mandatory profile fields");
		cb.selectCategory();
		cb.learnerBio(bio);
		cb.selectTopics();
	}
	
	

	@Test(priority = 5, enabled = false)
	public void profileComplete100() throws AWTException, InterruptedException {
		logger.info("Completing full profile to 100%");
		cb.profile100Complete(FName, FName, platformName, platformUrl, quality);
	}

	@Test(priority = 6, enabled = false)
	public void createPostWithPdfUpload() throws AWTException, InterruptedException {
		logger.info("Creating post with PDF upload");
		
		db.clickPostpdf(title, description);
		// TODO: Add proper validation
	}

	@Test(priority = 7, enabled = false)
	public void createPostWithMedia() throws AWTException, InterruptedException {
		logger.info("Creating post with media upload");
		driver.get(baseUrlLogin);
		lp.setEmail(Email);
		lp.setPassword(password);
		lp.clickSigninButton();
		wait.until(ExpectedConditions.urlContains("dashboard"));
		db.uploadvideo(title, description);
		// TODO: Add proper validation
	}

	@Test(priority = 8, enabled = false)
	public void schedulePost() throws Exception {
		driver.get(baseUrlLogin);
		lp.setEmail(Email);
		lp.setPassword(password);
		lp.clickSigninButton();
		wait.until(ExpectedConditions.urlContains("dashboard"));
		logger.info("Scheduling a post");
		
		db.schedulePost(title, description, date, hour, minute);
		// TODO: Add proper validation
	}
@Test(priority = 9, enabled = false)
	
	public void searchCousreTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.get(baseUrlLogin);
		lp.setEmail(Email);
		lp.setPassword(password);
		lp.clickSigninButton();
		wait.until(ExpectedConditions.urlContains("dashboard"));
		exp.exploreClick();
		logger.info("Course search test started");
		exp.searchCourse("Java"); 
        //Assert.assertTrue(exp.isSearchBoxVisible(), "Search box is not visible");
		
		logger.info("Course search test completed successfully");
		//exp.enrollCourse();
		//Assert.assertTrue(exp.isEnrollmentSuccessful(), "Enrollment failed or not verified");
		// Ensure this doesn't contain assertion internally
		
		
		
	}
	
	@Test(priority = 10, enabled = false)
	
	public void addToCartTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.get(baseUrlLogin);
		lp.setEmail(Email);
		lp.setPassword(password);
		lp.clickSigninButton();
		wait.until(ExpectedConditions.urlContains("dashboard"));
		exp.exploreClick();
		logger.info("Starting add to cart test");
		
		exp.addToWishList();  // Ensure this doesn't contain assertion internally
		Assert.assertTrue(exp.isEnrollmentSuccessful(), "Wishlist button is not visible");
		
		

		logger.info("Add to cart test completed successfully");
	}
	@Test(priority = 11, enabled = false)
	public void exploreAccessTest() // Test to access Explore page and enroll in a course
			throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.get(baseUrlLogin);
		lp.setEmail(Email);
		lp.setPassword(password);
		lp.clickSigninButton();
		wait.until(ExpectedConditions.urlContains("dashboard"));
		logger.info("Starting explore test");
		 // Click Explore and assert navigation
	   
	    exp.exploreClick();
	    Assert.assertTrue(exp.isExploreButtonVisible(), "Explore button is not visible");
	    //Assert.assertTrue(exp.getCurrentUrl().contains("explore"), "Explore page did not open");

	    // Enroll and assert result
	    exp.enrollCourse();  // Ensure this doesn't contain assertion internally
	    //Assert.assertTrue(exp.isEnrollmentSuccessful(), "Enrollment failed or not verified");

	    logger.info("Explore test completed successfully");
	    
	}
	
	@Test(priority = 12, enabled = false)
	
	public void payMentTest()
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		exp.paymentLogin("johndepp1900@personal.example.com", "Hanish@1205");
		exp.continueWithReviewOrder();
		Assert.assertTrue(exp.isPaymentSuccessful(), "Payment was not successful or not verified");
	}
	
	@Test(priority = 13, enabled = false)
	
	public void rateCourseTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.get(baseUrlLogin);
		lp.setEmail(Email);
		lp.setPassword(password);
		lp.clickSigninButton();
		wait.until(ExpectedConditions.urlContains("dashboard"));
		exp.exploreClick();
		logger.info("Starting course rating test");
		
		exp.rateCourse();
		Assert.assertTrue(exp.isRatingsListVisible(), "Rating failed or not verified");
		
		logger.info("Course rating test completed successfully");
	

		Assert.assertTrue(exp.isRatingsListVisible(), "Rating failed or not verified");
		
		logger.info("Course rating test completed successfully");
	}
	
	// Test to become a Guru
	@Test(priority= 14, enabled = true)
	public void becomeGuruTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		driver.get(baseUrlLogin);
		lp.setEmail(Email);
		lp.setPassword(password);
		lp.clickSigninButton();
		wait.until(ExpectedConditions.urlContains("dashboard"));
		logger.info("Starting Become a Guru test");
		
        // Add longer wait time and additional checks for loading issues
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(180)); // Increase timeout to 3 minutes
        longWait.until(ExpectedConditions.visibilityOf(cg.BeaGoorubutton));
        longWait.until(ExpectedConditions.elementToBeClickable(cg.BeaGoorubutton));
        try {
            // Add explicit wait for page load state
            new WebDriverWait(driver, Duration.ofSeconds(60))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
            
            Thread.sleep(5000); // Increase delay to 5 seconds
            cg.clickBeaGooruButton();
        } catch (ElementClickInterceptedException e) {
            // Retry click with increased wait time
            longWait.until(ExpectedConditions.elementToBeClickable(cg.BeaGoorubutton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cg.BeaGoorubutton);
        }
        
        wait.until(ExpectedConditions.elementToBeClickable(cg.chatInputField));
        cg.enterChatInput(generation);
        
        wait.until(ExpectedConditions.elementToBeClickable(cg.sendButton));
        cg.clickSendButton();
        
        // Add retry mechanism and explicit waits for chat input
        int maxRetries = 3;
        for(int i = 0; i < maxRetries; i++) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(cg.chatInputField));
                cg.enterChatInput(gooruTitle);
                break;
            } catch(Exception e) {
                if(i == maxRetries-1) throw e;
                Thread.sleep(1000);
            }
        }
        
        wait.until(ExpectedConditions.elementToBeClickable(cg.sendButton));
        cg.clickSendButton();
        
        // Add retry mechanism for second chat input
        for(int i = 0; i < maxRetries; i++) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(cg.chatInputField)); 
                cg.enterChatInput(gooruTitle);
                break;
            } catch(Exception e) {
                if(i == maxRetries-1) throw e;
                Thread.sleep(1000);
            }
        }
        
        wait.until(ExpectedConditions.elementToBeClickable(cg.sendButton));
        cg.clickSendButton();
        
     
        cg.clickYesButton();
        
        // Add additional wait to ensure element is fully loaded and clickable
       cg.clickAcceptButton();
		
		Assert.assertTrue(cg.assertCourseManagementButtonDisplayed(), "CourseButton is not visible");
		
		logger.info("Become a Guru test completed successfully");
	}
	
	// Test to create a course
	@Test(priority = 15, enabled = true)
	public void validateGooruisabletoaccesscoursemanagement() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.get(baseUrlLogin);
		lp.setEmail(Email);
		lp.setPassword(password);
		lp.clickSigninButton();
		wait.until(ExpectedConditions.urlContains("dashboard"));
		
		logger.info("Starting Create Course test");
		
		cm.clickCourseManagementMenu();
		Assert.assertTrue(cm.isCourseManagementMenuVisible(), "Course Management menu is not visible");
		
		logger.info("Create Course test completed successfully");
	}
	
	@Test(priority = 16, enabled = true)
	public void validategooruisabletoaccessCreateCousrescreen() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		logger.info("Starting Create Course test");
		
		cm.clickCreateCourseButton();
		Assert.assertEquals(driver.getTitle(), "GOORU", "Create Course page title is incorrect");
		
		logger.info("Create Course test completed successfully");
	}
	@Test(priority = 17, enabled = true)
	public void validateGooruisabletoentercoursetitle() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		logger.info("Starting Course Title Entry test");
		
		cm.enterCourseTitle(courseTitle);
		Assert.assertTrue(cm.isCourseTitleEntered(), "Course title was not entered successfully");
		
		logger.info("Course Title Entry test completed successfully");
	}
	@Test(priority = 18, enabled = true)
	public void validateGooruisabletoentercoursedescription() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		logger.info("Starting Course Description Entry test");
		
		cm.enterCourseDescription(courseDescription);
		Assert.assertTrue(cm.isCourseDescriptionEntered(), "Course description was not entered successfully");
		
		logger.info("Course Description Entry test completed successfully");
	}
	@Test(priority = 19, enabled = true)
	public void validateGooruisabletoselectCategory() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		logger.info("Starting Course Category Selection test");
		cm.selectCourseCategory();
		Assert.assertTrue(cm.isCourseCategorySelected(), "Course category was not selected successfully");
		
		//Assert.assertTrue(cm.isCourseImageUploaded(), "Course image was not uploaded successfully");
		
		logger.info("Course Category Selection test completed successfully");
	}	
	
	@Test(priority = 20, enabled = true)
	public void validateGooruisabletoselectsubcategory() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		logger.info("Starting Course Subcategory Selection test");
		
		cm.selectCourseSubCategory();
		Assert.assertTrue(cm.isCourseSubCategorySelected(), "Course subcategory was not selected successfully");
		
		logger.info("Course Subcategory Selection test completed successfully");
	}
	
}
