package org.fw.pages;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class ChoosePatientPage extends BasePage {

//    Task 83634: Patient search list page
    @iOSXCUITFindBy(accessibility = "")
    private WebElement hintButton;

    @iOSXCUITFindBy(accessibility = "")
    private WebElement addNewPatientButton;

    @iOSXCUITFindBy(accessibility = "action_search_users")
    private WebElement searchPatientsInput;

    @iOSXCUITFindBy(accessibility = "user_type_switcher")
    private WebElement patientTab;

    @iOSXCUITFindBy(accessibility = "user_type_switcher")
    private WebElement providerTab;

    @iOSXCUITFindBy(accessibility = "user_type_switcher")
    private WebElement staffTab;

}
