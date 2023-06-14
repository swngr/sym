package org.fw.components;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.pages.BasePage;
import org.fw.utils.WaitUtils;
import org.openqa.selenium.WebElement;

public class HamburgerMenuComponent extends BasePage {

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"side menu disclosure\"`][2]")
    private WebElement calendarDropdown;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == \"Check-In Patient\"`][1]")
    private WebElement checkInPatientButton;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == \"Check-Out Patient\"`][1]")
    private WebElement checkOutPatientButton;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == \"Check In\"`]")
    private WebElement checkInScreen;

    @iOSXCUITFindBy(accessibility = "button_close_menu")
    private WebElement closeHMButton;

    @iOSXCUITFindBy(accessibility = "label_tenant_name")
    private WebElement practiceName;

    @iOSXCUITFindBy(accessibility = "label_profile")
    private WebElement userName;

    @iOSXCUITFindBy(accessibility = "cell_menu_favorite_Calendar")
    private WebElement favoriteCalendarButton;

    @iOSXCUITFindBy(accessibility = "cell_menu_title_Practice Flow")
    private WebElement practiceFlowButton;

    @iOSXCUITFindBy(accessibility = "sign_out")
    private WebElement signOutButton;

    @iOSXCUITFindBy(accessibility = "cell_menu_title_Reports")
    private WebElement reportsButton;

    public void tapCloseHamburgerMenu() {
        click(closeHMButton, "Unable to click the \"Close Hamburger Menu\" button");
    }

    public void tapCalendarDropdown() {
        click(calendarDropdown, "Unable to click the \"Calendar\" dropdown");
    }

    public void tapCheckInPatientButton() {
        click(checkInPatientButton, "Unable to click the \"Close Hamburguer Menu\" button");
    }

    public void tapCheckOutPatientButton() {
        click(checkOutPatientButton, "Unable to click the \"Close Hamburguer Menu\" button");
    }

    public String getPracticeNameLoggedIn(){
        return getText(practiceName, "Unable to get text from \"Practice Name\" field");
    }

    public String getUserNameLoggedIn(){
        return getText(userName, "Unable to get text from \"User Name\" field");
    }

    public void tapFavoriteCalendar() {
        click(favoriteCalendarButton, "Unable to click the \"Favorite Calendar\" button");
        WaitUtils.sleep(getClass(), 3);
    }

    public boolean isCalendarFavorited() {
        return isElementFavorited(favoriteCalendarButton, "Unable to is selected \"Favorite\" button");
    }

    public void tapPracticeFlow(){
        click(practiceFlowButton, "Unable to click the \"practiceFlowButton\" button");
    }

    public void tapSignOutButton() {
        click(signOutButton , "Unable to click the \"Sign Out\" button");
    }

    public void tapOnReportsButton() {
        waitUntilVisible(reportsButton, "Unable to display \"Reports\" button");
        click(reportsButton, "Unable to tap on \"Reports\" button");
    }

}
