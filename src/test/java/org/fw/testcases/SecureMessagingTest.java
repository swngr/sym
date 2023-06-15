package org.fw.testcases;

import org.fw.components.BottonNavigatorComponent;
import org.fw.components.PushNotificationComponent;
import org.fw.pages.HomePage;
import org.fw.pages.SecureMessagePage;
import org.fw.utils.*;
import org.junit.*;

import static org.junit.Assert.assertTrue;

public class SecureMessagingTest extends BaseTest {

    @Rule
    public CustomTestWatcher watchman = new CustomTestWatcher();

    private final HomePage homePage = new HomePage();
    private final BottonNavigatorComponent bottonNavigatorComponent = new BottonNavigatorComponent();
    private final SecureMessagePage secureMessagePage = new SecureMessagePage();
    private final PushNotificationComponent pushNotificationComponent = new PushNotificationComponent();

    private static final String CONVERSATION_NAME = UserDataUtils.getUserData(Constants.CONVERSATION_NAME);
    private static final String conversationWithoutPatientsName = "Conversation-" + UserDataUtils.timeMillis();
    private static final String CONVERSATION_SEND_MESSAGE = UserDataUtils.getUserData(Constants.CONVERSATION_SEND_MESSAGE);

    private static final String username = "Connor";

    @Before
    public void setupLogin() {
        fastLoginSession();
    }

    @Test
//    Test Case 76836: Open Conversations tab - HomeScreen
    public void testOpenConversationTabHome(){
        homePage.tapSecureMessagingButton();
        assertTrue(secureMessagePage.isSecureMessagePageDisplayed());
        homePage.getBottonNavComp().tapHomeButton();
    }

    @Test
//    Test Case 84722: Open Conversations tab - Botton Navigation Icon
    public void testOpenConversationTabBottonNavMenu(){
        homePage.getBottonNavComp().tapMessageButton();
        assertTrue(secureMessagePage.isSecureMessagePageDisplayed());
        homePage.getBottonNavComp().tapHomeButton();
    }

    @Test
//    Test Case 76837: Search conversations by Participants
    public void testSearchConversationsByParticipants(){
        bottonNavigatorComponent.tapMessageButton();
        secureMessagePage.searchMessageWithPatient(username);
        WaitUtils.sleep(getClass(), 2);
        assertTrue(secureMessagePage.isSearchResultOnlyWithPatientSearched(username));
        secureMessagePage.clearSearchInputField();
        homePage.getBottonNavComp().tapHomeButton();
    }

    @Test
//    Test Case 76838: Create new Conversation
    public void testCreateNewConversation(){
        Assert.fail("ToDo - Test Under Construction: id: 76838");
        bottonNavigatorComponent.tapMessageButton();
        secureMessagePage.submitNewConversationForm(CONVERSATION_NAME, UserDataUtils.getUserData(Constants.PATIENT_FIRSTNAME), UserDataUtils.getUserData(Constants.PATIENT_FIRSTNAME));
        assertTrue(secureMessagePage.getConversationComponent().isDisplayed());
        secureMessagePage.getConversationComponent().tapBackButton();
        homePage.getBottonNavComp().tapHomeButton();
    }

    @Test
//    Test Case 84726: Create new Conversation - Without Patients
    public void testCreateNewConversationWithoutPatients(){
        bottonNavigatorComponent.tapMessageButton();
        secureMessagePage.submitNewConversationWithoutPatientsForm(conversationWithoutPatientsName);
        assertTrue(secureMessagePage.getConversationComponent().isDisplayed());
        secureMessagePage.getConversationComponent().tapBackButton();
        homePage.getBottonNavComp().tapHomeButton();
    }

    @Test
//    Test Case 76839: Add new members to the Conversation
    public void testAddNewMembersToConversation(){
        Assert.fail("ToDo - Test Under Construction: id: 76839");
    }

    @Test
//    Test Case 76840: Remove some members from Conversation
    public void testRemoveSomeMembersToConversation(){
        Assert.fail("ToDo - Test Under Construction: id: 76840");
    }

    @Test
//    Test Case 76841: Send a text message
    public void testSendTextMessage(){
        String message = "Hello World !!! - " + UserDataUtils.timeMillis();
        bottonNavigatorComponent.tapMessageButton();
        secureMessagePage.searchMessageWithPatient(username);
        secureMessagePage.tapConversation(CONVERSATION_NAME);
        WaitUtils.sleep(this.getClass(), 3);
        secureMessagePage.getConversationComponent().submitMessageThroughConversationForm(message);
        assertTrue(secureMessagePage.getConversationComponent().isDisplayed(message));
        secureMessagePage.getConversationComponent().tapBackButton();
        secureMessagePage.clearSearchInputField();
        homePage.getBottonNavComp().tapHomeButton();
    }

    @Test
//    Test Case 76842: Send a media message
    public void testSendMediaMessage(){
        bottonNavigatorComponent.tapMessageButton();
        secureMessagePage.searchMessageWithPatient(username);
        secureMessagePage.tapConversation(CONVERSATION_NAME);
        secureMessagePage.getConversationComponent().tapAddMediaGalleryPhoto();
        WaitUtils.sleep(getClass(), 4);
        secureMessagePage.getConversationComponent().tapLastImageOnChat();
        assertTrue(secureMessagePage.getConversationComponent().getImageGalleryComponent().isDisplayed());
        secureMessagePage.getConversationComponent().getImageGalleryComponent().tapBackButton();
        secureMessagePage.getConversationComponent().tapBackButton();
        secureMessagePage.clearSearchInputField();
        homePage.getBottonNavComp().tapHomeButton();
    }

    @Test
//    Test Case 76843: Receive a message from another user
    public void testReceiveMessageFromAnotherUser(){
        String newMessage = "Test - " + UserDataUtils.timeMillis();
        // send message through api
        ApiCalls.sendMessage(UserDataUtils.getUserData(Constants.PATIENT_ID_JOHN_CONNOR), UserDataUtils.getUserData(Constants.CONVERSATION_SEND_MESSAGE), newMessage);
        WaitUtils.sleep(getClass(), 20);
        homePage.swipe(Constants.Direction.DOWN);
        WaitUtils.sleep(getClass(), 10);
        homePage.getBottonNavComp().tapMessageButton();
        WaitUtils.sleep(getClass(), 5);
        //message at top
        try {
            assertTrue("The conversation that received the message should be the first",
                    secureMessagePage.isFirstConversationTheMessageSent(CONVERSATION_SEND_MESSAGE));
            assertTrue("Blue dot Notification not displayed",
                    secureMessagePage.isBlueDotDisplayedAtFirstMessage());
            assertTrue("Botton item message not showing the push notification attribute",
                    bottonNavigatorComponent.isMessageAttributeWithNotification());
        }catch (AssertionError | Exception e){
            throw new AssertionError(e.getMessage());
        }finally {
            secureMessagePage.tapFirstConversationWithMessage();
            secureMessagePage.getConversationComponent().tapBackButton();
            homePage.getBottonNavComp().tapHomeButton();
        }
    }

    @Test
//    Test Case 76844: Receive a push notification for new message
    public void testReceiveAPushNotificationForNewMessage(){
        bottonNavigatorComponent.tapMessageButton();
        secureMessagePage.searchMessageWithPatient(UserDataUtils.getUserData(Constants.AUTO_TESTER));
        secureMessagePage.tapConversation(CONVERSATION_SEND_MESSAGE);
        WaitUtils.sleep(getClass(), 5);
        homePage.minimizeAppDev();
        String newMessage = "Push Notification Test Message" + UserDataUtils.timeMillis();
        ApiCalls.sendMessage(UserDataUtils.getUserData(Constants.PATIENT_ID_JOHN_CONNOR), UserDataUtils.getUserData(Constants.CONVERSATION_SEND_MESSAGE), newMessage);
        long timeMessageSent = System.currentTimeMillis();
        try {
            assertTrue(pushNotificationComponent.isDisplayed());
            long timeMessageReceived = System.currentTimeMillis();
            int timeBetweenMessageSentAndReceived = Math.toIntExact((Math.abs(timeMessageSent - timeMessageReceived)) / 1000);
            assertTrue("Didn't received the push notification in time: 30 seconds", timeBetweenMessageSentAndReceived<=30);
            pushNotificationComponent.tapPushNotificationComponent();
        }catch (AssertionError e){
            homePage.goBackToSymApp();
            throw new AssertionError(e.getMessage());
        }finally {
            WaitUtils.sleep(getClass(), 10);
            assertTrue(secureMessagePage.getConversationComponent().isDisplayed(newMessage));
            secureMessagePage.getConversationComponent().tapBackButton();
            secureMessagePage.clearSearchInputField();
            homePage.getBottonNavComp().tapHomeButton();
        }
    }

    @Test
//    Test Case 76845: Start Meet Now (video visit) session with the Patient
    public void testStartAMeetNowSessionWithPatient(){
        Assert.fail("Test failing due to User Story - 88346");
        bottonNavigatorComponent.tapMessageButton();
        secureMessagePage.searchMessageWithPatient(username);
        secureMessagePage.tapConversation(CONVERSATION_NAME);
        secureMessagePage.getConversationComponent().tapMeetNowButton();
        try{
            secureMessagePage.getConversationComponent().tapStartMeetFromChat("Tap to join a Video Visit now.");
            assertTrue("The camera component was not displayed", secureMessagePage.getConversationComponent().getIphoneCameraComponent().isDisplayed());
        }catch (AssertionError | Exception e){
            throw new AssertionError(e.getMessage());
        }finally {
            secureMessagePage.getConversationComponent().getIphoneCameraComponent().tapHangoutMeetingButton();
            secureMessagePage.getConversationComponent().tapBackButton();
            secureMessagePage.clearSearchInputField();
            homePage.getBottonNavComp().tapHomeButton();
        }
    }

    @Test
//    Test Case 76932: Ability to see number of SM notifications on Home Screen and HM
    public void testSeeNumberOfSmNotificationOnHomeAndHmScreen(){

        String newMessage = "Test Notifications on Home Screen: " + UserDataUtils.timeMillis();
        ApiCalls.sendMessage(UserDataUtils.getUserData(Constants.PATIENT_ID_JOHN_CONNOR), UserDataUtils.getUserData(Constants.CONVERSATION_SEND_MESSAGE), newMessage);
        long timeMessageSent = System.currentTimeMillis();
        try {
            WaitUtils.sleep(getClass(), 10);
            homePage.swipe(Constants.Direction.DOWN);
            assertTrue(homePage.isSecureMessagingNotified());
            assertTrue(bottonNavigatorComponent.IsMessagingButtonNotified());
            long timeMessageReceived = System.currentTimeMillis();
            int timeBetweenMessageSentAndReceived = Math.toIntExact((Math.abs(timeMessageSent - timeMessageReceived)) / 1000);
            assertTrue("Didn't received the push notification in time: 30 seconds", timeBetweenMessageSentAndReceived<=30);
        }finally {
            bottonNavigatorComponent.tapMessageButton();
            secureMessagePage.searchMessageWithPatient(UserDataUtils.getUserData(Constants.AUTO_TESTER));
            secureMessagePage.tapConversation(CONVERSATION_SEND_MESSAGE);
            WaitUtils.sleep(getClass(), 3);
            secureMessagePage.getConversationComponent().tapBackButton();
            secureMessagePage.clearSearchInputField();
            homePage.getBottonNavComp().tapHomeButton();
        }
    }

    @After
    public void fastLogoutSession() {
        fastSignoutSession();
    }

}
