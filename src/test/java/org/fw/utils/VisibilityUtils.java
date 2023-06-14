package org.fw.utils;

import org.fw.managers.LoggingManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class VisibilityUtils{

    public static void waitUntilVisible(Class<?> className, WebElement element, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception exception) {
            LoggingManager.logError(className, errorMessage, exception);
        }
    }

    public static void waitUntilVisibleCustomTime(Class<?> className, WebElement element, int time, String errorMessage) {
        try {
            WaitUtils.waitCustomTime(time).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception exception) {
            LoggingManager.logError(className, errorMessage, exception);
            throw exception;
        }
    }

    public static void waitUntilVisible(Class<?> className, By locator, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception exception) {
            LoggingManager.logError(className, errorMessage, exception);
        }
    }
}
