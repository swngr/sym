package org.fw.pages;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.components.*;
import org.fw.utils.PermissionAlertDialogSettings;
import org.fw.utils.WaitUtils;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    @iOSXCUITFindBy(accessibility = "toast_view")
    private WebElement dismissUpdateMessageView;

    @iOSXCUITFindBy(accessibility = "dismiss_toast")
    private WebElement dismissUpdateMessageButton;

    // Top Bar buttons

    @iOSXCUITFindBy(accessibility = "button_side_menu")
    private WebElement openHamburgerMenuButton;

    @iOSXCUITFindBy(accessibility = "image_logg_nav_bar")
    private WebElement symplastLogoIcon;

    @iOSXCUITFindBy(accessibility = "button_dnd_nav_bar")
    private WebElement openDoNotDisturbButton;

    @iOSXCUITFindBy(accessibility = "button_add_nav_bar")
    private WebElement addPatientButton;

    // Practice Flow

    @iOSXCUITFindBy(accessibility = "image_Practice Flow")
    private WebElement practiceFlowImageButton;

    // Message

    @iOSXCUITFindBy(accessibility = "cell_Secure Messaging")
    private WebElement secureMessagingButton;

    @iOSXCUITFindBy(accessibility = "label_notification_Secure Messaging")
    private WebElement secureMessagingNotification;

    // Calendar

    @iOSXCUITFindBy(accessibility = "action_fav_Calendar")
    private WebElement favoritedCalendarButton;

    @iOSXCUITFindBy(accessibility = "cell_Calendar")
    private WebElement calendarText;


    // Page Components

    private final WelcomeCarrouselComponent welcomeCarrouselComp = new WelcomeCarrouselComponent();
    private final DoNotDisturbComponent doNotDisturbComp = new DoNotDisturbComponent();
    private final HamburgerMenuComponent hamburgerMenuComp = new HamburgerMenuComponent();
    private final BottonNavigatorComponent bottonNavigatorComp = new BottonNavigatorComponent();
    private final PatientProfileComponent patientProfileComponent = new PatientProfileComponent();
    private final SearchComponent searchComponent = new SearchComponent();

    // Login Welcome Carrousel Actions

    // Close Dismiss (Only available in virtual devices)
    public void closeDismissUpdateMessage(){
        click(dismissUpdateMessageView, "Unable to click the dismiss message Button");
        goBackToSymplastApp();
    }

    public WelcomeCarrouselComponent getWelcomeCarrouselComp(){
        return welcomeCarrouselComp;
    }

    // Hamburger Actions

    public void tapOpenHamburgerMenu() {
        click(openHamburgerMenuButton, "Unable to click the \"Hamburger Menu\" button");
    }

    public HamburgerMenuComponent getHamburgerMenuComp(){
        return hamburgerMenuComp;
    }

    public void signOut() {
        tapOpenHamburgerMenu();
        hamburgerMenuComp.tapSignOutButton();
    }

    // Do Not Disturb Actions

    public void tapOpenDoNotDisturbComp(){
        click(openDoNotDisturbButton, "Unable to click the \"Do Not Disturb\" button");
    }

    public DoNotDisturbComponent getDoNotDisturbComp() {
        return doNotDisturbComp;
    }

    // Add new patient component

    public void tapOpenNewPatientComp() {
        click(addPatientButton, "Unable to click the \"Patient Profile\" button");
        PermissionAlertDialogSettings.setAllowAccessToAllPhotos();
    }

    public PatientProfileComponent getPatientProfileComponent(){
        return patientProfileComponent;
    }

    // Home Page Actions

    public BottonNavigatorComponent getBottonNavComp() {
        return bottonNavigatorComp;
    }

    public void tapSecureMessagingButton(){
        click(secureMessagingButton, "Unable to click the \"Secure Message\" button");
    }

    public boolean isCalendarFavorited() {
        return isElementFavorited(favoritedCalendarButton, "Unable to is selected \"Favorite\" button");
    }

    public void tapFavoriteCalendar() {
        click(favoritedCalendarButton, "Unable to click the \"Favorited Calendar\" button");
        WaitUtils.sleep(getClass(), 3);
    }

    public boolean isSecureMessagingNotified() {
        return isDisplayedCustomTime(secureMessagingNotification, 20, "Unable to display the \"secureMessagingNotification\" element");
    }

    public boolean isHamburgerMenuDisplayed(){
        waitUntilVisible(openHamburgerMenuButton, "Unable to display \"hamburger menu\"");
        return openHamburgerMenuButton.isDisplayed();
    }

//    public void tapPracticeFlowImage() {
//        click(practiceFlowImageButton, "Unable to click the \"practiceFlowImageButton\" button");
//    }

    public void tapPracticeFlowImage() {
        click(practiceFlowImageButton, "Unable to click the \"practiceFlowImageButton\" button");
    }

    public boolean isSymplastLogoIconDisplayed() {
        return isDisplayed(symplastLogoIcon, " \"Symplast logo icon\" is not displayed");
    }

    public SearchComponent getSearchComp() {
        return searchComponent;
    }

}
