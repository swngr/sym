package org.fw.components;

import org.fw.managers.MobileDriverManager;
import org.fw.pages.BasePage;
import org.fw.utils.Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AppointmentMultiStatusComponent extends BasePage {

    @FindBy(name = "  Save  ")
    private WebElement saveButton;

    public void selectAppointmentStatus(Constants.AppointmentStatus appointmentStatus) {
        WebElement statusButton = MobileDriverManager.getMobileDriver().findElementByAccessibilityId(appointmentStatus.getAllFirstLettersUpperCase());
        click(statusButton, "Unable to click the '" + appointmentStatus.getName() + "' button");
        clear(saveButton, "Unable to clear the \"saveButton\" button");
    }

}