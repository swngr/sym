package org.fw.pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.components.BottonNavigatorComponent;
import org.fw.components.ConversationComponent;
import org.fw.components.NewConversationComponent;
import org.fw.managers.MobileDriverManager;
import org.fw.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SecureMessagePage extends BasePage {

    @iOSXCUITFindBy(accessibility = "close")
    private WebElement closeButton;

    @FindBy(name = "Conversations")
    private WebElement conversationsTitle;

    @iOSXCUITFindBy(accessibility = "filter")
    private WebElement filterButton;

    @iOSXCUITFindBy(accessibility = "add")
    private WebElement addNewConversationButton;

    @iOSXCUITFindBy(accessibility = "view_search_field")
    private WebElement searchInput;

    @iOSXCUITFindBy(accessibility = "status_unread")
    private WebElement blueDot;

    private static final NewConversationComponent newConversationComponent = new NewConversationComponent();
    private static final ConversationComponent conversationComponent = new ConversationComponent();
    private static final BottonNavigatorComponent bottonNavigatorComponent = new BottonNavigatorComponent();

    public void tapFilterButton(){
        click(filterButton, "Unable to click the \"Filter\" button");
    }

    public void tapAddNewConversationButton(){
        click(addNewConversationButton, "Unable to click the \"Add New Conversation\" button");
    }

    public BottonNavigatorComponent getBottonNavigatorComponent() {
        return bottonNavigatorComponent;
    }

    public boolean isSecureMessagePageDisplayed() {
        return (isDisplayed(conversationsTitle, "Unable to display the \"Secure Message Title\" element") &&
                isDisplayed(filterButton, "Unable to display the \"Filter\" button") &&
                isDisplayed(addNewConversationButton, "Unable to display the \"Add\" button") &&
                isDisplayed(searchInput, "Unable to display the \"Search\" input"));
    }

    public void searchMessageWithPatient(String patientFirstname) {
        clear(searchInput, "Unable to clear the \"Search Input\" element");
        sendKeys(searchInput, patientFirstname, "Unable to send keys to the \"Search Input\" element");
        hideKeyboard();
    }

    public boolean isSearchResultOnlyWithPatientSearched(String patientFirstname) {
        List<WebElement> cells = getConversationList().findElements(MobileBy.iOSClassChain("XCUIElementTypeCell"));
        int sizeOfList = cells.size();
        int amountOfOccurrences = getConversationList().findElements(MobileBy.xpath("//*[contains(@label, '"+patientFirstname+"')]")).size();
        return(sizeOfList == amountOfOccurrences);
    }

    public void tapConversation(String conversationName){
        WaitUtils.sleep(getClass(), 5);
        WebElement conversation = getConversationList().findElement(MobileBy.xpath("//*[contains(@label, '"+conversationName+"')]"));
        click(conversation, "Unable to click the \"Conversation\" list element");
    }

    public void tapFirstConversationWithMessage(){
        WaitUtils.sleep(getClass(), 5);
        WebElement webElement = getConversationList().findElement(MobileBy.AccessibilityId("cell_0"));
        click(webElement, "Unable to click the \"Conversation\" list element");
    }

    public void submitNewConversationWithoutPatientsForm(String conversationName) {
        tapAddNewConversationButton();
        newConversationComponent.submitNewConversationWithoutPatientForm(conversationName);
    }

    public void submitNewConversationForm(String conversationName, String patientName, String memberName) {
        tapAddNewConversationButton();
        newConversationComponent.submitNewConversationForm(conversationName, patientName, memberName);
    }

    public ConversationComponent getConversationComponent() {
        return conversationComponent;
    }

    public void clearSearchInputField(){
        clear(searchInput, "Unable to clear the \"Search Input\" element");
        hideKeyboard();
    }

    public boolean isFirstConversationTheMessageSent(String conversationName) {
        WebElement conversationNameCell = getConversationList().findElement(MobileBy.AccessibilityId("cell_0"));
        // xpath find inside the conversationNameElement element an XCUIElementTypeStaticText with name = label_name and value 'conversationName'
        WebElement conversationNameElement = conversationNameCell.findElement(By.xpath(".//XCUIElementTypeStaticText[@name='label_name' and @value='"+conversationName+"']"));
        return isDisplayed(conversationNameElement, "Unable to display the \"blueDot\" list element");
    }

    public boolean isBlueDotDisplayedAtFirstMessage() {
        return isDisplayed(blueDot, "Unable to display the \"blueDot\" list element");
    }

    private WebElement getConversationList(){
        return MobileDriverManager.getMobileDriver().findElementByXPath("//XCUIElementTypeTable[1]");
    }

    public void tapClose() {
        click(closeButton, "Unable to click the \"closeButton\" button");
    }

}
