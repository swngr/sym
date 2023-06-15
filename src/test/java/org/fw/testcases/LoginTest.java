package org.fw.testcases;

import org.fw.managers.MobileDriverManager;
import org.fw.pages.HomePage;
import org.fw.pages.LoginPage;
import org.fw.pages.PracticeLoginPage;
import org.fw.utils.*;
import org.junit.Rule;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Rule
    public CustomTestWatcher watchman = new CustomTestWatcher();

    private final PracticeLoginPage practiceLoginPage = new PracticeLoginPage();
    private final LoginPage loginPage = new LoginPage();
    private final HomePage homePage = new HomePage();

    @Test
//    Login - 76827 - Select Environment (Practice)
    public void testPracticeLoginTestCase() {
        // Select Practice
        practiceLoginPage.submitPracticeForm(UserDataUtils.getUserData(Constants.PRACTICE));
        assertTrue(loginPage.isSymIconDisplayed());
        assertEquals(UserDataUtils.getUserData(Constants.PRACTICE), loginPage.getPracticeName());
        assertTrue(loginPage.isForgotPasswordDisplayed());
    }

    @Test
//    Login - 76828 - Login to the Practice App
    public void testLoginTestCase(){
        // Login
        practiceLoginPage.submitPracticeForm(UserDataUtils.getUserData(Constants.PRACTICE));
        // Login form
        loginPage.submitLoginForm(UserDataUtils.getUserData(Constants.USERNAME), UserDataUtils.getUserData(Constants.PASSWORD));
        // Carrousel welcome
        homePage.getWelcomeCarrouselComp().closeWelcomeCarrousel();
        // Dismiss face id message
        PermissionAlertDialogSettings.setAllowUseFaceID();
        // Verify home page
        assertTrue(homePage.isHamburgerMenuDisplayed());
    }

    @Test
//    Login - 76829 - Sign Out from Practice App
    public void testSignOut(){
        fastLoginSession();
        // Signout
        homePage.signOut();
        if(!MobileDriverManager.isReleaseVersion()) {
            assertTrue(practiceLoginPage.isSymIconDisplayed());
            assertTrue(practiceLoginPage.isProceedLoginButtonDisplayed());
        }else {
            assertTrue(loginPage.isSymIconDisplayed());
            assertEquals(UserDataUtils.getUserData(Constants.PRACTICE), loginPage.getPracticeName());
            assertTrue(loginPage.isForgotPasswordDisplayed());
        }
    }

    @Test
//    Login  - 81128 - Verify "Forgot password" (Reset Password)
    public void testForgotPassword() {
        practiceLoginPage.submitPracticeForm(UserDataUtils.getUserData(Constants.PRACTICE));
        loginPage.tapForgotPasswordButton();
        assertTrue(loginPage.getForgotPasswordComp().isComponentDisplayed());
        loginPage.getForgotPasswordComp().submitForgotPasswordForm(UserDataUtils.getUserData(Constants.USERNAME));
        assertTrue(loginPage.isSymIconDisplayed());
    }

}
