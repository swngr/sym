package org.fw.pages;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.components.AppointmentStatusComponent;
import org.fw.components.EditAppointmentComponent;
import org.fw.managers.MobileDriverManager;
import org.fw.utils.Constants;
import org.fw.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AppointmentComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "Close")
    private WebElement closeButton;
    @iOSXCUITFindBy(accessibility = "Appointment Legal Forms")
    private WebElement aptLegalFormsButton;

    @iOSXCUITFindBy(accessibility = "Patient Intake Forms")
    private WebElement patientIntakeFormsButton;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == \"Appointment\"`]")
    private WebElement appointmentWindowHeader;



    private final AppointmentStatusComponent appointmentStatusComponent = new AppointmentStatusComponent();
    private final EditAppointmentComponent editAppointmentComponent = new EditAppointmentComponent();

    private WebElement getAppointmentsComponent() {
        return waitUntilElementIsVisible(By.xpath("//XCUIElementTypeNavigationBar[@name=\"Appointment\"]/.."));
    }

    public WebElement getNewEhrNoteButton() {
        List<WebElement> invoiceButton = getAppointmentsComponent().findElements(By.name("New EHR Note"));
        if(!invoiceButton.isEmpty()){
            return invoiceButton.get(0);
        }else{
            return getAppointmentsComponent().findElements(By.name("View EHR Note")).get(0);
        }
    }

    public WebElement getNewEstimationButton() {
        return getAppointmentsComponent().findElement(By.name("New Invoice"));
    }

    public WebElement getEditButton() {
        return getAppointmentsComponent().findElement(By.name("Edit"));
    }

    public WebElement getMoreButton() {
        return getAppointmentsComponent().findElement(By.name("More"));
    }

    public WebElement getElementByName(String name) {
        return getAppointmentsComponent().findElement(By.name(name));
    }

    public boolean isDisplayed(String aptUserName, String aptService, Constants.AppointmentStatus aptStatus) {

        WebElement patientName = getElementByName(aptUserName);
        WebElement patientServiceName = getElementByName(aptService);
        WebElement aptStatusName = getStatusElement(aptStatus);

        return (isDisplayed(patientName, "Unable to find the patient name") &&
                isDisplayed(patientServiceName, "Unable to find the patient service name") &&
                isDisplayed(getNewEhrNoteButton(), "Unable to click the \"newEhrNoteButton\" button") &&
                isDisplayed(getNewEstimationButton(), "Unable to click the \"newInvoiceButton\" button") &&
                isDisplayed(getEditButton(), "Unable to click the \"editButton\" button") &&
                isDisplayed(getMoreButton(), "Unable to click the \"moreButton\" button") &&
                isDisplayed(aptStatusName, "Unable to find the appointment status name") &&
                isDisplayed(aptLegalFormsButton, "Unable to click the \"aptLegalFormsButton\" button") &&
                isDisplayed(patientIntakeFormsButton, "Unable to click the \"patientIntakeFormsButton\" button"));
    }

    public void tapClose() {
        click(closeButton, "Unable to click the \"closeButton\" button");
    }

    public String getStatus(Constants.AppointmentStatus aptStatus) {
        return getStatusElement(aptStatus).getText();
    }

    private WebElement getStatusElement(Constants.AppointmentStatus aptStatus) {
        return MobileDriverManager.getMobileDriver().findElement(By.name(aptStatus.getName()));
    }

    public void changeStatus(Constants.AppointmentStatus aptStatusNow, Constants.AppointmentStatus aptStatusLater) {
        click(getStatusElement(aptStatusNow), "Unable to click the \"aptStatusElement\" button");
        appointmentStatusComponent.chooseStatus(aptStatusLater);
    }

    public void tapEditButton() {
        WebElement editButton = getAppointmentsComponent().findElement(By.name("Edit"));
        click(editButton, "Unable to click the \"editButton\" button");
        WaitUtils.sleep(getClass(), 5);
    }

    public void tapOnCloseButton(){
        click(closeButton, "Unable to tap on \"Close\" button ");
    }

    public EditAppointmentComponent getEditAppointmentComponent() {
        return editAppointmentComponent;
    }

    public String getTextAppointmentWindowHeader() {
        waitUntilVisible(appointmentWindowHeader, "Unable to display \"Appointment\" window");
        return appointmentWindowHeader.getText();
    }

}
