package com.Gooru.AI.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Gooru.AI.utilities.ClickActions;
import com.Gooru.AI.utilities.JavaScriptUtils;

// Page Object class for Explore functionality
public class Explore extends BasePage{
	
	ClickActions ca;
	JavaScriptUtils ju;

	public Explore(WebDriver driver) {
		super(driver);
		ca = new ClickActions(driver);
		ju=new JavaScriptUtils(driver);

	}

	// Web elements locators using @FindBy
	@FindBy(xpath = "//span[normalize-space()='Explore']")
	private WebElement exploreButton;
	
	@FindBy(xpath = "//div[@class='row course-grid']//div[1]//div[1]//div[2]//div[3]//button[2]")
	private WebElement enrollbutton;
	
	@FindBy(xpath = "//button[@class='btn btn-primary']")
	private WebElement coursePreviewButton;
	
	@FindBy(xpath = "//button[@class='btn btn-success']")
	private WebElement EnrollforFreeButton;
	
	@FindBy(xpath = "//*[@id=\"layout-wrapper\"]/div/div[1]/app-explore-courses/div/div[2]/section/div/div[1]/div/div[2]/div[3]/button[2]")
	private WebElement EnrollButton;
	
	@FindBy(xpath = "//button[normalize-space()='Proceed to Cart']")
	private WebElement ProceedtoCartButton;
	
	@FindBy(xpath = "//*[@id=\"layout-wrapper\"]/div/div[1]/app-cart-page/div/div[2]/div[2]/div/button")
	private WebElement Paybutton;
	
	
	@FindBy(xpath = "//div[@class='row']//div[2]//div[1]//div[2]//div[3]//button[1]")
	private WebElement WishListButton;
	
	@FindBy(xpath = "//input[@placeholder='Search courses...']")
	private WebElement ExploreSearchBox;
	
	@FindBy(xpath = "//button[@class='btn btn-primary']")
	private WebElement searchButton;
	
	@FindBy(xpath = "//button[@class='btn btn-primary']")
	private List<WebElement> SearchedCourseList;
	
	@FindBy(xpath = "//button[normalize-space()='Ratings']")
	private WebElement Ratings;
	
	@FindBy(xpath = "//*[@id=\"collapseRatings\"]/div/ul/li")
	private List<WebElement> RatingsList;
	
	@FindBy(xpath = "//input[@id='rating-4']")
	private WebElement rate4;
	
	@FindBy(xpath = "//span[text()='4.5']")
	private List<WebElement> CourseRatingsList;
	
	
	@FindBy(xpath = "//*[@id=\"email\"]")
	private WebElement PaymentEmail;
	
	@FindBy(xpath = "//*[@id=\"password\"]")
	private WebElement emailPaymentPassword;
	
	@FindBy(xpath = "//button[@id='btnLogin']")
	private WebElement Paymentloginclick;
	
	@FindBy(xpath = "//*[contains(text(),'Payment Successful!')]")
	private WebElement Paymentsucessfullmessage;
	
	// Method to verify successful payment message
	public boolean isPaymentSuccessful() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			wait.until(ExpectedConditions.visibilityOf(Paymentsucessfullmessage));
			return Paymentsucessfullmessage.isDisplayed();
		} catch(Exception e) {
			return false;
		}
	}
	
	@FindBy(xpath = "//*[@id=\"hermione-container\"]/div[1]/main/div[3]/div[2]/button")
	private WebElement ContinuewithReviewOrderButton;
	
	// Method to handle payment login
	public void paymentLogin(String email, String password) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		PaymentEmail.clear(); // Clear the email field before entering new value
		PaymentEmail.sendKeys(email);
		emailPaymentPassword.sendKeys(password);
		// Try multiple click methods with retries
		int maxRetries = 3;
		for(int i=0; i<maxRetries; i++) {
			try {
				// Try regular click first
				Paymentloginclick.click();
				break;
			} catch(Exception e) {
				try {
					// Try JavaScript click
					ju.javaScriptClick(Paymentloginclick); 
					break;
				} catch(Exception e2) {
					// Wait and retry if both clicks fail
					try {
						Thread.sleep(1000);
					} catch(InterruptedException ie) {}
					if(i == maxRetries-1) {
						throw new RuntimeException("Unable to click payment login button after " + maxRetries + " retries");
					}
				}
			}
		}
	}
	
	// Method to continue with review order
	public void continueWithReviewOrder() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(ContinuewithReviewOrderButton));
		ju.javaScriptClick(ContinuewithReviewOrderButton);
	}
	
	
	
	
	// Method to search and enroll in a course
	public void searchCourse(String courseName) {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
			// Wait for the search box to be visible and retry if not found
			int retries = 0;
			while(retries < 3) {
				try {
					// Added wait before attempting to find element
					Thread.sleep(2000);
					// Updated xpath to include full path and handle dynamic loading
					ExploreSearchBox = driver.findElement(By.xpath("//input[@placeholder='Search courses...']"));
					ju.javaScriptClick(ExploreSearchBox);
					break;
				} catch(Exception e) {
					retries++;
					Thread.sleep(1000);
				}
			}
			
			// Enter the course name in the search box
			ExploreSearchBox.sendKeys(courseName);
			
			// Click search with retries
			retries = 0; 
			while(retries < 3) {
				try {
					ju.javaScriptClick(searchButton);
					break;
				} catch(Exception e) {
					retries++;
					Thread.sleep(1000);
				}
			}
			
			// Wait for search results
			Thread.sleep(20000);
			
			// Print all searched courses
			System.out.println("Found courses:");
			for(WebElement course : SearchedCourseList) {
				System.out.println(course.getText());
			}
			
			// Enroll in first course if available
			if(!SearchedCourseList.isEmpty()) {
				ju.javaScriptClick(SearchedCourseList.get(0));
				enrollCourse();
			}
		} catch(Exception e) {
			System.out.println("Error searching course: " + e.getMessage());
		}
	}

	// Method to add course to wishlist
	public void addToWishList() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60)); // t implicit wait for elements to loadto loaddto load
		// Add explicit wait for element to be clickable to avoid ElementClickInterceptedException
		ju.javaScriptClick(WishListButton);
	}
	
	// Method to check if search box is visible
	public boolean isSearchBoxVisible() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		// Check if the search box is displayed
		return ExploreSearchBox.isDisplayed();
	}
	
	// Method to click explore button
	public void exploreClick()
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		// Try JavaScript click if regular click fails
		try {
			exploreButton.click();
		} catch (Exception e) {
			ju.javaScriptClick(exploreButton);
		}
		// Add explicit wait for element to be clickable to avoid ElementClickInterceptedException
	}

	// Method to enroll in a course
	public void enrollCourse()
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		
		// Add explicit wait for EnrollButton with increased timeout
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		try {
			// Wait for element to be present first
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='row course-grid ng-star-inserted']//div[1]//div[1]//div[2]//div[3]//button[2]")));
			
			// Then wait for it to be clickable
			//wait.until(ExpectedConditions.elementToBeClickable(EnrollButton));
			
			// Scroll into view
			ju.scrollToElement(EnrollButton);
			
			// Add longer delay
			Thread.sleep(3000);
			
			// Try regular click first, fallback to JavaScript click
			try {
				EnrollButton.click();
			} catch (Exception e) {
				ju.javaScriptClick(EnrollButton);
			}
			
		} catch (Exception e) {
			System.out.println("EnrollButton not found: " + e.getMessage());
			e.printStackTrace();
		}
		
		try {
			// Add wait for ProceedToCart button with retry
			int retries = 0;
			while(retries < 3) {
				try {
					wait.until(ExpectedConditions.elementToBeClickable(ProceedtoCartButton));
					ju.javaScriptClick(ProceedtoCartButton);
					break;
				} catch(Exception e) {
					retries++;
					Thread.sleep(2000);
				}
			}
		} catch (Exception e) {
			System.out.println("ProceedToCart button not found: " + e.getMessage());
			e.printStackTrace();
		}
		ju.javaScriptClick(Paybutton);
	}
	
	// Method to rate a course
	public void rateCourse() {
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    ju.javaScriptClick(Ratings);

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='collapseRatings']/div/ul/li")));

	    for (WebElement rate : RatingsList) {
	    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	        
	        System.out.println("Rate: " +rate.getText());
	        // Use the rate4 WebElement that's already defined instead of finding it again
	        if (rate4.isDisplayed()) {
	            ju.scrollToElement(rate4);
	            ju.javaScriptClick(rate4);
	            break;
	        }
	    }
	    
	    // Assert that rating 4 is selected
	    for (WebElement filteredrating : CourseRatingsList) {
	        System.out.println("Course Rating: " + filteredrating.getText());
	        assert filteredrating.getText().equals("4.5") : "Expected rating 4.5 not found";
	    }
	   
	}

	// Method to check if ratings list is visible
	public boolean isRatingsListVisible() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		// Check if the Ratings button is displayed
		
		    return !RatingsList.isEmpty() && RatingsList.get(0).isDisplayed(); // ✅ Correct usage
		}

	// Method to check if wishlist button is visible
	public boolean isWishListButtonVisible() {
	    return WishListButton.isDisplayed();
	}
	
	// Method to check if explore button is visible
	public boolean isExploreButtonVisible() {
	    return exploreButton.isDisplayed();
	}

	// Method to get current URL
	public String getCurrentUrl() {
	    return driver.getCurrentUrl();
	}

	// Method to check if enrollment was successful
	public boolean isEnrollmentSuccessful() {
		// TODO Auto-generated method stub
		return coursePreviewButton.isDisplayed();
	}

	public boolean isNoCourseFoundVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	
		
}
