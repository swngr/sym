package org.fw.testcases;

import org.fw.pages.HomePage;
import org.fw.pages.PracticeFlowPage;
import org.fw.pages.SecureMessagePage;
import org.fw.utils.*;
import org.junit.*;

import static org.junit.Assert.*;

public class PracticeFlowTest extends BaseTest {

    @Rule
    public CustomTestWatcher watchman = new CustomTestWatcher();

    private final HomePage homePage = new HomePage();
    private final PracticeFlowPage practiceFlowPage = new PracticeFlowPage();

    private static String aptUserName;
    private static String aptService;
    private static Constants.AppointmentStatus aptStatus;

    private static boolean isAppointmentCreated = false;

    @Before
    public void setupLogin() {
        fastLoginSession();

        aptUserName = UserDataUtils.getUserData(Constants.PATIENT_FIRSTNAME) + " " +
                UserDataUtils.getUserData(Constants.PATIENT_LASTNAME);
        aptService = "Cosmetic Surgery - Automation NonSurgical";
        aptStatus = Constants.AppointmentStatus.PENDING;

        if(!isAppointmentCreated){
            ApiCalls.createAppointment();
            isAppointmentCreated = true;
        }
    }

    @Test
//    PracticeFlow - 76858 - Open Practice Flow tab - HomeScreen
    public void testOpenPracticeFlowTabHome(){
        homePage.tapPracticeFlowImage();
        practiceFlowPage.changeApptLocation(Constants.AppointmentLocation.MAIN, Constants.AppointmentLocation.AUTO_TEST_ROOM);
        assertTrue(practiceFlowPage.isDisplayed());
        practiceFlowPage.changeApptLocation(Constants.AppointmentLocation.AUTO_TEST_ROOM, Constants.AppointmentLocation.MAIN);
        practiceFlowPage.tapBackButton();
    }

    @Test
//    PracticeFlow - 87994 - Open Practice Flow tab - Left Side Menu
    public void testOpenPracticeFlowTabLeftSide() {
        homePage.tapOpenHamburgerMenu();
        homePage.getHamburgerMenuComp().tapPracticeFlow();
        practiceFlowPage.changeApptLocation(Constants.AppointmentLocation.MAIN, Constants.AppointmentLocation.AUTO_TEST_ROOM);
        assertTrue(practiceFlowPage.isDisplayed());
        practiceFlowPage.changeApptLocation(Constants.AppointmentLocation.AUTO_TEST_ROOM, Constants.AppointmentLocation.MAIN);
        practiceFlowPage.tapBackButton();
    }

    @Test
//    PracticeFlow - 76859 - Change location on Practice Flow
    public void testChangePracticeFlowLocation() {
        homePage.tapPracticeFlowImage();
        practiceFlowPage.changeApptLocation(Constants.AppointmentLocation.MAIN, Constants.AppointmentLocation.AUTO_TEST_ROOM);
        assertTrue(practiceFlowPage.isTimezoneDisplayed("CST"));
        practiceFlowPage.changeApptLocation(Constants.AppointmentLocation.AUTO_TEST_ROOM, Constants.AppointmentLocation.AUTO_TEST_SPA);
        assertTrue(practiceFlowPage.isTimezoneDisplayed("HST"));
        practiceFlowPage.changeApptLocation(Constants.AppointmentLocation.AUTO_TEST_SPA, Constants.AppointmentLocation.MAIN);
        practiceFlowPage.tapBackButton();
    }

    @Test
//    PracticeFlow - 76860 - View Appointment Cards for selected Location
    public void testViewAppointmentForSelectedLocation() {
        homePage.tapPracticeFlowImage();
        practiceFlowPage.changeApptLocation(Constants.AppointmentLocation.MAIN, Constants.AppointmentLocation.AUTO_TEST_ROOM);
        assertTrue(practiceFlowPage.isCardDisplayed(aptUserName, aptService));
        practiceFlowPage.changeApptLocation(Constants.AppointmentLocation.AUTO_TEST_ROOM, Constants.AppointmentLocation.MAIN);
        practiceFlowPage.tapBackButton();
    }

    @Test
//    Test Case 76862: Open Appointment Card
    public void testOpenAppointmentCard() {
        homePage.tapPracticeFlowImage();
        practiceFlowPage.changeApptLocation(Constants.AppointmentLocation.MAIN, Constants.AppointmentLocation.AUTO_TEST_ROOM);
        practiceFlowPage.tapCard(aptUserName);
        practiceFlowPage.getPracticeFlowCardComponent().isDisplayed(aptUserName, aptService);
        practiceFlowPage.getPracticeFlowCardComponent().tapAppointment();
        practiceFlowPage.getPracticeFlowCardComponent().getAppointmentComponent().isDisplayed(aptUserName, aptService, aptStatus);
        practiceFlowPage.getPracticeFlowCardComponent().getAppointmentComponent().tapClose();
        practiceFlowPage.getPracticeFlowCardComponent().tapClose();
        practiceFlowPage.changeApptLocation(Constants.AppointmentLocation.AUTO_TEST_ROOM, Constants.AppointmentLocation.MAIN);
        practiceFlowPage.tapBackButton();
    }

    @Test
//    Test Case 76861: Verify the Ability to move card to any group in Practice flow
    public void testVerifyMoveCardToRoom() {
        homePage.tapPracticeFlowImage();
        practiceFlowPage.changeApptLocation(Constants.AppointmentLocation.MAIN, Constants.AppointmentLocation.AUTO_TEST_ROOM);

        int apptYBefore = practiceFlowPage.getApptElement(aptUserName).getLocation().getY();
        int pendingYBefore = practiceFlowPage.getPendingElement().getLocation().getY();
        int confirmedYBefore = practiceFlowPage.getConfirmedElement().getLocation().getY();

        // Verify appointment is between pending and confirmed (pending, appointment, confirmed)
        assertTrue(pendingYBefore < apptYBefore && apptYBefore < confirmedYBefore);

        // Go to appointment
        practiceFlowPage.tapCard(aptUserName);
        practiceFlowPage.getPracticeFlowCardComponent().tapAppointment();

        // Verify group changed inside appointment component
        String aptStatusBefore = practiceFlowPage.getPracticeFlowCardComponent().getAppointmentComponent().getStatus(Constants.AppointmentStatus.PENDING);
        practiceFlowPage.getPracticeFlowCardComponent().getAppointmentComponent().changeStatus(Constants.AppointmentStatus.PENDING, Constants.AppointmentStatus.CONFIRMED);
        String aptStatusAfter = practiceFlowPage.getPracticeFlowCardComponent().getAppointmentComponent().getStatus(Constants.AppointmentStatus.CONFIRMED);
        Assert.assertNotEquals(aptStatusBefore, aptStatusAfter);

        practiceFlowPage.getPracticeFlowCardComponent().getAppointmentComponent().tapClose();
        practiceFlowPage.getPracticeFlowCardComponent().tapClose();

        // Verify group change on practice flow
        int apptYAfter = practiceFlowPage.getApptElement((aptUserName)).getLocation().getY();
        int pendingYAfter = practiceFlowPage.getPendingElement().getLocation().getY();
        int confirmedYAfter = practiceFlowPage.getConfirmedElement().getLocation().getY();

        // Verify appointment is after pending and confirmed (pending, confirmed, appointment)
        assertTrue(pendingYAfter < confirmedYAfter && confirmedYAfter < apptYAfter);

        // Send appointment back to the pending group
        practiceFlowPage.tapCard(aptUserName);
        practiceFlowPage.getPracticeFlowCardComponent().tapAppointment();
        practiceFlowPage.getPracticeFlowCardComponent().getAppointmentComponent().changeStatus(Constants.AppointmentStatus.CONFIRMED, Constants.AppointmentStatus.PENDING);

        practiceFlowPage.getPracticeFlowCardComponent().getAppointmentComponent().tapClose();
        practiceFlowPage.getPracticeFlowCardComponent().tapClose();
        practiceFlowPage.changeApptLocation(Constants.AppointmentLocation.AUTO_TEST_ROOM, Constants.AppointmentLocation.MAIN);
        practiceFlowPage.tapBackButton();
    }

    @Test
//    Test Case 88716: Verify the Ability to move card to any room in Practice flow
    public void testVerifyMoveCardToGroup() {
        homePage.tapPracticeFlowImage();
        practiceFlowPage.changeApptLocation(Constants.AppointmentLocation.MAIN, Constants.AppointmentLocation.AUTO_TEST_ROOM);
        practiceFlowPage.tapCard(aptUserName);
        practiceFlowPage.getPracticeFlowCardComponent().tapAppointment();
        practiceFlowPage.getPracticeFlowCardComponent().getAppointmentComponent().tapEditButton();
        practiceFlowPage.getPracticeFlowCardComponent().getAppointmentComponent().getEditAppointmentComponent().chooseLocation(Constants.AppointmentLocation.AUTO_TEST_SPA);
        practiceFlowPage.getPracticeFlowCardComponent().getAppointmentComponent().getEditAppointmentComponent().tapSaveButton();
        practiceFlowPage.getPracticeFlowCardComponent().getAppointmentComponent().tapClose();
        practiceFlowPage.getPracticeFlowCardComponent().tapClose();
        practiceFlowPage.changeApptLocation(Constants.AppointmentLocation.AUTO_TEST_ROOM, Constants.AppointmentLocation.AUTO_TEST_SPA);
        // check if card is in the correct group
        assertTrue(practiceFlowPage.isCardDisplayed(aptUserName, aptService));

        // send card back to previous room
        practiceFlowPage.tapCard(aptUserName);
        practiceFlowPage.getPracticeFlowCardComponent().tapAppointment();
        practiceFlowPage.getPracticeFlowCardComponent().getAppointmentComponent().tapEditButton();
        practiceFlowPage.getPracticeFlowCardComponent().getAppointmentComponent().getEditAppointmentComponent().chooseLocation(Constants.AppointmentLocation.AUTO_TEST_ROOM);
        practiceFlowPage.getPracticeFlowCardComponent().getAppointmentComponent().getEditAppointmentComponent().tapSaveButton();
        practiceFlowPage.getPracticeFlowCardComponent().getAppointmentComponent().tapClose();
        practiceFlowPage.getPracticeFlowCardComponent().tapClose();
        practiceFlowPage.changeApptLocation(Constants.AppointmentLocation.AUTO_TEST_SPA, Constants.AppointmentLocation.AUTO_TEST_ROOM);
        assertTrue(practiceFlowPage.isCardDisplayed(aptUserName, aptService));
        practiceFlowPage.changeApptLocation(Constants.AppointmentLocation.AUTO_TEST_ROOM, Constants.AppointmentLocation.MAIN);
        practiceFlowPage.tapBackButton();
    }

    @Test
//    Test Case 76863: Verify all functions in the Appointment Card
    public void testVerifyAppointmentCardFunctions() {
        homePage.tapPracticeFlowImage();
        practiceFlowPage.changeApptLocation(Constants.AppointmentLocation.MAIN, Constants.AppointmentLocation.AUTO_TEST_ROOM);
        practiceFlowPage.tapCard(aptUserName);

        // Open "Open Appointment"
        practiceFlowPage.getPracticeFlowCardComponent().tapAppointment();
        assertTrue(practiceFlowPage.getPracticeFlowCardComponent().getAppointmentComponent().isDisplayed(aptUserName, aptService, aptStatus));
        practiceFlowPage.getPracticeFlowCardComponent().getAppointmentComponent().tapClose();

        // Open "Patient File"
        practiceFlowPage.getPracticeFlowCardComponent().tapPatientFile();
        assertTrue(practiceFlowPage.getPracticeFlowCardComponent().getPatientFileComponent().isDisplayed());
        practiceFlowPage.getPracticeFlowCardComponent().getPatientFileComponent().tapClose();

        // Open "Media"
        practiceFlowPage.getPracticeFlowCardComponent().tapMedia();
        assertTrue(practiceFlowPage.getPracticeFlowCardComponent().getMediaComponent().isDisplayed());
        practiceFlowPage.getPracticeFlowCardComponent().getMediaComponent().tapClose();

        // Open "New EHR Note"
        practiceFlowPage.getPracticeFlowCardComponent().tapEhrNote();
        assertTrue(practiceFlowPage.getPracticeFlowCardComponent().getEhrNoteComponent().isDisplayed());
        practiceFlowPage.getPracticeFlowCardComponent().getEhrNoteComponent().tapClose();

        // Open "New Estimate"
        practiceFlowPage.getPracticeFlowCardComponent().tapNewEstimate();
        practiceFlowPage.getPracticeFlowCardComponent().getEstimateComponent().getNewEstimateComponent().tapCloseLocation();
        practiceFlowPage.getPracticeFlowCardComponent().getEstimateComponent().getNewEstimateComponent().tapClose();
        assertTrue(practiceFlowPage.getPracticeFlowCardComponent().getEstimateComponent().isDisplayed());
        practiceFlowPage.getPracticeFlowCardComponent().getEstimateComponent().tapCancel();

        // Open "New Invoice"
        practiceFlowPage.getPracticeFlowCardComponent().tapNewInvoice();
        practiceFlowPage.getPracticeFlowCardComponent().getInvoiceComponent().getNewInvoiceComponent().tapCloseLocation();
        practiceFlowPage.getPracticeFlowCardComponent().getInvoiceComponent().getNewInvoiceComponent().tapClose();
        assertTrue(practiceFlowPage.getPracticeFlowCardComponent().getInvoiceComponent().isDisplayed());
        practiceFlowPage.getPracticeFlowCardComponent().getInvoiceComponent().tapCancel();

        // Open "Secure Message"
        practiceFlowPage.getPracticeFlowCardComponent().tapSecureMessage();
        assertTrue(practiceFlowPage.getPracticeFlowCardComponent().getConversationComponent().isDisplayed());
        practiceFlowPage.getPracticeFlowCardComponent().getConversationComponent().tapBackButton();
        new SecureMessagePage().tapClose();

        practiceFlowPage.getPracticeFlowCardComponent().tapClose();
        practiceFlowPage.changeApptLocation(Constants.AppointmentLocation.AUTO_TEST_ROOM, Constants.AppointmentLocation.MAIN);
        practiceFlowPage.tapBackButton();
    }

    @Test
//    Test Case 76864: Setup Groups and Rooms via PF settings
    public void testSetupGroupAndFunctionsViaPFSettings() {
        homePage.tapPracticeFlowImage();
        practiceFlowPage.changeApptLocation(Constants.AppointmentLocation.MAIN, Constants.AppointmentLocation.AUTO_TEST_ROOM);
        practiceFlowPage.tapSettingsButton();

        assertTrue(practiceFlowPage.getPracticeFlowSettingsCardComponent().isDisplayed());

        // Create new test room
        String roomName = "Auto Test Room";
        String groupName = "Upcoming";
        practiceFlowPage.getPracticeFlowSettingsCardComponent().createRoom(roomName);

        // Add new test room created inside a group
        practiceFlowPage.getPracticeFlowSettingsCardComponent().addRoomAtGroup(roomName, groupName);
        assertTrue(practiceFlowPage.isRoomAngGroupDisplayed(roomName, groupName));

        // Delete test room created
        practiceFlowPage.tapSettingsButton();
        practiceFlowPage.getPracticeFlowSettingsCardComponent().deleteRoom(roomName);
        WaitUtils.sleep(getClass(), 4);
        assertFalse(practiceFlowPage.isRoomAngGroupDisplayed(roomName, groupName));

        // Go back to homepage
        practiceFlowPage.changeApptLocation(Constants.AppointmentLocation.AUTO_TEST_ROOM, Constants.AppointmentLocation.MAIN);
        practiceFlowPage.tapBackButton();
    }

//    Test Case 76865: Verify Practice Flow Notifications settings
    @Test
    public void testVerifyPracticeFlowNotificationsSettings() {
        homePage.tapPracticeFlowImage();
        practiceFlowPage.changeApptLocation(Constants.AppointmentLocation.MAIN, Constants.AppointmentLocation.AUTO_TEST_ROOM);
        practiceFlowPage.tapNotificationsButton();

        assertTrue(practiceFlowPage.getNotificationsDetailsComponent().isDisplayed());

        String userName = "Luiz Gustavo";
        // Add User to notification list
        practiceFlowPage.getNotificationsDetailsComponent().addUserToNotificationList(userName);
        // Verify user is added to notification list
        practiceFlowPage.tapNotificationsButton();
        assertTrue(practiceFlowPage.getNotificationsDetailsComponent().isUserAddedToNotificationList(userName));
        // Remove user from notification list
        practiceFlowPage.getNotificationsDetailsComponent().removeUserFromNotificationList(userName);

        // Go back to homepage
        practiceFlowPage.changeApptLocation(Constants.AppointmentLocation.AUTO_TEST_ROOM, Constants.AppointmentLocation.MAIN);
        practiceFlowPage.tapBackButton();
    }

}
