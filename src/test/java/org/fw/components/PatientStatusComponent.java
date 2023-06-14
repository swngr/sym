package org.fw.components;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.managers.MobileDriverManager;
import org.fw.pages.BasePage;
import org.fw.utils.Constants;
import org.openqa.selenium.WebElement;

public class PatientStatusComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "Patient Profile")
    private WebElement patientProfileBackButton;

    public void submitPatientStatusComponent(Constants.PatientStatus patientStatus){
        WebElement cellElement = MobileDriverManager.getMobileDriver().findElementByName(patientStatus.getName());
        if (cellElement.getText().equals(patientStatus.getName())){
            click(patientProfileBackButton, "Unable to click to the \"Back\" button");
        }else{
            click(cellElement, "Unable to click to the \""+patientStatus.getName()+"\" element");
        }
    }

}
