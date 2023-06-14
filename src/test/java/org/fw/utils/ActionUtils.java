package org.fw.utils;

import io.appium.java_client.MobileElement;
import org.fw.managers.LoggingManager;
import org.fw.managers.MobileDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ActionUtils {

    public static void click(Class<?> className, By locator, String errorMessage) {
        WebElement element = MobileDriverManager.getMobileDriver().findElement(locator);
        element.click();
    }

    public static void click(Class<?> className, WebElement element, String errorMessage) {
        element.click();
    }

    public static void sendKeys(WebElement element, String keys) {
        element.sendKeys(keys);
    }

    public static void clear(WebElement element) {
        element.clear();
    }

    public static void setValues(MobileElement element, String keys) {
        element.setValue(keys);
    }

    public static String getAttribute(WebElement element, String keys) {
        return element.getAttribute(keys);
    }

    public static boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public static boolean isEnabled(WebElement element) {
        return element.isEnabled();
    }

    public static boolean isSelected(WebElement element) {
        if(element.getText().equals("1")){
            return true;
        }else{
            return false;
        }
    }

    public static String getText(WebElement element, String errorMessage) {
        return element.getText();
    }

    public static void waitUntilClickable(Class<?> className, WebElement element, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));

        } catch (Exception exception) {
            LoggingManager.logError(className, errorMessage, exception);
        }
    }

    public static void waitUntilClickable(Class<?> className, By locator, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(locator));

        } catch (Exception exception) {
            LoggingManager.logError(className, errorMessage, exception);
        }
    }

}
