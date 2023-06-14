package org.fw.testcases;


import org.fw.components.*;
import org.fw.pages.*;
import org.fw.utils.Constants;
import org.fw.utils.CustomTestWatcher;
import org.fw.utils.UserDataUtils;
import org.junit.*;

import static org.junit.Assert.*;

public class CheckInCheckOutTest extends BaseTest{

    @Rule
    public CustomTestWatcher watchman = new CustomTestWatcher();
    private final HomePage homePage = new HomePage();
    HamburgerMenuComponent hamburgerMenuComponent = new HamburgerMenuComponent();
    CheckInCheckOutPage checkInCheckOutPage = new CheckInCheckOutPage();
    LocationsComponent locationsComponent = new LocationsComponent();
    PracticeLoginPage practiceLoginPage = new PracticeLoginPage();
    LoginPage loginPage = new LoginPage();
    WelcomeCarrouselComponent welcomeCarrouselComponent = new WelcomeCarrouselComponent();
    AppointmentStatusComponent appointmentStatusComponent = new AppointmentStatusComponent();
    AppointmentComponent appointmentComponent = new AppointmentComponent();
    PatientFilePage patientFilePage = new PatientFilePage();
    BillingLocationsPage billingLocationsPage = new BillingLocationsPage();


    //TEST CASE 76867 - Open Check-In / Check-Out screen
    @Before
    public void testGoToCheckInCheckOutSection() {
        practiceLoginPage.practiceSelection();
        loginPage.submitLoginForm(UserDataUtils.getUserData(Constants.USERNAME), UserDataUtils.getUserData(Constants.PASSWORD));
        welcomeCarrouselComponent.newsCarousel();
        homePage.tapOpenHamburgerMenu();
        hamburgerMenuComponent.tapCalendarDropdown();

    }

    //TEST CASE 76867
    @Test
    public void testGoToCheckInPatient() {

        hamburgerMenuComponent.tapCheckInPatientButton();
        String checkInTitle = checkInCheckOutPage.getTextCheckInCheckOutTitle();
        assertEquals(checkInTitle, "Check In");
    }

    //TEST CASE 76867
    @Test
    public void testGoToCheckOutPatient() {

        hamburgerMenuComponent.tapCheckOutPatientButton();
        String checkOutTitle = checkInCheckOutPage.getTextCheckInCheckOutTitle();
        assertEquals(checkOutTitle, "Check Out");
    }

    //TEST CASE 76868
    @Test
    public void testChangeLocations() {

        hamburgerMenuComponent.tapCheckInPatientButton();
        String defaultApptLocation = checkInCheckOutPage.getTextAppointmentLocation();
        checkInCheckOutPage.clickOnAppointmentLocationButton();
        locationsComponent.clickOnChangeLocation("Chicken room");
        String newApptLocation = checkInCheckOutPage.getTextAppointmentLocation();
        assertNotEquals(defaultApptLocation, newApptLocation);
        checkInCheckOutPage.clickOnAppointmentLocationButton();
        locationsComponent.clickOnChangeLocation("Main");
        String latestApptLocation = checkInCheckOutPage.getTextAppointmentLocation();
        assertNotEquals(newApptLocation, latestApptLocation);
        checkInCheckOutPage.clickOnAppointmentLocationButton();
        locationsComponent.clickOnChangeLocation("All Locations");
    }

    //TEST CASE 76869
    @Test
    public void testViewAppointmentsList() {

        hamburgerMenuComponent.tapCheckInPatientButton();
        checkInCheckOutPage.clickOnAppointmentLocationButton();
        locationsComponent.clickOnChangeLocation("Auto Test Room");

        if (checkInCheckOutPage.getTextAppointmentsTable().contains("No appointments for Check In") || !checkInCheckOutPage.getTextAppointmentStatus().equals("Pending")){
            appointmentStatusComponent.createApptWithStatusAndLocation("Pending", "Auto Test Room");
            checkInCheckOutPage.clickOnAppointmentLocationButton();
            locationsComponent.clickOnChangeLocation("All Locations");
            checkInCheckOutPage.clickOnAppointmentLocationButton();
            locationsComponent.clickOnChangeLocation("Auto Test Room");
        }
        assertEquals(checkInCheckOutPage.getTextAppointmentStatus(), "Pending");
        checkInCheckOutPage.clickOnAppointmentLocationButton();
        locationsComponent.clickOnChangeLocation("All Locations");
        assertEquals(checkInCheckOutPage.getTextAppointmentStatus(), "Pending");

        checkInCheckOutPage.tapOnCloseButton();
        homePage.tapOpenHamburgerMenu();
        hamburgerMenuComponent.tapCheckOutPatientButton();
        checkInCheckOutPage.clickOnAppointmentLocationButton();
        locationsComponent.clickOnChangeLocation("Auto Test Room");

        if (checkInCheckOutPage.getTextAppointmentsTable().contains("No appointments for Check Out") || !checkInCheckOutPage.getTextAppointmentStatus().equals("Checked In")){
            appointmentStatusComponent.createApptWithStatusAndLocation("Checked In", "Auto Test Room");
            checkInCheckOutPage.clickOnAppointmentLocationButton();
            locationsComponent.clickOnChangeLocation("All Locations");
            checkInCheckOutPage.clickOnAppointmentLocationButton();
            locationsComponent.clickOnChangeLocation("Auto Test Room");
        }
        assertEquals(checkInCheckOutPage.getTextAppointmentStatus(), "Checked In");
        checkInCheckOutPage.clickOnAppointmentLocationButton();
        locationsComponent.clickOnChangeLocation("All Locations");
        assertEquals(checkInCheckOutPage.getTextAppointmentStatus(), "Checked In");

    }


    //TEST CASE 76870
    @Test
    public void testAppointmentStatusUpdate() {

        hamburgerMenuComponent.tapCheckInPatientButton();
        checkInCheckOutPage.clickOnAppointmentLocationButton();
        locationsComponent.clickOnChangeLocation("Auto Test Room");

        if (checkInCheckOutPage.getTextAppointmentsTable().contains("No appointments for Check In") || !checkInCheckOutPage.getTextAppointmentStatus().equals("Pending")){
            appointmentStatusComponent.createApptWithStatusAndLocation("Pending", "Auto Test Room");
            checkInCheckOutPage.clickOnAppointmentLocationButton();
            locationsComponent.clickOnChangeLocation("All Locations");
            checkInCheckOutPage.clickOnAppointmentLocationButton();
            locationsComponent.clickOnChangeLocation("Auto Test Room");
        }
        String statusPending = checkInCheckOutPage.getTextAppointmentStatus();
        assertEquals(statusPending, "Pending");
        checkInCheckOutPage.tapOnAppointmentStatus();
        Assert.assertTrue(appointmentStatusComponent.isEmergentWindowDisplayed());

        appointmentStatusComponent.tapOnEmergentAppointmentOption("Check In");
        appointmentStatusComponent.tapOnEmergentAppointmentOption("Checked In");
        String statusCheckedIn = checkInCheckOutPage.getTextAppointmentStatus();
        assertTrue(!statusPending.equals(statusCheckedIn));
        checkInCheckOutPage.tapOnCloseButton();

        homePage.tapOpenHamburgerMenu();
        hamburgerMenuComponent.tapCheckOutPatientButton();
        statusCheckedIn = checkInCheckOutPage.getTextAppointmentStatus();
        checkInCheckOutPage.tapOnAppointmentStatus();
        appointmentStatusComponent.tapOnEmergentAppointmentOption("Check Out");
        appointmentStatusComponent.tapOnEmergentAppointmentOption("Check Out");
        String statusCheckOut = checkInCheckOutPage.getTextAppointmentStatus();
        assertTrue(!statusCheckedIn.equals(statusCheckOut));
        checkInCheckOutPage.clickOnAppointmentLocationButton();
        locationsComponent.clickOnChangeLocation("All Locations");
    }


    //TEST CASE 76871
    @Test
    public void testViewAppointmentOptions() {

        hamburgerMenuComponent.tapCheckInPatientButton();
        checkInCheckOutPage.clickOnAppointmentLocationButton();
        locationsComponent.clickOnChangeLocation("Auto Test Spa");

        if (checkInCheckOutPage.getTextAppointmentsTable().contains("No appointments for Check In") || !checkInCheckOutPage.getTextAppointmentStatus().equals("Pending")){
            appointmentStatusComponent.createApptWithStatusAndLocation("Pending", "Auto Test Spa");
            checkInCheckOutPage.clickOnAppointmentLocationButton();
            locationsComponent.clickOnChangeLocation("All Locations");
            checkInCheckOutPage.clickOnAppointmentLocationButton();
            locationsComponent.clickOnChangeLocation("Auto Test Spa");
        }
        assertEquals(checkInCheckOutPage.getTextAppointmentStatus(), "Pending");
        checkInCheckOutPage.tapOnAppointmentStatus();
        Assert.assertTrue(appointmentStatusComponent.isEmergentWindowDisplayed());
        appointmentStatusComponent.tapOnEmergentAppointmentOption("View Appointment");
        assertTrue(appointmentComponent.getTextAppointmentWindowHeader().equals("Appointment"));
        appointmentComponent.tapOnCloseButton();
        homePage.tapOpenHamburgerMenu();
        hamburgerMenuComponent.tapCheckInPatientButton();
        checkInCheckOutPage.tapOnAppointmentStatus();
        Assert.assertTrue(appointmentStatusComponent.isEmergentWindowDisplayed());
        appointmentStatusComponent.tapOnEmergentAppointmentOption("Show Patient File");
        assertTrue(patientFilePage.getTextPatientFileWindowHeader().equals("Patient File"));
        patientFilePage.tapOnCancelButton();
        homePage.tapOpenHamburgerMenu();

        hamburgerMenuComponent.tapCheckOutPatientButton();
        if (checkInCheckOutPage.getTextAppointmentsTable().contains("No appointments for Check Out") || !checkInCheckOutPage.getTextAppointmentStatus().equals("Checked In")){
            appointmentStatusComponent.createApptWithStatusAndLocation("Checked In", "Auto Test Spa");
            checkInCheckOutPage.clickOnAppointmentLocationButton();
            locationsComponent.clickOnChangeLocation("All Locations");
            checkInCheckOutPage.clickOnAppointmentLocationButton();
            locationsComponent.clickOnChangeLocation("Auto Test Spa");
        }
        assertEquals(checkInCheckOutPage.getTextAppointmentStatus(), "Checked In");
        checkInCheckOutPage.tapOnAppointmentStatus();
        Assert.assertTrue(appointmentStatusComponent.isEmergentWindowDisplayed());
        appointmentStatusComponent.tapOnEmergentAppointmentOption("View Appointment");
        assertTrue(appointmentComponent.getTextAppointmentWindowHeader().equals("Appointment"));
        appointmentComponent.tapOnCloseButton();
        homePage.tapOpenHamburgerMenu();
        hamburgerMenuComponent.tapCheckOutPatientButton();
        checkInCheckOutPage.tapOnAppointmentStatus();
        Assert.assertTrue(appointmentStatusComponent.isEmergentWindowDisplayed());
        appointmentStatusComponent.tapOnEmergentAppointmentOption("Show Patient File");
        assertTrue(patientFilePage.getTextPatientFileWindowHeader().equals("Patient File"));
        patientFilePage.tapOnCancelButton();

        homePage.tapOpenHamburgerMenu();
        hamburgerMenuComponent.tapCheckOutPatientButton();
        checkInCheckOutPage.tapOnAppointmentStatus();
        appointmentStatusComponent.tapOnEmergentAppointmentOption("Create Invoice");
        assertTrue(billingLocationsPage.getTextBillingLocationsWindowHeader().equals("Billing Locations"));

    }

}



