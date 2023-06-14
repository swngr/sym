package org.fw.pages;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.components.NotificationsDetailsComponent;
import org.fw.components.PracticeFlowSettingsCardComponent;
import org.fw.managers.MobileDriverManager;
import org.fw.utils.Constants;
import org.fw.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PracticeFlowPage extends BasePage {

    @iOSXCUITFindBy(accessibility = "Back")
    private WebElement backButton;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Back']/following-sibling::XCUIElementTypeButton[3]")
    private WebElement threeDotsButton;

    @iOSXCUITFindBy(accessibility = "Practice Flow Notifications")
    private WebElement practiceFlowNtfButton;

    @iOSXCUITFindBy(accessibility = "Settings")
    private WebElement settingsButton;

    @iOSXCUITFindBy(accessibility = "Upcoming")
    private WebElement upcomingTitleLabel;

    @FindBy(name = "Pending")
    private WebElement pendingTitleLabel;

    @FindBy(name = "Confirmed")
    private WebElement confirmedTitleLabel;

    @FindBy(name = "Checked In")
    private WebElement checkedInTitleLabel;

    @FindBy(name = "Received")
    private WebElement receivedTitleLabel;

    @FindBy(name = "New Room")
    private WebElement newRoomTitleLabel;

    @FindBy(name = "\uF84C")
    private WebElement compressButton;

    @FindBy(name = "\uF004")
    private WebElement contactsButton;

    private final NotificationsDetailsComponent notificationsDetailsComponent = new NotificationsDetailsComponent();
    private final PracticeFlowCardComponent practiceFlowCardComponent = new PracticeFlowCardComponent();
    private final PracticeFlowSettingsCardComponent practiceFlowSettingsCardComponent = new PracticeFlowSettingsCardComponent();

    public boolean isDisplayed() {
        return (isDisplayed(upcomingTitleLabel, "Unable to display the \"upcomingTitleLabel\" element") &&
                isDisplayed(pendingTitleLabel, "Unable to display the \"pendingTitleLabel\" element") &&
                isDisplayed(confirmedTitleLabel, "Unable to display the \"confirmedTitleLabel\" element") &&
                isDisplayed(compressButton, "Unable to display the \"compressButton\" button") &&
                isDisplayed(contactsButton, "Unable to display the \"contactsButton\" button"));
    }

    public void tapBackButton() {
        click(backButton, "Unable to click the \"backButton\" button");
    }

    public boolean isTimezoneDisplayed(String timezone) {
        return isDisplayed(MobileDriverManager.getMobileDriver().findElement(By.name(timezone)), "Unable to display the \"" + timezone + "\" label");
    }

    public void changeApptLocation(Constants.AppointmentLocation locationStart, Constants.AppointmentLocation locationEnd) {
        click(getLocationButton(locationStart), "Unable to click the \"" + locationStart.getName() + "\" button");
        click(getLocationOptButton(locationEnd), "Unable to click the \"" + locationEnd.getName() + "\" button");
        WaitUtils.sleep(getClass(), 5);
    }

    private WebElement getLocationButton(Constants.AppointmentLocation location) {
        WaitUtils.sleep(getClass(), 5);
        return MobileDriverManager.getMobileDriver().findElementByXPath("//XCUIElementTypeButton[contains(@name, '" + location.getName() + "')]");
    }

    private WebElement getLocationOptButton(Constants.AppointmentLocation location) {
        WaitUtils.sleep(getClass(), 5);
        return MobileDriverManager.getMobileDriver().findElementByAccessibilityId(location.getName());
    }

    public boolean isCardDisplayed(String apptUserName, String apptService) {
        WebElement apptUserNameElement = MobileDriverManager.getMobileDriver().findElement(By.name(apptUserName));
        WebElement apptServiceElement = MobileDriverManager.getMobileDriver().findElement(By.name(apptService));
        return (isDisplayed(apptUserNameElement,   "Unable to display the '" + apptUserName + "' element") &&
                isDisplayed(apptServiceElement, "Unable to display the '" + apptService + "' element"));
    }

    public boolean isRoomAngGroupDisplayed(String room, String group){
        WebElement roomElement = waitUntilElementIsVisibleCustomTime(By.name(room), 6);
        WebElement groupElement = waitUntilElementIsVisibleCustomTime(By.name(group), 6);
        if(roomElement != null && groupElement != null){
            return (isDisplayed(roomElement,   "Unable to display the '" + room + "' element") &&
                isDisplayed(groupElement, "Unable to display the '" + group + "' element"));
        }else{
            return false;
        }
    }

    public void tapCard(String apptUserName) {
        WebElement apptUserNameElement = MobileDriverManager.getMobileDriver().findElement(By.name(apptUserName));
        click(apptUserNameElement, "Unable to click the \"apptUserName\" element");
    }

    public WebElement getApptElement(String apptUserName) {
        return MobileDriverManager.getMobileDriver().findElement(By.name(apptUserName));
    }

    public WebElement getPendingElement() {
        return pendingTitleLabel;
    }

    public WebElement getConfirmedElement() {
        return confirmedTitleLabel;
    }

    public PracticeFlowCardComponent getPracticeFlowCardComponent() {
        return practiceFlowCardComponent;
    }

    public void tapNotificationsButton() {
        click(threeDotsButton, "Unable to click the \"threeDotsButton\" button");
        click(practiceFlowNtfButton, "Unable to click the \"practiceFlowNtfButton\" button");
    }

    public void tapSettingsButton() {
        click(threeDotsButton, "Unable to click the \"threeDotsButton\" button");
        click(settingsButton, "Unable to click the \"settingsButton\" button");
    }

    public PracticeFlowSettingsCardComponent getPracticeFlowSettingsCardComponent() {
        return practiceFlowSettingsCardComponent;
    }

    public NotificationsDetailsComponent getNotificationsDetailsComponent() {
        return notificationsDetailsComponent;
    }

}
