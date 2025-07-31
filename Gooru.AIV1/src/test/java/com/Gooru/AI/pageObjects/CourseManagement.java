package com.Gooru.AI.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Gooru.AI.utilities.ClickActions;
import com.Gooru.AI.utilities.JavaScriptUtils;

public class CourseManagement extends BasePage {
	
	private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(20);

	private final ClickActions ca;
	private final JavaScriptUtils ju;
	private final WebDriverWait wait;

	// Define locators and methods for interacting with the course management interface

	public CourseManagement(WebDriver driver) {
		super(driver);
		// Initialize any additional csuper(driver);
		this.ca = new ClickActions(driver);
		this.ju = new JavaScriptUtils(driver);
		this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
	}
	@FindBy(xpath = "//span[normalize-space()='Course Management']")
	private WebElement courseManagementMenu;
	
	@FindBy(xpath = "//div[@class='loader-overlay']")
	private WebElement loaderOverlay;
	
	public void clickCourseManagementMenu() {
		try {
			waitForLoaderToDisappear();
			wait.until(ExpectedConditions.elementToBeClickable(courseManagementMenu));
			ca.clickElement(courseManagementMenu);
		} catch (Exception e) {
			ju.javaScriptClick(courseManagementMenu);
		}
	}
	
	private void waitForLoaderToDisappear() {
		try {
			// Wait for loader to be invisible (max 10 seconds)
			WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));
			shortWait.until(ExpectedConditions.invisibilityOf(loaderOverlay));
		} catch (Exception e) {
			// Loader might not be present, continue
			System.out.println("Loader not found or already disappeared");
		}
	}
	
	@FindBy(xpath = "//button[normalize-space()='Add New Course']")
	private WebElement createCourseButton;
	
	public void clickCreateCourseButton() {
		try {
			waitForLoaderToDisappear();
			wait.until(ExpectedConditions.elementToBeClickable(createCourseButton));
			ca.clickElement(createCourseButton);
		} catch (Exception e) {
			ca.clickElement(createCourseButton);
		}
	}
	
	
	@FindBy(xpath = "//input[@placeholder='Enter Title']")
	private WebElement courseTitleInput;
	
	public void enterCourseTitle(String title) {
		try {
			wait.until(ExpectedConditions.visibilityOf(courseTitleInput));
			courseTitleInput.clear();
			courseTitleInput.sendKeys(title);
		} catch (Exception e) {
			System.out.println("Error entering course title: " + e.getMessage());
			throw e;
		}
	}
	@FindBy(xpath = "//*[@id=\"layout-wrapper\"]/div/div[1]/app-create-new-course/div[2]/form/app-speech-to-text/div/div/ngx-editor/div/div/p")
	private WebElement courseDescriptionInput;
	
	public void enterCourseDescription(String description) {
		try {
			wait.until(ExpectedConditions.visibilityOf(courseDescriptionInput));
			ju.scrollToElement(courseDescriptionInput);
			courseDescriptionInput.clear();
			courseDescriptionInput.sendKeys(description);
		} catch (Exception e) {
			System.out.println("Error entering course description: " + e.getMessage());
			throw e;
		}
	}
	
	@FindBy(xpath = "//button[@role='option']/div[1]")
	private WebElement courseCategoryInput;
	
	public void selectCourseCategory() {
		try {
			waitForLoaderToDisappear();
			wait.until(ExpectedConditions.visibilityOf(courseCategoryInput));
			ju.scrollToElement(courseCategoryInput);
			courseCategoryInput.click();
		} catch (Exception e) {
			System.out.println("Error selecting course category: " + e.getMessage());
			throw e;
		}
	}
	@FindBy(xpath = "//div[normalize-space()='AI & Machine Learning']")
	private WebElement courseCategorysubInput;
	
	public void selectCourseSubCategory() {
		try {
			waitForLoaderToDisappear();
			wait.until(ExpectedConditions.visibilityOf(courseCategorysubInput));
			ju.scrollToElement(courseCategorysubInput);
			courseCategorysubInput.click();
		} catch (Exception e) {
			System.out.println("Error selecting course sub-category: " + e.getMessage());
			throw e;
		}
	}
	@FindBy(xpath = "//button[normalize-space()='Save & Continue']")
	private WebElement saveAndContinueButton;
	
	public void clickSaveAndContinueButton() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(saveAndContinueButton));
			ca.clickElement(saveAndContinueButton);
		} catch (Exception e) {
			System.out.println("Error clicking Save & Continue button: " + e.getMessage());
			throw e;
		}
	}

	public boolean isCourseManagementMenuVisible() {
		// TODO Auto-generated method stub
		try {
			wait.until(ExpectedConditions.visibilityOf(courseManagementMenu));
			return courseManagementMenu.isDisplayed();
		} catch (Exception e) {
			System.out.println("Course Management menu is not visible: " + e.getMessage());
		}
		return false;
	}

	public boolean isCourseTitleEntered() {
		// TODO Auto-generated method stub
		try {
			wait.until(ExpectedConditions.visibilityOf(courseTitleInput));
			return !courseTitleInput.getAttribute("value").isEmpty();
		} catch (Exception e) {
			System.out.println("Course title input is not visible or empty: " + e.getMessage());
		}
		return false;
	}

	public boolean isCourseDescriptionEntered() {
		// TODO Auto-generated method stub
		try {
			wait.until(ExpectedConditions.visibilityOf(courseDescriptionInput));
			return !courseDescriptionInput.getText().isEmpty();
		} catch (Exception e) {
			System.out.println("Course description input is not visible or empty: " + e.getMessage());
		}
		return false;
	}

	public boolean isCourseCategorySelected() {
		// TODO Auto-generated method stub
		try {
			wait.until(ExpectedConditions.visibilityOf(courseCategoryInput));
			return courseCategoryInput.getText() != null && !courseCategoryInput.getText().isEmpty();
		} catch (Exception e) {
			System.out.println("Course category input is not visible or empty: " + e.getMessage());
		}
		return false;
	}

	public boolean isCourseSubCategorySelected() {
		// TODO Auto-generated method stub
		try {
			wait.until(ExpectedConditions.visibilityOf(courseCategorysubInput));
			return courseCategorysubInput.getText() != null && !courseCategorysubInput.getText().isEmpty();
		} catch (Exception e) {
			System.out.println("Course sub-category input is not visible or empty: " + e.getMessage());
		}
		return false;
	}
	
	
	// Additional methods for managing courses can be added here

}