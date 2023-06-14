package org.fw.components;

import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.managers.MobileDriverManager;
import org.fw.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NotificationsDetailsComponent extends BasePage {

    // Contracted

    @iOSXCUITFindBy(accessibility = "Practice Flow Notifications")
    private WebElement practiceFlowNtfButton;

    @iOSXCUITFindBy(accessibility = "OK")
    private WebElement okButton;

    // Expanded

    @iOSXCUITFindBy(accessibility = "Cancel")
    private WebElement cancelButton;

    @FindBy(name = "Notifications Details")
    private WebElement notificationsDetailsTitle;

    @iOSXCUITFindBy(accessibility = "Save")
    private WebElement saveButton;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='documents album edit']/../..//XCUIElementTypeTextField")
    private WebElement searchNameTextField;

    @iOSXCUITFindBy(accessibility = "checkmark")
    private WebElement checkmarkButton;

    // Component Fields

    @iOSXCUITFindBy(accessibility = "documents album edit")
    private WebElement documentsAlbumEditButton;

    public boolean isDisplayed() {
        return (isDisplayed(practiceFlowNtfButton, "Unable to display the 'practiceFlowNtfButton' button") &&
                isDisplayed(okButton, "Unable to display the 'okButton' button") &&
                isDisplayed(documentsAlbumEditButton, "Unable to display the 'documentsAlbumEditButton' element"));
    }

    public void addUserToNotificationList(String userName) {
        click(documentsAlbumEditButton, "Unable to click on the 'documentsAlbumEditButton' button");
        sendKeys(searchNameTextField, userName, "Unable to send keys to the 'searchNameTextField' text field");
        tapUserNameFound(userName);
        click(saveButton, "Unable to click on the 'saveButton' button");
        click(okButton, "Unable to click on the 'okButton' button");
    }

    public void tapUserNameFound(String userName) {
        click(getUsernameFound(userName), "Unable to click on the '" + userName + "' found");
    }

    private WebElement getUsernameFound(String userName) {
        return MobileDriverManager.getMobileDriver().findElement(MobileBy.AccessibilityId(userName));
    }

    public boolean isUserAddedToNotificationList(String userName) {
        click(documentsAlbumEditButton, "Unable to click on the 'documentsAlbumEditButton' button");
        sendKeys(searchNameTextField, userName, "Unable to send keys to the 'searchNameTextField' text field");
        return (isDisplayed(getUsernameFound(userName), "Unable to display the '" + userName + "' found") &&
                isDisplayed(checkmarkButton, "Unable to display the 'checkmarkButton' button"));
    }

    public void removeUserFromNotificationList(String userName) {
        tapUserNameFound(userName);
        click(saveButton, "Unable to click on the 'saveButton' button");
        click(okButton, "Unable to click on the 'okButton' button");
    }

}
