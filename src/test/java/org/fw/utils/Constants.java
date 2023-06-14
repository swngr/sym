package org.fw.utils;

public class Constants {

    // PropertyUtils
    public static final String BUILD = "build";
    public static final String PROPERTY_PROPERTIES_FILES = "propertiesFiles";
    public static final String DEFAULT_PROPERTIES_FILES = "local.properties";
    public static final String DEFAULT_AZURE_PROPERTIES_FILES = "ios.azure.properties";
    public static final String APP_NAME = "app.name";
    public static final String DEVICE_NAME = "device.name";
    public static final String AUTOMATION_NAME = "automation.name";
    public static final String PLATFORM_VERSION = "platform.version";
    public static final String PLATFORM_NAME = "platform.name";
    public static final String BS_APP_URL = "app.url";
    public static final String BS_USERNAME = "bs.username";
    public static final String BS_SERVER = "bs.server";
    public static final String BS_ACCESS_KEY = "bs.access_key";
    public static final String APPIUM_URL = "appium.url";
    public static final String AUTO_DISMISS_ALERTS = "auto.dismiss.alerts";
    public static final String AUTO_ACCEPT_ALERTS = "auto.accept.alerts";
    public static final String AUTO_GRANT_PERMISSIONS = "auto.grant.permissions";
    public static final String APP_PUSH_TIMEOUT = "app.push.timeout";
    public static final String BS_NETWORK_LOGS = "browserstack.networkLogs";
    public static final String DEVICE_UDID = "device.udid";
    public static final String IS_RELEASE_VERSION = "is.release.version";
    public static final String BACKEND_ENV = "backend.env";

    // UserDataUtils

    public static final String PRACTICE = "practice";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String PRACTICE_USERNAME = "practice.username";
    // UserDataUtils -> username 2
    public static final String USERNAME2 = "username2";
    public static final String PASSWORD2 = "password2";
    public static final String AUTO_TESTER = "auto.tester";
    // UserDataUtils -> Patient
    public static final String PATIENT_STATUS = "patient.status";
    public static final String PATIENT_FIRSTNAME = "patient.firstname";
    public static final String PATIENT_LASTNAME = "patient.lastname";
    public static final String PATIENT_ID_JOHN_CONNOR = "patient.id.john.connor";
    public static final String PATIENT_FIRSTNAME_TONY = "patient.firstname.tony";
    public static final String PATIENT_LASTNAME_STARK = "patient.lastname.stark";
    public static final String PATIENT_ID_TONY_STARK = "patient.id.tony.stark";
    public static final String PATIENT_DOB_MONTH = "patient.dob.month";
    public static final String PATIENT_DOB_DAY = "patient.dob.day";
    public static final String PATIENT_DOB_YEAR = "patient.dob.year";
    public static final String PATIENT_GENDER = "patient.gender";
    public static final String PATIENT_MOBILE_PHONE = "patient.mobile.phone";
    public static final String PATIENT_REFERRAL_SOURCE = "patient.referral.source";

    // Utils

    public static final String APP_ID = "app.id";

    // User Data

    public static final String CONVERSATION_NAME = "conversation.name";
    public static final String CONVERSATION_SEND_MESSAGE = "conversation.send.message";

    // API Data

    public static final String API_USERNAME = "api.username";
    public static final String API_PASSWORD = "api.password";
    public static final String API_CLIENT_ID = "api.client.id";

    // Appointment Data

    public static final String LOCATION_ID = "location.id"; // id: 125 = room, id: 127 = spa

    // Json file switch variables

    public static final String ENV = "ENV";
    public static final String JSON_APPOINTMENT = "appointment";

    public enum Gender {
        FEMALE("Female"),
        MALE("Male"),
        OTHER("Other");

        private final String name;

        Gender(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

    public enum PatientStatus {
        PATIENT("Patient"),
        PROSPECT("Prospect");

        private final String name;

        PatientStatus(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

    public enum Direction {
        UP("Up"),
        DOWN("Down");

        private final String name;

        Direction(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

    public enum AppointmentStatus {
        PENDING("PENDING"),
        CONFIRMED("CONFIRMED"),
        CHECKED_IN("CHECKED_IN"),
        ONLINE_WAITING_ROOM("ONLINE_WAITING_ROOM"),
        RECEIVED("RECEIVED"),
        ONLINE_MEETING_ROOM("ONLINE_MEETING_ROOM"),
        CHECK_OUT("CHECK_OUT"),
        NO_SHOW("NO_SHOW"),
        CANCELLED("CANCELLED"),
        CHECK_IN_PARK_LOT("CHECK_IN_PARK_LOT"),
        AUTO_TEST_ROOM_STATUS("AUTO_TEST_ROOM_STATUS");

        private final String name;

        AppointmentStatus(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getFirstLetterUpperCase() {
            return StringUtils.upperCaseFirstLetter(name);
        }

        public String getAllFirstLettersUpperCase() {
            return StringUtils.upperCaseAllLetters(name);
        }

    }

    public enum AppointmentLocation {
        ARIZONA_SURGERY("Arizona Surgery"),
        AUTO_TEST_ROOM("Auto Test Room"),
        AUTO_TEST_SPA("Auto Test Spa"),
        CHICKEN_ROOM("Chicken room"),
        KOLOTAK("Kolotak"),
        MAIN("Main"),
        MED_SPA("Med Spa"),
        WEST("West");

        private final String name;

        AppointmentLocation(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

}
