package org.fw.pages;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.components.*;
import org.fw.managers.MobileDriverManager;
import org.fw.utils.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PracticeFlowCardComponent extends BasePage {

    @FindBy(name = "\uF156")
    private WebElement closeButton;

    @iOSXCUITFindBy(accessibility = "Open Appointment")
    private WebElement openApptButton;

    @iOSXCUITFindBy(accessibility = "Patient File")
    private WebElement patientFileButton;

    @iOSXCUITFindBy(accessibility = "Media")
    private WebElement mediaButton;

    @iOSXCUITFindBy(accessibility = "New Estimate")
    private WebElement newEstimateButton;

    @iOSXCUITFindBy(accessibility = "New Invoice")
    private WebElement newInvoiceButton;

    @iOSXCUITFindBy(accessibility = "Secure Message")
    private WebElement secureMessageButton;

    private final AppointmentComponent appointmentComponent = new AppointmentComponent();
    private final PatientFileComponent patientFileComponent = new PatientFileComponent();
    private final MediaComponent mediaComponent = new MediaComponent();
    private final EhrNoteComponent ehrNoteComponent = new EhrNoteComponent();
    private final EstimateComponent estimateComponent = new EstimateComponent();
    private final InvoiceComponent invoiceComponent = new InvoiceComponent();
    private final ConversationComponent conversationComponent = new ConversationComponent();

    public boolean isDisplayed(String apptPatientUserName, String apptServiceName){
        WebElement patientName = MobileDriverManager.getMobileDriver().findElementByName(apptPatientUserName);
        WebElement patientServiceName = MobileDriverManager.getMobileDriver().findElementByName(apptServiceName);

        return (isDisplayed(patientName, "Unable to find the patient name") &&
                isDisplayed(patientServiceName, "Unable to find the patient service name") &&
                isDisplayed(openApptButton, "Unable to click the \"openApptButton\" button") &&
                isDisplayed(patientFileButton, "Unable to click the \"patientFileButton\" button") &&
                isDisplayed(mediaButton, "Unable to click the \"mediaButton\" button") &&
                isDisplayed(getEhrNoteButton(), "Unable to click the \"newEhrNoteButton\" button") &&
                isDisplayed(newEstimateButton, "Unable to click the \"newEstimationButton\" button") &&
                isDisplayed(newInvoiceButton, "Unable to click the \"newInvoiceButton\" button") &&
                isDisplayed(secureMessageButton, "Unable to click the \"secureMessageButton\" button"));
    }

    private WebElement getEhrNoteButton() {
        List<WebElement> newEhrNoteButton = MobileDriverManager.getMobileDriver().findElementsByAccessibilityId("New EHR Note");
        if(!newEhrNoteButton.isEmpty()){
            return newEhrNoteButton.get(0);
        }else {
            List<WebElement> vewEhrNoteButton = MobileDriverManager.getMobileDriver().findElementsByAccessibilityId("View EHR Note");
            return vewEhrNoteButton.get(0);
        }
    }

    public void tapClose() {
        click(closeButton, "Unable to click the \"closeButton\" button");
    }

    public void tapAppointment() {
        click(openApptButton, "Unable to click the \"openApptButton\" button");
        WaitUtils.sleep(getClass(), 5);
    }

    public void tapPatientFile() {
        click(patientFileButton, "Unable to click the \"patientFileButton\" button");
    }

    public void tapMedia() {
        click(mediaButton, "Unable to click the \"mediaButton\" button");
    }

    public void tapEhrNote() {
        click(getEhrNoteButton(), "Unable to click the \"newEhrNoteButton\" button");
    }

    public void tapNewEstimate() {
        click(newEstimateButton, "Unable to click the \"newEstimationButton\" button");
    }

    public void tapNewInvoice() {
        click(newInvoiceButton, "Unable to click the \"newInvoiceButton\" button");
    }

    public void tapSecureMessage() {
        click(secureMessageButton, "Unable to click the \"secureMessageButton\" button");
    }

    public AppointmentComponent getAppointmentComponent() {
        return appointmentComponent;
    }

    public PatientFileComponent getPatientFileComponent() {
        return patientFileComponent;
    }

    public MediaComponent getMediaComponent() {
        return mediaComponent;
    }

    public EhrNoteComponent getEhrNoteComponent() {
        return ehrNoteComponent;
    }

    public EstimateComponent getEstimateComponent() {
        return estimateComponent;
    }

    public InvoiceComponent getInvoiceComponent() {
        return invoiceComponent;
    }

    public ConversationComponent getConversationComponent() {
        return conversationComponent;
    }

}
