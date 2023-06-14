package org.fw.components;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.pages.BasePage;
import org.fw.utils.Constants;
import org.openqa.selenium.WebElement;

import java.util.Calendar;

public class PatientProfileComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "button_cancel")
    private WebElement cancelButton;

    @iOSXCUITFindBy(accessibility = "button_save")
    private WebElement savePatientButton;

    @iOSXCUITFindBy(accessibility = "button_camera")
    private WebElement cameraButton;

    @iOSXCUITFindBy(accessibility = "image_profile")
    private WebElement profilePicture;

    @iOSXCUITFindBy(accessibility = "cell_status")
    private WebElement patientStatusButton;

    @iOSXCUITFindBy(accessibility = "cell_firstName")
    private WebElement firstNameInput;

    @iOSXCUITFindBy(accessibility = "cell_lastName")
    private WebElement lastNameInput;

    @iOSXCUITFindBy(accessibility = "cell_prefix")
    private WebElement prefixButton;

    @iOSXCUITFindBy(accessibility = "cell_dob")
    private WebElement dateOfBirthInput;

    @iOSXCUITFindBy(accessibility = "cell_gender")
    private WebElement genderInput;

    @iOSXCUITFindBy(accessibility = "cell_mobile")
    private WebElement mobilePhoneInput;

    @iOSXCUITFindBy(accessibility = "cell_email")
    private WebElement emailInput;

    @iOSXCUITFindBy(accessibility = "cell_referralSource")
    private WebElement referralResourceInput;

    private final PatientStatusComponent patientStatusComponent = new PatientStatusComponent();

    private final DateOfBirthComponent dateOfBirthComponent = new DateOfBirthComponent();

    private final GenderComponent genderComponent = new GenderComponent();

    private final ReferralSourceComponent referralSourceComponent = new ReferralSourceComponent();

    public void submitPatientForm(Constants.PatientStatus patientStatus, String patientFirstname, String patientLastname, Calendar cal, Constants.Gender patientGender, String patientMobilePhone, String patientReferralSource) {
        click(patientStatusButton, "Unable to click the \"Patient Status\" button");
        patientStatusComponent.submitPatientStatusComponent(patientStatus);
        sendKeys(firstNameInput, patientFirstname, "Unable to send key to the \"First Name\" field");
        sendKeys(lastNameInput, patientLastname, "Unable to send key to the \"Last Name\" field");
        hideKeyboard();
        click(dateOfBirthInput, "Unable to click the \"Date of Birth\" button");
        dateOfBirthComponent.submitDobForm(cal);
        click(genderInput, "Unable to click the \"Gender\" button");
        genderComponent.submitGenderForm(patientGender);
        sendKeys(mobilePhoneInput, patientMobilePhone, "Unable to send key to the \"Mobile Phone\" field");
        swipe(Constants.Direction.UP);
        click(referralResourceInput, "Unable to click the \"Referral Source\" button");
        referralSourceComponent.submitReferralSource(patientReferralSource);
        click(savePatientButton, "Unable to click the \"Save Patient\" button");
    }

}
