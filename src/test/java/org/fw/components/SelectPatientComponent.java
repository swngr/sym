package org.fw.components;

import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.pages.BasePage;
import org.fw.utils.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SelectPatientComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "cancel")
    private WebElement cancelButton;

    @iOSXCUITFindBy(accessibility = "Save")
    private WebElement saveButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Practice-Dev\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeOther/XCUIElementTypeTextField")
    private WebElement patientInput;

    @FindBy(xpath = "//XCUIElementTypeApplication[@name=\"Practice-Dev\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeTable")
    private WebElement patientList;

    public void tapCancelButton(){
        click(cancelButton, "Unable to click the \"Cancel\" button");
    }

    public void tapSaveButton(){
        click(saveButton, "Unable to click the \"Save\" button");
    }

    public void submitPatientForm(String patientName) {
        WaitUtils.sleep(getClass(), 5);
        sendKeys(patientInput, patientName, "Unable to send values to the \"Patient Name\" input");
        tapPatientSearched(patientName);
        tapSaveButton();
    }

    private void tapPatientSearched(String patientName) {
        List<WebElement> cells = patientList.findElements(MobileBy.iOSClassChain("XCUIElementTypeCell"));
        for(WebElement cellElement : cells){
            String name = cellElement.findElements(MobileBy.iOSClassChain("XCUIElementTypeStaticText")).get(1).getText();
            if(name.contains(patientName)){
                click(cellElement, "Unable to click the \""+patientName+"\" element");
                return;
            }
        }
    }

}
