package org.fw.components;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.pages.BasePage;
import org.openqa.selenium.WebElement;

public class IphoneCameraComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "ic call end")
    private WebElement hangOutButton;

    @iOSXCUITFindBy(accessibility = "microphone")
    private WebElement microphoneButton;

    public void tapHangoutMeetingButton(){
        click(hangOutButton, "Unable to click the \"hangOutButton\" button");
    }

    public boolean isDisplayed(){
        return (isDisplayed(hangOutButton, "Unable to click the \"hangOutButton\" button") &&
                isDisplayed(microphoneButton, "Unable to click the \"microphoneButton\" button"));
    }

}
