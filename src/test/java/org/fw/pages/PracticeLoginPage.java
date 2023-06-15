package org.fw.pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.managers.MobileDriverManager;
import org.fw.utils.Constants;
import org.fw.utils.UserDataUtils;
import org.openqa.selenium.WebElement;

public class PracticeLoginPage extends BasePage {

    @iOSXCUITFindBy(accessibility = "image_0")
    private WebElement symIcon;

    @iOSXCUITFindBy(accessibility = "practice_name")
    private WebElement practiceInput;

    @iOSXCUITFindBy(accessibility = "action")
    private WebElement proceedLoginButton;

    private static boolean isPracticeChosen = false;

    public void practiceSelection() {
        if(isPracticeChosen) {
            return;
        }
        isPracticeChosen = true;
        submitPracticeForm(UserDataUtils.getUserData(Constants.PRACTICE));
    }

    public boolean isProceedLoginButtonDisplayed () {
        return isDisplayed(proceedLoginButton, "Unable to display the \"Proceed Login\" button");
    }

    public void submitPracticeForm(String practice) {
        if(isDisplayed()) {
            sendKeys(practiceInput, practice, "Unable to send key to the \"Practice Name\" field");
            WebElement optPractice = MobileDriverManager.getMobileDriver().findElementByAccessibilityId(UserDataUtils.getUserData(Constants.PRACTICE));
            click(optPractice, "Unable to click the \"Practice\" option");
            click(proceedLoginButton, "Unable to click the \"Proceed to Login\" button");
        }
    }

    public boolean isSymIconDisplayed(){
        return isDisplayed(symIcon, "Unable to display the \"Symplast\" icon");
    }

    public boolean isDisplayed(){
        WebElement proceedToLogin = waitUntilElementIsVisibleCustomTime(MobileBy.AccessibilityId("Proceed To Login"), 5);
        return proceedToLogin!=null;
    }

}
