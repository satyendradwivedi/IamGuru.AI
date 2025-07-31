package com.Gooru.AI.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.Gooru.AI.utilities.ClickActions;
import com.Gooru.AI.utilities.JavaScriptUtils;


public class Registration extends BasePage{
	
	ClickActions ca;
	JavaScriptUtils ju;

	public Registration(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		ca=new ClickActions(driver);
		ju=new JavaScriptUtils(driver);
		
	}
	
	//Identified all locators
	
	@FindBy(xpath="//input[@id='username']")
	@CacheLookup
	private WebElement FirstName;
	
	@FindBy(xpath="//input[@id='lname']")
	@CacheLookup
	private WebElement LastName;
	
	@FindBy(xpath="//input[@id='emailAddress']")
	@CacheLookup
	private WebElement Email;
	
	@FindBy(xpath="//input[@id='regPassword']")
	@CacheLookup
	private WebElement Password;
	
	@FindBy(xpath="//input[@id='confirmPassword']")
	@CacheLookup
	private WebElement  CPassword;
	//drop down
	@FindBy(xpath="")
	@CacheLookup
	private WebElement  Country;
	
	@FindBy(xpath="//input[@id='regphoneNumber']")
	@CacheLookup
	private WebElement  PhoneNumber;
	
	@FindBy(xpath="//button[normalize-space()='Submit']")
	@CacheLookup
	private WebElement  submit;
	
	@FindBy(xpath="//input[@id='agreeTerms']")
	@CacheLookup
	private WebElement  agrterms;
	
	@FindBy(xpath="//*[text()='Verify your email address']")
	@CacheLookup
	private WebElement verify ;
	@FindBy(xpath="//input[@id='dob']")
	@CacheLookup
	private WebElement dob ;
	//Implementing actions on Locators
	
	  // Actions
    public void setFirstName(String firstName) {
        FirstName.clear();
        FirstName.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        LastName.clear();
        LastName.sendKeys(lastName);
    }

    public void setEmail(String email) {
        Email.clear();
        Email.sendKeys(email);
    }

    public void setPassword(String password) {
        Password.clear();
        Password.sendKeys(password);
    }

    public void setConfirmPassword(String confirmPassword) {
        CPassword.clear();
        CPassword.sendKeys(confirmPassword);
    }
    public void setDOB(String dobtext) {
        String script = "arguments[0].value='" + dobtext + "';" +
                        "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));";
        ((JavascriptExecutor) driver).executeScript(script, dob);
    }


    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber.clear();
        PhoneNumber.sendKeys(phoneNumber);
        PhoneNumber.sendKeys(Keys.TAB);
    }
   
    
    public boolean verificationMessage()
    {
    	return verify.isDisplayed();
    	
    }
    public void clickAgreeTerms() {
    	ju.scrollToElement(agrterms);
    	ju.javaScriptClick(agrterms);
    }
    public void clickSubmit() {
    	ju.scrollToElement(submit);
    	ju.javaScriptClick(submit);
		
    	
    	
    }
}
