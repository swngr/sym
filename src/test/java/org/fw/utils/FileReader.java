package org.fw.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.fw.managers.LoggingManager;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReader {

    protected static JsonObject loadUserData(String fileType){
        String userDir = PropertyUtils.getProperty("user.dir");
        BufferedReader reader = null;
        JsonObject data = null;
        try {

            if (System.getenv("APP_CENTER_TEST") == null) {
                reader = new BufferedReader(new java.io.FileReader(userDir + "/src/test/resources/" + getUserDataFiles(fileType) + ".json"));
            } else if (System.getenv("APP_CENTER_TEST").equals("1")) {
                reader = new BufferedReader(new java.io.FileReader(userDir + "/test-classes/" + getUserDataFiles(fileType) + ".json"));
            }else{
                reader = new BufferedReader(new java.io.FileReader(userDir + "/src/test/resources/" + getUserDataFiles(fileType) + ".json"));
            }

            // read the JSON data into a string
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            reader.close();

            // parse the JSON data into a JsonObject
            data = JsonParser.parseString(json.toString()).getAsJsonObject();

        } catch (Exception exception) {
            LoggingManager.logError(FileReader.class, "Unable to load Json Files", exception);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException exception) {
                    LoggingManager.logError(FileReader.class, "Unable to close file stream", exception);
                }
            }
        }
        return data;
    }

    public static String getUserData(JsonObject dataFile, String userDataKey) {
        String userDataValueFromFile = null;
        try {
            userDataValueFromFile = dataFile.get(userDataKey).getAsString();

        } catch (Exception exception) {
            LoggingManager.logDebug(FileReader.class, "User Data '" + userDataKey + "'" + " does not exist in any of the " + FILE_TYPE + " files: '" + getUserDataFiles(FILE_TYPE) + "'");
        }
        LoggingManager.logDebug(FileReader.class, userDataKey + " = " + userDataValueFromFile);
        return userDataValueFromFile;
    }

    public static int getIntUserData(JsonObject dataFile, String userDataKey) {
        int userDataValueFromFile = -1;
        try {
            userDataValueFromFile = dataFile.get(userDataKey).getAsInt();
        } catch (Exception exception) {
            LoggingManager.logDebug(FileReader.class, "User Data '" + userDataKey + "'" + " does not exist in any of the " + FILE_TYPE + " files: '" + getUserDataFiles(FILE_TYPE) + "'");
        }
        LoggingManager.logDebug(FileReader.class, userDataKey + " = " + userDataValueFromFile);
        return userDataValueFromFile;
    }

    public static Constants.PatientStatus getUserDataPatient(JsonObject dataFile, String userDataKey) {
        Constants.PatientStatus userDataValueFromFile = null;
        try {
            userDataValueFromFile = Constants.PatientStatus.valueOf(dataFile.get(userDataKey).getAsString());
        } catch (Exception exception) {
            LoggingManager.logDebug(FileReader.class, "User Data '" + userDataKey + "'" + " does not exist in any of the " + FILE_TYPE + " files: '" + getUserDataFiles(FILE_TYPE) + "'");
        }
        LoggingManager.logDebug(FileReader.class, userDataKey + " = " + userDataValueFromFile.getName());
        return userDataValueFromFile;
    }

    public static Constants.Gender getUserDataGender(JsonObject dataFile, String patientGender) {
        Constants.Gender userDataValueFromFile = null;
        try {
            userDataValueFromFile = Constants.Gender.valueOf(dataFile.get(patientGender).getAsString());
        } catch (Exception exception) {
            LoggingManager.logDebug(FileReader.class, "User Data '" + patientGender + "'" + " does not exist in any of the " + FILE_TYPE + " files: '" + getUserDataFiles(FILE_TYPE) + "'");
        }
        LoggingManager.logDebug(FileReader.class, patientGender + " = " + userDataValueFromFile);
        return userDataValueFromFile;
    }

    private static String FILE_TYPE;
    private static String getUserDataFiles(String fileType){
        FILE_TYPE = fileType;
        if(fileType.equals(Constants.ENV)) {
            return "user-data/" + PropertyUtils.getProperty(Constants.BACKEND_ENV);
        }else if(fileType.equals(Constants.JSON_APPOINTMENT)){
            return "json-files/" + Constants.JSON_APPOINTMENT;
        }else{
            return null;
        }
    }

}
