package org.fw.components;

import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddConversationMemberComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "Cancel")
    private WebElement cancelButton;

    @iOSXCUITFindBy(accessibility = "Save")
    private WebElement saveButton;

    @iOSXCUITFindBy(accessibility = "Patients")
    private WebElement patientsTabButton;

    @iOSXCUITFindBy(accessibility = "Practice")
    private WebElement practiceTabButton;

    @iOSXCUITFindBy(accessibility = "Partners")
    private WebElement partnersTabButton;

    @iOSXCUITFindBy(accessibility = "Teams")
    private WebElement teamsTabButton;

    @FindBy(xpath = "//XCUIElementTypeApplication[@name=\"Practice-Dev\"]/XCUIElementTypeWindow/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeOther")
    private WebElement participantsInput;

    @FindBy(xpath = "//XCUIElementTypeApplication[@name=\"Practice-Dev\"]/XCUIElementTypeWindow/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeTable")
    private WebElement participantList;

    public void tapSaveButton(){
        click(saveButton, "Unable to click the \"Save\" button");
    }

    public void submitConversationMemberForm(String conversationMember) {
        click(practiceTabButton, "Unable to click the \"Practice Tab\" button");
        sendKeys(participantsInput, conversationMember, "Unable to send values to the \"Patient Name\" input");
        tapParticipantSearched(conversationMember);
        tapSaveButton();
    }

    private void tapParticipantSearched(String conversationMember) {
        WebElement webElement = participantList.findElement(MobileBy.AccessibilityId(conversationMember));
        click(webElement, "Unable to click the \""+conversationMember+"\" element");
    }

}
