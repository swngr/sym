package org.fw.components;

import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.managers.MobileDriverManager;
import org.fw.pages.BasePage;
import org.fw.utils.ApiCalls;
import org.fw.utils.Constants;
import org.fw.utils.UserDataUtils;
import org.fw.utils.WaitUtils;
import org.openqa.selenium.WebElement;

public class AppointmentStatusComponent extends BasePage {

    private static String appointmentUserName;
    private static String aptService;
    ApiCalls apiCalls = new ApiCalls();

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeScrollView[1]")
    private WebElement apptEmergentWindow;

    public void chooseStatus(Constants.AppointmentStatus status){
        WebElement componentElement = MobileDriverManager.getMobileDriver().findElementByXPath("//XCUIElementTypeStaticText[@name=\"Appt. Status\"]/../..");
        WebElement optStatus = componentElement.findElement(MobileBy.AccessibilityId(status.getFirstLetterUpperCase()));
        click(optStatus, "Unable to click the \"" + status.getName() + "\" button");
        WaitUtils.sleep(getClass(), 5);
    }

    public void createApptWithStatusAndLocation(String appStatus, String appLocation) {
        appointmentUserName = UserDataUtils.getUserData(Constants.PATIENT_FIRSTNAME_TONY) + " " +
                UserDataUtils.getUserData(Constants.PATIENT_LASTNAME_STARK);
        aptService = "Cosmetic Surgery - Automation NonSurgical";
        apiCalls.createAppointmentWithStatusAndLocation(appStatus, appLocation);
    }

    public Boolean isEmergentWindowDisplayed() {
        return isDisplayed(apptEmergentWindow, "Unable to display \"emergent\" window");
    }

    public void tapOnEmergentAppointmentOption(String apptOption) {
        waitUntilVisible(apptEmergentWindow, "Option \"" + apptOption + "\" is not showing");
        click(MobileDriverManager.getMobileDriver().findElement(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`label == \"" + apptOption + "\"`]")),"Unable to click on: \" " + apptOption + "\" ");
    }

}
