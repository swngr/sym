package org.fw.components;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.pages.BasePage;
import org.openqa.selenium.WebElement;

public class EhrNoteComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "close_left_nav_bar")
    private WebElement closeButton;

    @iOSXCUITFindBy(accessibility = "Tap To Post and Create Task")
    private WebElement tapToPostAndCreateTask;

    @iOSXCUITFindBy(accessibility = "calendar")
    private WebElement calendar;

    @iOSXCUITFindBy(accessibility = "grey up")
    private WebElement greyUp;

    @iOSXCUITFindBy(accessibility = "Health History")
    private WebElement healthHistory;

    public boolean isDisplayed() {
        return (isDisplayed(tapToPostAndCreateTask, "Unable to find the \"tapToPostAndCreateTask\" button") &&
                isDisplayed(calendar, "Unable to find the \"calendar\" button") &&
                isDisplayed(greyUp, "Unable to find the \"greyUp\" button") &&
                isDisplayed(healthHistory, "Unable to find the \"healthHistory\" button"));
    }

    public void tapClose() {
        click(closeButton, "Unable to click the \"closeButton\" button");
    }

}
