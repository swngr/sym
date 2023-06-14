package org.fw.pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.components.PatientFileComponent;
import org.fw.components.PatientHintComponent;
import org.fw.components.PatientProfileComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchComponent extends BasePage {

    // NavBar Element

    @iOSXCUITFindBy(accessibility = "More Info")
    private WebElement moreInfoButton;

    @iOSXCUITFindBy(accessibility = "Add")
    private WebElement addButton;

    @iOSXCUITFindBy(accessibility = "action_search_users")
    private WebElement searchInput;

    // Tab Elements

    @iOSXCUITFindBy(accessibility = "Patient")
    private WebElement patientTab;

    @iOSXCUITFindBy(accessibility = "Provider")
    private WebElement providerTab;

    @iOSXCUITFindBy(accessibility = "Staff")
    private WebElement staffTab;

    // List Elements


    // Components

    private final PatientHintComponent patientHintComp = new PatientHintComponent();
    private final PatientFileComponent patientFileComp = new PatientFileComponent();
    private final PatientProfileComponent patientProfileComp = new PatientProfileComponent();

    // Methods

    public boolean isDisplayed() {
        return (moreInfoButton.isDisplayed() &&
                addButton.isDisplayed() &&
                searchInput.isDisplayed() &&
                patientTab.isDisplayed() &&
                providerTab.isDisplayed() &&
                staffTab.isDisplayed());
    }

    public boolean searchForPatient(String patientId) {
        sendKeys(searchInput, patientId, "Unable to send keys to the 'Search' input");
        hideKeyboard();
        return isDisplayed(findPatientById(patientId), "Unable to find patient with ID: " + patientId);
    }

    public void clearSearchField(){
        clear(searchInput, "Unable to clear the 'Search' input");
    }

    private WebElement findPatientById(String patientId) {
        return waitUntilElementIsVisible(MobileBy.AccessibilityId("cell_patient_id_" + patientId));
    }

    public boolean openPatientFile(String patientId) {
        sendKeys(searchInput, patientId, "Unable to send keys to the 'Search' input");
        click(findPatientById(patientId), "Unable to click on patient with ID: " + patientId);
        return patientFileComp.isDisplayed();
    }

    public PatientFileComponent getPatientFileComp() {
        return patientFileComp;
    }

    public PatientHintComponent getPatientHintComp() {
        return patientHintComp;
    }

    public void tapPatientHintButton() {
        click(moreInfoButton, "Unable to click on the 'More Info' button");
    }

    public void tapAdd() {
        click(addButton, "Unable to click on the 'Add' button");
    }

    public PatientProfileComponent getPatientProfileComp() {
        return patientProfileComp;
    }

    public void tapPatientTab() {
        click(patientTab, "Unable to click on the 'Patient' tab");
    }

    public void tapProviderTab() {
        click(providerTab, "Unable to click on the 'Provider' tab");
    }

    public void tapStaffTab() {
        click(staffTab, "Unable to click on the 'Staff' tab");
    }

    public boolean isProviderTabDisplayed() {
        return (providerTab.getAttribute("value").equals("1") &&
                isDisplayed(getPageTitle("Provider"), "Unable to find the 'Provider' page title"));
    }

    private WebElement getPageTitle(String pageTitle) {
        return waitUntilElementIsVisible(By.name("Choose " + pageTitle));
    }

}
