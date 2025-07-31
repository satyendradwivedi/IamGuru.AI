package com.Gooru.AI.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import com.Gooru.AI.utilities.ClickActions;
import com.Gooru.AI.utilities.JavaScriptUtils;

public class Content extends BasePage {

	ClickActions ca;
	JavaScriptUtils ju;
	public Content(WebDriver driver) {
		super(driver);
		
		ca = new ClickActions(driver);
		ju=new JavaScriptUtils(driver);
	}
	@FindBy(xpath = "//button[@class='btn btn-primary become-learner-btn']")
	@CacheLookup
	private WebElement btnSwitchtoLearner;
	
	@FindBy(xpath = "//button[normalize-space()='Create New Content']")
	@CacheLookup
	private WebElement CreateNewContent;
     
	public boolean creatorPortal()
	
	{
		return btnSwitchtoLearner.isDisplayed();
		
	}
	public void clickNewContent()
	
	{
		ca.clickElement(CreateNewContent);
	}
	
	
}
	
	
	


