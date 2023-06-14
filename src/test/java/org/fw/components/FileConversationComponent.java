package org.fw.components;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.pages.BasePage;
import org.openqa.selenium.WebElement;

public class FileConversationComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "")
    private WebElement cancelButton;

    @iOSXCUITFindBy(accessibility = "")
    private WebElement fileConversationTitle;

    @iOSXCUITFindBy(accessibility = "")
    private WebElement noneButton;

    @iOSXCUITFindBy(accessibility = "")
    private WebElement allButton;

    @iOSXCUITFindBy(accessibility = "")
    private WebElement applyButton;

    @iOSXCUITFindBy(accessibility = "")
    private WebElement conversationList;

    public void tapCancelButton() {
        click(cancelButton , "Unable to click the \"Cancel\" button");
    }

}
