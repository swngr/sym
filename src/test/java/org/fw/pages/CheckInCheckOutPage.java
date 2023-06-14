package org.fw.pages;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class CheckInCheckOutPage extends BasePage{

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label contains \"Check\"`][1]")
    private WebElement checkInCheckOutTitle;

    @iOSXCUITFindBy(accessibility = "Close")
    private WebElement closeButton;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeNavigationBar[`name CONTAINS \"Check\"`]/**/XCUIElementTypeButton[2]")
    private WebElement appointmentLocation;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell[1]/**/XCUIElementTypeStaticText[3]")
    private WebElement appointmentProvider;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell[1]/**/XCUIElementTypeStaticText[5]")
    private WebElement appointmentStatus;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTable")
    private WebElement appointmentsTable;


    public void tapOnCloseButton() {
        closeButton.click();
    }

    public String getTextCheckInCheckOutTitle() {
        waitUntilVisible(checkInCheckOutTitle, "Unable to display \"Title\" ");
        return checkInCheckOutTitle.getText();
    }

    public String getTextAppointmentLocation() {
        waitUntilVisible(appointmentLocation, "Unable to display \"Location\" name");
        return appointmentLocation.getText();
    }

    public void clickOnAppointmentLocationButton() {
        click(appointmentLocation, "Unable to click on \"Locations\" button");

    }

    public String getTextAppointmentProvider() {
        waitUntilVisible(appointmentProvider, "Unable to display appointment\"Provider\" ");
        return appointmentProvider.getText();
    }

    public String getTextAppointmentsTable(){
        waitUntilVisible(appointmentsTable, "Unable to validate if appointments list is empty");
        return appointmentsTable.getText();
    }

    public String getTextAppointmentStatus(){
        waitUntilVisible(appointmentStatus, "Unable to obtain the text for status \"" + appointmentStatus + "\" ");
        return appointmentStatus.getText();
    }

    public void tapOnAppointmentStatus() {
        click(appointmentStatus, "Unable to tap on \" " + appointmentStatus + "\" ");
    }

}
