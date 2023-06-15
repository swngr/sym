package org.fw.testcases;

import org.fw.managers.LoggingManager;
import org.fw.managers.MobileDriverManager;
import org.fw.pages.HomePage;
import org.fw.pages.LoginPage;
import org.fw.pages.PracticeLoginPage;
import org.fw.utils.Constants;
import org.fw.utils.PermissionAlertDialogSettings;
import org.fw.utils.UserDataUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BaseTest {

    @BeforeClass
    public static void setUp() {
        LoggingManager.logInfo(BaseTest.class, "Setting up MobileDriver");
        setupMobileDriver();
    }

    public static void setupMobileDriver() {
        if (!MobileDriverManager.isDriverSessionActive()) {
            MobileDriverManager.createMobileDriver();
            LoggingManager.logInfo(BaseTest.class, "Opened an application");
        }
    }

    @AfterClass
    public static void tearDown() {
        tearDownMobileDriver();
    }

    public static void tearDownMobileDriver() {
        if (MobileDriverManager.isDriverSessionActive()) {
            MobileDriverManager.tearDownDriver();
        }
    }

//    public static void label(String label) {
//        if (PropertyUtils.isPlatform(Platform.AZURE_ANDROID) || PropertyUtils.isPlatform(Platform.IOS_AZURE)) {
//            EnhancedAndroidDriver<WebElement> driver = (EnhancedAndroidDriver<WebElement>) MobileDriverManager.getMobileDriver();
//
//            driver.label(label);
//        }
//    }

    private final PracticeLoginPage practiceLoginPage = new PracticeLoginPage();
    private final LoginPage loginPage = new LoginPage();
    private final HomePage homePage = new HomePage();

    public void fastLoginSession() {
        // Select Practice
        practiceLoginPage.submitPracticeForm(UserDataUtils.getUserData(Constants.PRACTICE));
        // Login form
        loginPage.submitLoginForm(UserDataUtils.getUserData(Constants.USERNAME), UserDataUtils.getUserData(Constants.PASSWORD));
        // Carrousel welcome
        homePage.getWelcomeCarrouselComp().closeWelcomeCarrousel();
        // Dismiss face id message
        PermissionAlertDialogSettings.setAllowUseFaceID();
    }

    public void fastSignoutSession() {
        // Signout
        homePage.signOut();
        if (!MobileDriverManager.isReleaseVersion()) {
            assertTrue(practiceLoginPage.isSymIconDisplayed());
            assertTrue(practiceLoginPage.isProceedLoginButtonDisplayed());
        } else {
            assertTrue(loginPage.isSymIconDisplayed());
            assertEquals(UserDataUtils.getUserData(Constants.PRACTICE), loginPage.getPracticeName());
            assertTrue(loginPage.isForgotPasswordDisplayed());
        }
    }
}
