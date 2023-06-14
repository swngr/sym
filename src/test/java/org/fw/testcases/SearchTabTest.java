package org.fw.testcases;

import org.fw.pages.HomePage;
import org.fw.utils.Constants;
import org.fw.utils.CustomTestWatcher;
import org.fw.utils.PermissionAlertDialogSettings;
import org.fw.utils.UserDataUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertTrue;

public class SearchTabTest extends BaseTest {

    @Rule
    public CustomTestWatcher watchman = new CustomTestWatcher();

    private final HomePage homePage = new HomePage();

    private String patientFirstName;
    private String patientLastName;
    private String patientUserName;
    private String patientID;

    @Before
    public void setupLogin() {
        fastLoginSession();
        patientFirstName = UserDataUtils.getUserData(Constants.PATIENT_FIRSTNAME);
        patientLastName = UserDataUtils.getUserData(Constants.PATIENT_LASTNAME);
        patientUserName = patientFirstName + " " + patientLastName;
        patientID = UserDataUtils.getUserData(Constants.PATIENT_ID_JOHN_CONNOR);
    }

//    Test Case 76881: Open Search tab
    @Test
    public void testOpenSearchTab() {
        homePage.getBottonNavComp().tapPatientButton();
        assertTrue(homePage.getSearchComp().isDisplayed());
        homePage.getBottonNavComp().tapHomeButton();
    }

//    Test Case 76882: Search for Patients
    @Test
    public void testSearchForPatients() {
        homePage.getBottonNavComp().tapPatientButton();
        assertTrue(homePage.getSearchComp().searchForPatient(patientID));
        homePage.getSearchComp().clearSearchField();
        homePage.getBottonNavComp().tapHomeButton();
    }

//    Test Case 76889: Ability to open Patient File
    @Test
    public void testAbilityToOpenPatientFile() {
        homePage.getBottonNavComp().tapPatientButton();
        assertTrue(homePage.getSearchComp().openPatientFile(patientID));
        homePage.getBottonNavComp().tapHomeButton();
    }

//    Test Case 76890: Check Patient Search Tips ToolTip
    @Test
    public void testCheckPatientSearchTipsToolTip() {
        homePage.getBottonNavComp().tapPatientButton();
        homePage.getSearchComp().tapPatientHintButton();
        assertTrue(homePage.getSearchComp().getPatientHintComp().isDisplayed());
        homePage.getSearchComp().getPatientHintComp().closeHint();
        homePage.getBottonNavComp().tapHomeButton();
    }

//    Test Case 76891: Create new Patient
    @Test
    public void testCreateNewPatient() {
        homePage.getBottonNavComp().tapPatientButton();

        String firstName = UserDataUtils.randFirstName();
        String lastName = UserDataUtils.randLastName();
        Calendar cal = UserDataUtils.getUserDOB();

        PermissionAlertDialogSettings.setAllowAccessToAllPhotos();
        homePage.getSearchComp().tapAdd();
        homePage.getSearchComp().getPatientProfileComp().submitPatientForm(UserDataUtils.getUserDataPatient(Constants.PATIENT_STATUS),
                firstName, lastName, cal,
                UserDataUtils.getUserDataGender(Constants.PATIENT_GENDER), UserDataUtils.getUserData(Constants.PATIENT_MOBILE_PHONE),
                UserDataUtils.getUserData(Constants.PATIENT_REFERRAL_SOURCE));
        assertTrue(homePage.getSearchComp().getPatientFileComp().isUserSaved(firstName, lastName, cal));

        homePage.getBottonNavComp().tapHomeButton();
    }

//    Test Case 76892: Switch to Provider tab
    @Test
    public void testSwitchToProviderTab() {
        homePage.getBottonNavComp().tapPatientButton();
        homePage.getSearchComp().tapProviderTab();
        assertTrue(homePage.getSearchComp().isProviderTabDisplayed());
        homePage.getSearchComp().tapPatientTab();
        homePage.getBottonNavComp().tapHomeButton();
    }

////    Test Case 76893: Search for Providers
//    @Test
//    public void testSearchForProviders() {
//
//    }
//
////    Test Case 76894: Ability to open Provider File
//    @Test
//    public void testAbilityToOpenProviderFile() {
//
//    }
//
////    Test Case 76895: Check Provider Creation Tool Tip dialog
//    @Test
//    public void testCheckProviderCreationToolTipDialog() {
//
//    }
//
////    Test Case 76896: Switch to Staff tab
//    @Test
//    public void testSwitchToStaffTab() {
//
//    }
//
////    Test Case 76897: Search for Staff
//    @Test
//    public void testSearchForStaff() {
//
//    }
//
////    Test Case 76898: Ability to Open Staff file
//    @Test
//    public void testAbilityToOpenStaffFile() {
//
//    }
//
////    Test Case 76899: Check Staff Creation Tool Tip dialog
//    @Test
//    public void testCheckStaffCreationToolTipDialog() {
//
//    }

}
