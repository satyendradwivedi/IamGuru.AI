package com.Gooru.AI.utilities;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownUtility {

    // Select option by visible text
    public static void selectByVisibleText(WebElement dropdownElement, String visibleText) {
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(visibleText);
    }

    // Select option by value attribute
    public static void selectByValue(WebElement dropdownElement, String value) {
        Select select = new Select(dropdownElement);
        select.selectByValue(value);
    }

    // Select option by index
    public static void selectByIndex(WebElement dropdownElement, int index) {
        Select select = new Select(dropdownElement);
        select.selectByIndex(index);
    }

    // Get selected option text
    public static String getSelectedOption(WebElement dropdownElement) {
        Select select = new Select(dropdownElement);
        return select.getFirstSelectedOption().getText();
    }

    // Deselect all options (only works with multi-select dropdowns)
    public static void deselectAll(WebElement dropdownElement) {
        Select select = new Select(dropdownElement);
        select.deselectAll();
    }

    // Check if dropdown is multi-select
    public static boolean isMultiple(WebElement dropdownElement) {
        Select select = new Select(dropdownElement);
        return select.isMultiple();
    }

    // Get all options
    public static List<String> getAllOptions(WebElement dropdownElement) {
        Select select = new Select(dropdownElement);
        List<WebElement> options = select.getOptions();
        List<String> optionTexts = new ArrayList<>();
        for (WebElement option : options) {
            optionTexts.add(option.getText());
        }
        return optionTexts;
    }
}

