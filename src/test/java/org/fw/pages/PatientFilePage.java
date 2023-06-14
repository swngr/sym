package org.fw.pages;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.components.PatientFileComponent;
import org.openqa.selenium.WebElement;

public class PatientFilePage extends PatientFileComponent {

    // Navigation bar elements

    @iOSXCUITFindBy(accessibility = "button_nav_bar_back")
    private WebElement backButton;

    @iOSXCUITFindBy(accessibility = "button_sticky_note")
    private WebElement stickyNoteButton;

    @iOSXCUITFindBy(accessibility = "button_edit")
    private WebElement editPatientProfileButton;

    @iOSXCUITFindBy(accessibility = "close_left_nav_bar")
    private WebElement cancelButton;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == \"Patient File\"`]")
    private WebElement patientFileWindowHeader;


    public void tapOnCancelButton() {
        click(cancelButton, "Unable to tap on \"Cancel\" button ");
    }

    public String getTextPatientFileWindowHeader() {
        waitUntilVisible(patientFileWindowHeader, "Unable to display \"Patient File\" window");
        return patientFileWindowHeader.getText();
    }


}
