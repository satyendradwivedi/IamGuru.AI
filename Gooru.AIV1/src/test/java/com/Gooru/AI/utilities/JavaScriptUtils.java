package com.Gooru.AI.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {

    private WebDriver driver;

    // Constructor to initialize the WebDriver
    public JavaScriptUtils(WebDriver driver) {
        this.driver = driver;
    }

    // Method to send keys to an element using JavaScript
    public void javaScriptSendKeys(WebElement element, String keysToSend) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].value = arguments[1];", element, keysToSend);
    }

    // Method to click an element using JavaScript
    public void javaScriptClick(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click();", element);
    }

    // Method to scroll to an element using JavaScript
    public void scrollToElement(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

	public void removeAttribute(WebElement proceedbtn, String string) {
		// TODO Auto-generated method stub
		
	}

	
}
