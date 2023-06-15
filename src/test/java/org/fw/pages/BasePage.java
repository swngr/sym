package org.fw.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.fw.managers.MobileDriverManager;
import org.fw.utils.*;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.Map;

public class BasePage {

    public BasePage() {
        initializePageElements();
    }

    private void initializePageElements() {
        PageFactory.initElements(new AppiumFieldDecorator(MobileDriverManager.getMobileDriver()), this);
    }

    public BasePage click(WebElement element, String errorMessage) {
        waitUntilVisible(element, errorMessage);
        waitUntilClickable(element, errorMessage);
        ActionUtils.click(getClass(), element, errorMessage);
        return this;
    }

    public boolean isDisplayed(WebElement element, String errorMessage) {
        waitUntilVisible(element, errorMessage);
        return ActionUtils.isDisplayed(element);
    }

    public boolean isDisplayedCustomTime(WebElement element, int time, String errorMessage) {
        waitUntilVisibleCustomTime(element, time, errorMessage);
        return ActionUtils.isDisplayed(element);
    }

    public boolean isEnabled(WebElement element, String errorMessage) {
        return ActionUtils.isEnabled(element);
    }

    public boolean isSelected(WebElement element, String errorMessage) {
        waitUntilVisible(element, errorMessage);
        return ActionUtils.isSelected(element);
    }

    public BasePage sendKeys(WebElement element, String keys, String errorMessage) {
        waitUntilVisible(element, errorMessage);
        waitUntilClickable(element, errorMessage);
        ActionUtils.sendKeys(element, keys);
        return this;
    }

    public void clear(WebElement element, String errorMessage) {
        waitUntilVisible(element, errorMessage);
        waitUntilClickable(element, errorMessage);
        ActionUtils.clear(element);
    }

    public BasePage setValues(MobileElement element, String keys, String errorMessage) {
        waitUntilVisible(element, errorMessage);
        waitUntilClickable(element, errorMessage);
        ActionUtils.setValues(element, keys);
        return this;
    }

    public String getText(WebElement element, String errorMessage) {
        waitUntilVisible(element, errorMessage);
        return ActionUtils.getText(element, errorMessage);
    }

    public String getAttribute(WebElement element, String keys, String errorMessage) {
        waitUntilVisible(element, errorMessage);
        return ActionUtils.getAttribute(element, keys);
    }

    public void waitUntilVisible(WebElement element, String errorMessage) {
        VisibilityUtils.waitUntilVisible(getClass(), element, errorMessage);
    }

    public WebElement waitUntilElementIsVisible(By byLocator){
        try {
            return WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfElementLocated(byLocator));
        }catch (Exception e) {
            return null;
        }
    }

    public WebElement waitUntilElementIsVisibleCustomTime(By byLocator, int time){
        try {
            return WaitUtils.waitCustomTime(time).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfElementLocated(byLocator));
        }catch (Exception e) {
            return null;
        }
    }

    public void waitUntilVisibleCustomTime(WebElement element, int time, String errorMessage) {
        VisibilityUtils.waitUntilVisibleCustomTime(getClass(), element, time, errorMessage);
    }

    public BasePage waitUntilClickable(WebElement element, String errorMessage) {
        ActionUtils.waitUntilClickable(getClass(), element, errorMessage);
        return this;
    }

    public BasePage waitUntilClickable(By locator, String errorMessage) {
        ActionUtils.waitUntilClickable(getClass(), locator, errorMessage);
        return this;
    }

    public void swipe(Constants.Direction direction){
        hideKeyboard();
        HashMap<String, String> swipeObject = new HashMap<>();
        swipeObject.put("direction", direction.getName());
        MobileDriverManager.getMobileDriver().executeScript("mobile:swipe", swipeObject);
    }

//    public void swipeToBotton(ConversationComponent conversationComponent, Constants.Direction direction){
//        hideKeyboard();
//        HashMap<String, String> swipeObject = new HashMap<>();
//        swipeObject.put("direction", direction.getName());
//
//        int chatSize = conversationComponent.getChatListSize();
//        do{
//            MobileDriverManager.getMobileDriver().executeScript("mobile:swipe", swipeObject);
//            WaitUtils.sleep(getClass(), 3);
//        }while(chatSize < conversationComponent.getChatListSize());
//    }

    public void hideKeyboard(){
        MobileDriverManager.getMobileDriver().getKeyboard().pressKey("\n");
    }

    public void minimizeAppDev(){
        Map<String, Object> args = new HashMap<>();
        args.put("name", "home");
        MobileDriverManager.getMobileDriver().executeScript("mobile: pressButton", args);
    }

    public void goBackToSymApp(){
        MobileDriverManager.getMobileDriver().activateApp(UserDataUtils.getUserData(Constants.APP_ID));
    }

    public String getPageTitle(){
        return MobileDriverManager.getMobileDriver().findElement(By.className("XCUIElementTypeNavigationBar")).getAttribute("name");
    }

    public WebElement getPageTitleElement(){
        return MobileDriverManager.getMobileDriver().findElement(By.className("XCUIElementTypeNavigationBar"));
    }

    public boolean isElementFavorited(WebElement element, String errorMessage){
        waitUntilVisible(element, errorMessage);
        return !element.getAttribute("label").contains("not");
    }

    public WebElement swipeToElementUntilVisible(Constants.Direction direction, By byLocator) {
        do {
            swipe(direction);
        }while(MobileDriverManager.getMobileDriver().findElements(byLocator).isEmpty());
        swipe(direction);
        return MobileDriverManager.getMobileDriver().findElements(byLocator).get(0);
    }

    public void tapOutsideElement(){
        // Appium will tap on screen position x,y
        Map<String, Object> args = new HashMap<>();
        args.put("x", 20);
        args.put("y", 60);
        MobileDriverManager.getMobileDriver().executeScript("mobile: tap", args);
    }

}
