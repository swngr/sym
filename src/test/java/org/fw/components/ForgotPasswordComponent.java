package org.fw.components;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.pages.BasePage;
import org.openqa.selenium.WebElement;

public class ForgotPasswordComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "close_left_nav_bar")
    private WebElement cancelButton;

    @iOSXCUITFindBy(accessibility = "image")
    private WebElement symIcon;

    @iOSXCUITFindBy(accessibility = "username_1")
    private WebElement usernameInput;

    @iOSXCUITFindBy(accessibility = "action")
    private WebElement resetPasswordButton;

    public boolean isComponentDisplayed() {
        return (isDisplayed(cancelButton, "Unable to display \"Cancel\" button") &&
                isDisplayed(symIcon, "Unable to display \"Symplast\" icon") &&
                isDisplayed(usernameInput, "Unable to display \"User Name\" input") &&
                isDisplayed(resetPasswordButton, "Unable to display \"Reset Password\" button"));
    }

    public void submitForgotPasswordForm(String userName){
        sendKeys(usernameInput, userName, "Unable to sed keys to \"User Name\" input");
        click(resetPasswordButton, "Unable to click \"Reset Password\" button");
    }

}
