package org.fw.testcases;

import org.fw.pages.HomePage;
import org.fw.pages.PatientFilePage;
import org.fw.utils.Constants;
import org.fw.utils.CustomTestWatcher;
import org.fw.utils.PermissionAlertDialogSettings;
import org.fw.utils.UserDataUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class HomeScreenTest extends BaseTest {

    @Rule
    public CustomTestWatcher watchman = new CustomTestWatcher();

    private final HomePage homePage = new HomePage();
    private final PatientFilePage patientFilePage = new PatientFilePage();

    @Before
    public void setupLogin() {
        fastLoginSession();
    }

    @Test
//    Home - 76830 - Open Left Side (Hamburger) Menu
    public void testHomeScreenHamburguerMenu(){
        homePage.tapOpenHamburgerMenu();
        assertEquals(UserDataUtils.getUserData(Constants.PRACTICE), homePage.getHamburgerMenuComp().getPracticeNameLoggedIn());
        assertEquals("Quality Assurance Automation Webapp", homePage.getHamburgerMenuComp().getUserNameLoggedIn());
        homePage.getHamburgerMenuComp().tapCloseHamburgerMenu();
    }

    @Test
//    Home - 76831 - Add a Favorite Through (Hamburguer)
    public void testAddFavoriteCalendarThroughHamburguer(){
        homePage.tapOpenHamburgerMenu();
        homePage.getHamburgerMenuComp().tapFavoriteCalendar();
        assertTrue(homePage.getHamburgerMenuComp().isCalendarFavorited());
        homePage.getHamburgerMenuComp().tapCloseHamburgerMenu();
        assertTrue(homePage.isCalendarFavorited());
        homePage.tapFavoriteCalendar();
    }

    @Test
//    Home - 76832 - Remove a Favorite Through (Hamburguer)
    public void testRemoveFavoriteCalendarThroughHamburguer(){
        homePage.tapFavoriteCalendar();
        homePage.tapOpenHamburgerMenu();
        homePage.getHamburgerMenuComp().tapFavoriteCalendar();
        assertFalse(homePage.getHamburgerMenuComp().isCalendarFavorited());
        homePage.getHamburgerMenuComp().tapCloseHamburgerMenu();
        assertFalse(homePage.isCalendarFavorited());
    }

    @Test
//    Home - 82951 - Add a Favorite Through (Home)
    public void testAddFavoriteCalendarThroughHome(){
        homePage.tapFavoriteCalendar();
        assertTrue(homePage.isCalendarFavorited());
        homePage.tapOpenHamburgerMenu();
        assertTrue(homePage.getHamburgerMenuComp().isCalendarFavorited());
        homePage.getHamburgerMenuComp().tapCloseHamburgerMenu();
        homePage.tapFavoriteCalendar();
    }

    @Test
//    Home - 82952 - Remove a Favorite Through (Home)
    public void testRemoveFavoriteCalendarThroughHome(){
        // First favorite
        homePage.tapFavoriteCalendar();
        // Remove favorite
        homePage.tapFavoriteCalendar();
        assertFalse(homePage.isCalendarFavorited());
        homePage.tapOpenHamburgerMenu();
        assertFalse(homePage.getHamburgerMenuComp().isCalendarFavorited());
        homePage.getHamburgerMenuComp().tapCloseHamburgerMenu();
    }

    @Test
//    Home - Test Case 76833: Disable "My Notifications" via DND
    public void testDisableNotification(){
        homePage.tapOpenDoNotDisturbComp();
        assertTrue(homePage.getDoNotDisturbComp().isDoNotDisturbCompDisplayed());
        homePage.getDoNotDisturbComp().tapEnableDisableButton();
        assertFalse(homePage.getDoNotDisturbComp().isEnableDisableButtonSelected());
        homePage.getDoNotDisturbComp().tapApplyChanges();
        // Enable notifications again
        homePage.tapOpenDoNotDisturbComp();
        assertTrue(homePage.getDoNotDisturbComp().isDoNotDisturbCompDisplayed());
        homePage.getDoNotDisturbComp().tapEnableDisableButton();
        assertTrue(homePage.getDoNotDisturbComp().isEnableDisableButtonSelected());
        homePage.getDoNotDisturbComp().tapApplyChanges();
    }

    @Test
//    Login - 76834 - Create new Patient via "+" button
    public void testCreateNewPatient(){
        String firstName = UserDataUtils.randFirstName();
        String lastName = UserDataUtils.randLastName();
        Calendar cal = UserDataUtils.getUserDOB();

        PermissionAlertDialogSettings.setAllowAccessToAllPhotos();
        homePage.tapOpenNewPatientComp();
        homePage.getPatientProfileComponent().submitPatientForm(UserDataUtils.getUserDataPatient(Constants.PATIENT_STATUS),
                firstName, lastName, cal,
                UserDataUtils.getUserDataGender(Constants.PATIENT_GENDER), UserDataUtils.getUserData(Constants.PATIENT_MOBILE_PHONE),
                UserDataUtils.getUserData(Constants.PATIENT_REFERRAL_SOURCE));
        try {
            assertTrue(patientFilePage.isUserSaved(firstName, lastName, cal));
        }catch (AssertionError e){
            throw new AssertionError(e.getMessage());
        }finally {
            homePage.getBottonNavComp().tapHomeButton();
        }
    }

    @After
    public void fastLogoutSession() {
        fastSignoutSession();
    }

}
