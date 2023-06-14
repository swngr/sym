package org.fw.components;

import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.pages.BasePage;
import org.fw.utils.Constants;
import org.fw.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ConversationComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "Back")
    private WebElement backButton;

    @iOSXCUITFindBy(accessibility = "button_edit")
    private WebElement editButton;

    @iOSXCUITFindBy(accessibility = "list")
    private WebElement messageChatTable;

    @iOSXCUITFindBy(accessibility = "view_input")
    private WebElement messageInput;

    @iOSXCUITFindBy(accessibility = "button_video_chat")
    private WebElement messageInputStartVideoChatButton;

    @iOSXCUITFindBy(accessibility = "button_photo")
    private WebElement messageInputAddPhotoButton;

    @iOSXCUITFindBy(accessibility = "Patient's Multimedia Gallery")
    private WebElement optPatientMultimediaGallery;

    @iOSXCUITFindBy(accessibility = "Device's Photo Gallery")
    private WebElement optDevicePhotoGallery;

    @iOSXCUITFindBy(accessibility = "Horizontal scroll bar, 1 page")
    private WebElement optCamera;

    private final PatientMultimediaGalleryComponent patientMultimediaGalleryComponent = new PatientMultimediaGalleryComponent();


    @iOSXCUITFindBy(accessibility = "button_send")
    private WebElement sendMessageButton;

    private final IphoneCameraComponent iphoneCameraComponent = new IphoneCameraComponent();

    private final ImageGalleryComponent imageGalleryComponent = new ImageGalleryComponent();

    public boolean isDisplayed(){
        return (isDisplayed(backButton, "Unable to display the \"Back\" button") &&
                isDisplayed(messageInput, "Unable to display the \"Message\" input") &&
                isDisplayed(sendMessageButton, "Unable to display the \"Send Message\" button"));
    }

    public void submitMessageThroughConversationForm(String message) {
        sendKeys(messageInput, message, "Unable to send keys to the \"messageInput\" field");
        click(sendMessageButton, "Unable to click the \"sendMessageButton\" button");
        click(messageInput, "Unable to click the \"messageInput\" button");
    }

    public boolean isDisplayed(String expectedChatMessage) {
        String xpath = "//*[self::XCUIElementTypeTextView[@value='" + expectedChatMessage + "']]";
        WebElement chatMessage = messageChatTable.findElement(By.xpath(xpath));
        return isDisplayed(chatMessage, "Unable to display the \"chatMessage\" element");
    }

    public int getChatListSize(){
        WaitUtils.sleep(this.getClass(), 5);
        return messageChatTable.findElements(MobileBy.iOSClassChain("XCUIElementTypeCell")).size();
    }

    public void tapMeetNowButton(){
        click(messageInputStartVideoChatButton, "Unable to click the \"messageInputStartVideoChatButton\" button");
    }

    public void tapStartMeetFromChat(String conversationTitle){
        swipe(Constants.Direction.UP);
        WebElement clickButton = messageChatTable.findElement(By.name(conversationTitle));
        click(clickButton, "Unable to click the \"joinVideoCallElement\" element");
    }

    public void tapAddMediaGalleryPhoto(){
        WaitUtils.sleep(getClass(), 3);
        click(messageInputAddPhotoButton, "Unable to click the \"messageInputAddPhotoButton\" button");
        click(optPatientMultimediaGallery, "Unable to click the \"optPatientMultimediaGallery\" button");
        WaitUtils.sleep(getClass(), 5);
        patientMultimediaGalleryComponent.tapTheMostRecentAddedPhoto();
    }

    public void tapAddPhotoButton(){
        click(messageInputAddPhotoButton, "Unable to click the \"messageInputAddPhotoButton\" button");
    }

    public void tapBackButton() {
        click(backButton, "Unable to click the \"backButton\" button");
    }

    public IphoneCameraComponent getIphoneCameraComponent() {
        return iphoneCameraComponent;
    }

    public ImageGalleryComponent getImageGalleryComponent() {
        return imageGalleryComponent;
    }

    public void tapLastImageOnChat() {
        swipe(Constants.Direction.UP);
        // find an XCUIElementTypeCell element with name = 'cell_media' if visible true and a positive y coordinate
        WebElement imageElement = messageChatTable.findElements(By.xpath("//XCUIElementTypeCell[@name='cell_media' and @visible='true' and @y>0]")).get(0);
        click(imageElement, "Unable to click the \"imageElement\" element");
    }

}
