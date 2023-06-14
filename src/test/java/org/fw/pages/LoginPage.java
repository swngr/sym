package org.fw.pages;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.components.ForgotPasswordComponent;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    @iOSXCUITFindBy(accessibility = "image_1")
    private WebElement symplastIcon;

    @iOSXCUITFindBy(accessibility = "label_0")
    private WebElement practiceName;

    @iOSXCUITFindBy(accessibility = "username")
    private WebElement emailInput;

    @iOSXCUITFindBy(accessibility = "password")
    private WebElement passwordInput;

    @iOSXCUITFindBy(accessibility = "sign_in")
    private WebElement loginButton;

    @iOSXCUITFindBy(accessibility = "button_0")
    private WebElement forgotPassButton;

    private static final ForgotPasswordComponent forgotPasswordComp = new ForgotPasswordComponent();

    public void submitLoginForm(String userName, String password) {
        clear(emailInput, "Unable to clear the \"User Name\" field");
        sendKeys(emailInput, userName, "Unable to send key to the \"User Name\" field");
        passwordInput.clear();
        sendKeys(passwordInput, password, "Unable to send key to the \"Password\" field");
        click(loginButton, "Unable to click the \"Login\" button");
    }

    public String getPracticeName(){
        return getText(practiceName, "Unable to get text from \"Practice Name\" field");
    }

    public boolean isSymplastIconDisplayed(){
        return isDisplayed(symplastIcon, "Unable to display the \"Symplast\" icon");
    }

    public boolean isForgotPasswordDisplayed(){
        return isDisplayed(forgotPassButton, "Unable to display the \"Forgot Password\" button");
    }

    public void tapForgotPasswordButton(){
        click(forgotPassButton, "Unable to click the \"Forgot Password\" button");
    }

    public ForgotPasswordComponent getForgotPasswordComp() {
        return forgotPasswordComp;
    }

}
