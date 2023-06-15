package org.fw.testcases;

import com.microsoft.appcenter.appium.Factory;
import org.fw.components.HamburgerMenuComponent;
import org.fw.components.WelcomeCarrouselComponent;
import org.fw.pages.HomePage;
import org.fw.pages.LoginPage;
import org.fw.pages.PracticeLoginPage;
import org.fw.utils.Constants;
import org.fw.utils.PermissionAlertDialogSettings;
import org.fw.utils.UserDataUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginToAppTest extends BaseTest{

    @Rule
    public TestWatcher watcher = Factory.createWatcher();
    private final LoginPage loginPage = new LoginPage();
    private final HomePage homePage = new HomePage();
    HamburgerMenuComponent hamburgerMenuComponent = new HamburgerMenuComponent();
    PracticeLoginPage practiceLoginPage = new PracticeLoginPage();
    public WelcomeCarrouselComponent welcomeCarrouselComponent = new WelcomeCarrouselComponent();



    @Before
    public void initializeApp() {
        practiceLoginPage.practiceSelection();
    }


    public void userCanNavigateInNewsCarousel() {

        welcomeCarrouselComponent.newsCarousel();
        PermissionAlertDialogSettings.setAllowUseFaceID();

    }

    @Test
    public void userCanSelectPractice() {
        practiceLoginPage.practiceSelection();
        assertTrue(loginPage.isSymIconDisplayed());
        assertEquals(UserDataUtils.getUserData(Constants.PRACTICE), loginPage.getPracticeName());
        assertTrue(loginPage.isForgotPasswordDisplayed());
    }

    @Test
    public void userCanLogIn() {
        practiceLoginPage.practiceSelection();
        loginPage.submitLoginForm(UserDataUtils.getUserData(Constants.USERNAME), UserDataUtils.getUserData(Constants.PASSWORD));
        assertTrue(welcomeCarrouselComponent.isCarrouselTexDisplayed());
        userCanNavigateInNewsCarousel();
        assertTrue(homePage.isSymLogoIconDisplayed());
        homePage.tapOpenHamburgerMenu();
        hamburgerMenuComponent.tapSignOutButton();

    }

    @Test
//    Login  - 81128 - Verify "Forgot password" (Reset Password)
    public void userForgotPassword() {
        loginPage.tapForgotPasswordButton();
        assertTrue(loginPage.getForgotPasswordComp().isComponentDisplayed());
        loginPage.getForgotPasswordComp().submitForgotPasswordForm(UserDataUtils.getUserData(Constants.USERNAME));
        assertTrue(loginPage.isSymIconDisplayed());

    }

    @Test
//    Login - 76829 - Sign Out from Practice App
    public void userCanSignOut() {
        loginPage.submitLoginForm(UserDataUtils.getUserData(Constants.USERNAME), UserDataUtils.getUserData(Constants.PASSWORD));
        userCanNavigateInNewsCarousel();
        homePage.tapOpenHamburgerMenu();
        hamburgerMenuComponent.tapSignOutButton();
        assertTrue(practiceLoginPage.isSymIconDisplayed());
        assertTrue(loginPage.isForgotPasswordDisplayed());

    }


}
