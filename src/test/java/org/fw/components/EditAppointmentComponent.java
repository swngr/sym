package org.fw.components;

import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.managers.MobileDriverManager;
import org.fw.pages.BasePage;
import org.fw.utils.Constants;
import org.fw.utils.WaitUtils;
import org.openqa.selenium.WebElement;

public class EditAppointmentComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "Cancel")
    private WebElement cancelButton;

    @iOSXCUITFindBy(accessibility = "Save")
    private WebElement saveButton;

    @iOSXCUITFindBy(accessibility = "Location*")
    private WebElement locationLabel;

    public void chooseLocation(Constants.AppointmentLocation location){
        click(locationLabel, "Unable to click the \"Location\" label");
        WebElement optLocation = MobileDriverManager.getMobileDriver().findElement(MobileBy.AccessibilityId(location.getName()));
        click(optLocation, "Unable to click the \"" + location.getName() + "\" button");
        WaitUtils.sleep(getClass(), 5);
    }

    public void tapSaveButton() {
        click(saveButton, "Unable to click the \"saveButton\" button");
    }

}
