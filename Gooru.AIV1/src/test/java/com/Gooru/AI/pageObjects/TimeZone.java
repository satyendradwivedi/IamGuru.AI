package com.Gooru.AI.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import com.Gooru.AI.utilities.ClickActions;
import com.Gooru.AI.utilities.JavaScriptUtils;

public class TimeZone extends BasePage {

	
	ClickActions ca;
	JavaScriptUtils ju;

	public TimeZone(WebDriver driver) {
		super(driver);
		ca = new ClickActions(driver);
		ju=new JavaScriptUtils(driver);

	}

	@FindBy(xpath = "//button[normalize-space()='Confirm']")
	@CacheLookup
	private WebElement timezoneconfirm;


public void timeZoneConfirm()
{
	ca.clickElement(timezoneconfirm);
}
}
