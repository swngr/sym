package org.fw.components;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewConversationComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "cancel")
    private WebElement cancelButton;

    @iOSXCUITFindBy(accessibility = "button_save")
    private WebElement saveButton;

    @iOSXCUITFindBy(accessibility = "label_conversation_name")
    private WebElement conversationNameInput;

    @iOSXCUITFindBy(accessibility = "None Selected")
    private WebElement selectPatientButton;

    @iOSXCUITFindBy(accessibility = "Add a conversation member +")
    private WebElement selectMemberButton;

    private static final SelectPatientComponent selectPatientComponent = new SelectPatientComponent();
    private static final AddConversationMemberComponent addConversationMemberComponent = new AddConversationMemberComponent();

    public void submitNewConversationWithoutPatientForm(String conversationName) {
        // add name
        sendKeys(conversationNameInput, conversationName, "Unable to send values to the \"Conversation Name\" input");
        click(saveButton, "Unable to click the \"Save\" button");
    }

    public void submitNewConversationForm(String conversationName, String patientName, String memberName) {
        // add name
        sendKeys(conversationNameInput, conversationName, "Unable to send values to the \"Conversation Name\" input");
        hideKeyboard();
        // add conversation
        click(selectPatientButton, "Unable to click the \"Select Patient\" button");
        selectPatientComponent.submitPatientForm(patientName);
        // add member
        click(selectMemberButton, "Unable to click the \"Select Member\" button");
        addConversationMemberComponent.submitConversationMemberForm(memberName);
        click(saveButton, "Unable to click the \"Save\" button");
    }

}
