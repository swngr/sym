package org.fw.utils;

import com.google.gson.JsonObject;

public class JsonDataUtils extends FileReader {

    private static final JsonObject JSON_FILES = loadUserData(Constants.JSON_APPOINTMENT);

    public static String getUserData(String userDataKey) {
        return getUserData(JSON_FILES, userDataKey);
    }

    public static int getIntUserData(String userDataKey) {
        return getIntUserData(JSON_FILES, userDataKey);
    }

    public static Constants.PatientStatus getUserDataPatient(String userDataKey) {
        return getUserDataPatient(JSON_FILES, userDataKey);
    }

    public static Constants.Gender getUserDataGender(String patientGender) {
        return getUserDataGender(JSON_FILES, patientGender);
    }

    public static JsonObject getJsonObject(){
        return JSON_FILES;
    }

}
