package org.fw.components;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.pages.BasePage;
import org.openqa.selenium.WebElement;

import java.util.Calendar;

public class PatientFileComponent extends BasePage {

    // Navigation bar elements

    @iOSXCUITFindBy(accessibility = "close_left_nav_bar")
    private WebElement closeButton;

    @iOSXCUITFindBy(accessibility = "button_sticky_note")
    private WebElement stickyNoteButton;

    @iOSXCUITFindBy(accessibility = "button_edit")
    private WebElement editPatientProfileButton;

    // Patient data tab_1 elements

    @iOSXCUITFindBy(accessibility = "button_patient_info")
    private WebElement panelInfoButton;

    @iOSXCUITFindBy(accessibility = "image_patient_avatar")
    private WebElement patientImage;

    @iOSXCUITFindBy(accessibility = "label_patient_name")
    private WebElement patientName;

    @iOSXCUITFindBy(accessibility = "label_patient_info")
    private WebElement patientData;

    @iOSXCUITFindBy(accessibility = "label_patient_id")
    private WebElement patientId;

    // Patient data tab_2 elements

    @iOSXCUITFindBy(accessibility = "label_patient_life_cycle")
    private WebElement patientLifeCycleStatusMessage;

    @iOSXCUITFindBy(accessibility = "button_retail_status")
    private WebElement prospect1;

    @iOSXCUITFindBy(accessibility = "button_non_surgical_status")
    private WebElement prospect2;

    @iOSXCUITFindBy(accessibility = "button_surgical_status")
    private WebElement prospect3;

    // Patient actions tab_1 icons

    @iOSXCUITFindBy(accessibility = "button_patient_chat")
    private WebElement secureMessage;

    @iOSXCUITFindBy(accessibility = "patient_videoVisit")
    private WebElement meetNow;

    @iOSXCUITFindBy(accessibility = "button_patient_patientApp")
    private WebElement patientApp;

    @iOSXCUITFindBy(accessibility = "button_patient_intakeStatus")
    private WebElement intakeStatus;

    @iOSXCUITFindBy(accessibility = "button_toggle_left")
    private WebElement arrowMoreItemsButton;

    // Patient actions tab_2 icons

    @iOSXCUITFindBy(accessibility = "button_patient_call")
    private WebElement callButton;

    @iOSXCUITFindBy(accessibility = "button_patient_sms")
    private WebElement textMessageButton;

    @iOSXCUITFindBy(accessibility = "button_patient_email")
    private WebElement emailButton;

    @iOSXCUITFindBy(accessibility = "button_toggle_right")
    private WebElement arrowGoBackItemsButton;

    @iOSXCUITFindBy(accessibility = "cell_title_Activity Notes")
    private WebElement activityNotes;

    @iOSXCUITFindBy(accessibility = "cell_title_Appointments")
    private WebElement appointments;

    @iOSXCUITFindBy(accessibility = "cell_title_Conversations")
    private WebElement conversations;

    @iOSXCUITFindBy(accessibility = "cell_title_Documents")
    private WebElement documents;

    @iOSXCUITFindBy(accessibility = "cell_title_Legal Forms")
    private WebElement legalForms;

    @iOSXCUITFindBy(accessibility = "cell_title_Media")
    private WebElement media;

    @iOSXCUITFindBy(accessibility = "cell_title_EHR Notes")
    private WebElement ehrNotes;

    public boolean isUserSaved(String firstName, String lastName, Calendar cal) {
        waitUntilVisible(patientName, "Unable to show the \"patientName\" element");
        return (isDisplayed(getPageTitleElement(), "Unable to display the \"Patient App Invitation\" message") &&
                patientName.getText().contains(firstName) &&
                patientName.getText().contains(lastName) &&
                patientData.getText().contains(String.valueOf(cal.get(Calendar.DAY_OF_MONTH))) &&
                patientData.getText().contains(String.valueOf(cal.get(Calendar.YEAR))));
    }

    public boolean isDisplayed() {
        waitUntilVisible(activityNotes, "Unable to show the \"activityNotes\" element");
        return (isDisplayed(activityNotes, "Unable to display the \"Activity Notes\" element") &&
                isDisplayed(appointments, "Unable to display the \"Appointments\" element") &&
                isDisplayed(conversations, "Unable to display the \"Conversations\" element") &&
                isDisplayed(documents, "Unable to display the \"Documents\" element") &&
                isDisplayed(legalForms, "Unable to display the \"Legal Forms\" element") &&
                isDisplayed(media, "Unable to display the \"Media\" element") &&
                isDisplayed(ehrNotes, "Unable to display the \"EHR Notes\" element"));
    }

    public void tapClose() {
        click(closeButton, "Unable to tap the \"closeButton\" element");
    }

}
