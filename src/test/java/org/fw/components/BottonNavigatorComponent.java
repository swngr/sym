package org.fw.components;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.pages.BasePage;
import org.openqa.selenium.WebElement;

public class BottonNavigatorComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "tab_item_home")
    private WebElement homeButton;

    @iOSXCUITFindBy(accessibility = "tab_item_calendar")
    private WebElement calendarButton;

    @iOSXCUITFindBy(accessibility = "tab_item_voice_helper")
    private WebElement micButton;

    @iOSXCUITFindBy(accessibility = "tab_item_chat")
    private WebElement messageButton;

    @iOSXCUITFindBy(accessibility = "tab_item_search")
    private WebElement patientButton;

    public boolean isNavBarButtonsDisplayed(){
        String errorMessage = "Unable to display \"Botton NavBar\" elements";
        return (isDisplayed(homeButton, errorMessage) &&
                isDisplayed(calendarButton, errorMessage) &&
                isDisplayed(micButton, errorMessage) &&
                isDisplayed(messageButton, errorMessage) &&
                isDisplayed(patientButton, errorMessage));
    }

    public void tapHomeButton(){
        click(homeButton, "Unable to click the \"Home\" button");
    }

    public void tapCalendarButton(){
        click(calendarButton, "Unable to click the \"Calendar\" button");
    }

    public void tapMicButton(){
        click(micButton, "Unable to click the \"Mic\" button");
    }

    public void tapMessageButton(){
        click(messageButton, "Unable to click the \"Message\" button");
    }

    public void tapPatientButton(){
        click(patientButton, "Unable to click the \"Patient\" button");
    }

    public boolean isMessageAttributeWithNotification() {
        return getAttribute(messageButton, "value", "Unable to get attribute from \"messageButton\" element").equals("1 item");
    }

    public boolean IsMessagingButtonNotified() {
        return getAttribute(messageButton, "value", "Unable to get attribute from \"messageButton\" element").contains("item");
    }
}
