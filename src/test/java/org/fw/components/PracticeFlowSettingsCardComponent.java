package org.fw.components;

import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.managers.MobileDriverManager;
import org.fw.pages.BasePage;
import org.fw.utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PracticeFlowSettingsCardComponent extends BasePage {

    // Navigation Bar
    @iOSXCUITFindBy(accessibility = "Close")
    private WebElement closeButton;

    @iOSXCUITFindBy(accessibility = "Practice Flow")
    private WebElement componentTitle;

    @iOSXCUITFindBy(accessibility = "  Next >  ")
    private WebElement nextButton;

    @iOSXCUITFindBy(accessibility = "  Save  ")
    private WebElement saveButton;

    // Part 1/2 - Setup Rooms

    private WebElement getRoom(String roomName){
        return MobileDriverManager.getMobileDriver().findElement(By.name(roomName));
    }

    @iOSXCUITFindBy(accessibility = "Check Out")
    private WebElement checkOutButton;

    @iOSXCUITFindBy(accessibility = "Checked In")
    private WebElement checkedIntButton;

    @iOSXCUITFindBy(accessibility = "Confirmed")
    private WebElement confirmedButton;

    @iOSXCUITFindBy(accessibility = "Online Waiting Area")
    private WebElement onlineWaitingAreaButton;

    @iOSXCUITFindBy(accessibility = "Pending")
    private WebElement pendingButton;

    @iOSXCUITFindBy(accessibility = "Received")
    private WebElement receivedButton;

    @iOSXCUITFindBy(accessibility = "Add a Room +")
    private WebElement addRoomButton;

    @iOSXCUITFindBy(accessibility = "Delete room")
    private WebElement deleteRoomButton;

    // Room Details

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Room Name*']/following-sibling::XCUIElementTypeTextField")
    private WebElement roomNameField;

    @iOSXCUITFindBy(accessibility = "Appointment Status(es)*")
    private WebElement appointmentStatusField;

    // Part 2/2 - Setup Groups


    // Group Details

    @iOSXCUITFindBy(accessibility = "Upcoming")
    private WebElement upcomingButton;

    @iOSXCUITFindBy(accessibility = "Assigned Rooms")
    private WebElement assignedRoomsButton;

    // Upcoming

    private void assignRooms(String roomName){
        WebElement assignButton = MobileDriverManager.getMobileDriver().findElement(MobileBy.AccessibilityId(roomName));
        click(assignButton, "Unable to click the \"" + roomName + "\" button");
    }

    private final AppointmentMultiStatusComponent appointmentMultiStatusComponent = new AppointmentMultiStatusComponent();

    private WebElement finishButton(){
        return MobileDriverManager.getMobileDriver().findElement(By.name("Finish"));
    }

    public void tapCloseButton() {
        click(closeButton, "Unable to click the \"Close\" button");
    }

    public boolean isDisplayed() {
        waitUntilElementIsVisibleCustomTime(MobileBy.AccessibilityId("Close"), 3);
        return (isDisplayed(closeButton, "Close button is not displayed") &&
                isDisplayed(componentTitle, "Practice Flow Settings Card title is not displayed") &&
                isDisplayed(nextButton, "Next button is not displayed") &&
                isDisplayed(checkOutButton, "Check Out button is not displayed") &&
                isDisplayed(checkedIntButton, "Check In button is not displayed") &&
                isDisplayed(confirmedButton, "Confirmed button is not displayed") &&
                isDisplayed(onlineWaitingAreaButton, "Online Waiting Area button is not displayed") &&
                isDisplayed(pendingButton, "Pending button is not displayed") &&
                isDisplayed(receivedButton, "Received button is not displayed") &&
                isDisplayed(addRoomButton, "Add a Room + button is not displayed"));
    }

    public void deleteRoom(String roomName) {
        click(getRoom(roomName), "Unable to click the \"" + roomName + "\" room");
        click(deleteRoomButton, "Unable to click the \"Delete room\" button");
        click(nextButton, "Unable to click the \"Next\" button");
        click(nextButton, "Unable to click the \"Next\" button");
        click(swipeToElementUntilVisible(Constants.Direction.UP, By.name("Finish")), "Unable to click the \"Finish\" button");
    }

    public void createRoom(String roomName) {
        swipeToElementUntilVisible(Constants.Direction.UP, By.name("Add a Room +"));
        click(addRoomButton, "Unable to click the \"Add a Room +\" button");
        sendKeys(roomNameField, roomName, "Unable to send keys to the \"Room Name\" field");
        click(appointmentStatusField, "Unable to click the \"Appointment Status\" field");
        appointmentMultiStatusComponent.selectAppointmentStatus(Constants.AppointmentStatus.AUTO_TEST_ROOM_STATUS);
        click(saveButton, "Unable to click the \"Save\" button");
        click(saveButton, "Unable to click the \"Save\" button");
        click(nextButton, "Unable to click the \"Next\" button");
    }

    public void addRoomAtGroup(String roomName, String groupName) {
        click(upcomingButton, "Unable to click the \"Check Out\" button");
        click(assignedRoomsButton, "Unable to click the \"Check In\" button");
        assignRooms(roomName);
        click(saveButton, "Unable to click the \"Save\" button");
        click(saveButton, "Unable to click the \"Save\" button");
        click(nextButton, "Unable to click the \"Next\" button");
        click(swipeToElementUntilVisible(Constants.Direction.UP, By.name("Finish")), "Unable to click the \"Finish\" button");
    }

}
