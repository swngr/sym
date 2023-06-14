package org.fw.testcases;

import org.fw.components.HamburgerMenuComponent;
import org.fw.components.WelcomeCarrouselComponent;
import org.fw.pages.*;
import org.fw.utils.Constants;
import org.fw.utils.CustomTestWatcher;
import org.fw.utils.UserDataUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ReportsTest extends BaseTest{

    @Rule
    public CustomTestWatcher watchman = new CustomTestWatcher();
    private final HomePage homePage = new HomePage();
    HamburgerMenuComponent hamburgerMenuComponent = new HamburgerMenuComponent();
    PracticeLoginPage practiceLoginPage = new PracticeLoginPage();
    LoginPage loginPage = new LoginPage();
    WelcomeCarrouselComponent welcomeCarrouselComponent = new WelcomeCarrouselComponent();
    ReportsPage reportsPage = new ReportsPage();

    @Before
    public void testNavigateToReportsSection() {
        practiceLoginPage.practiceSelection();
        loginPage.submitLoginForm(UserDataUtils.getUserData(Constants.USERNAME), UserDataUtils.getUserData(Constants.PASSWORD));
        welcomeCarrouselComponent.newsCarousel();
        homePage.tapOpenHamburgerMenu();
        hamburgerMenuComponent.tapOnReportsButton();

    }
    //TEST CASE 76873
    @Test
    public void testOpenReport() {
        assertTrue(reportsPage.isReportsDashboardDisplayed());
    }
}
