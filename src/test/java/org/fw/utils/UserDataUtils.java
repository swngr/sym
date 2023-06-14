package org.fw.utils;

import com.google.gson.JsonObject;

import java.util.Calendar;

public class UserDataUtils extends FileReader {

    public static String randFirstName(){
        return getUserData(Constants.PATIENT_FIRSTNAME) + "-" + timeMillis();
    }

    public static String randLastName(){
        return getUserData(Constants.PATIENT_LASTNAME) + "-" + timeMillis();
    }

    public static String timeMillis(){
        return String.valueOf(System.currentTimeMillis());
    }

    public static Calendar getUserDOB(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, getIntUserData(Constants.PATIENT_DOB_DAY));
        calendar.set(Calendar.MONTH, getIntUserData(Constants.PATIENT_DOB_MONTH) - 1);
        calendar.set(Calendar.YEAR, getIntUserData(Constants.PATIENT_DOB_YEAR));
        return calendar;
    }

    private static JsonObject USER_DATA_FILES = loadUserData(Constants.ENV);

    public static String getUserData(String userDataKey) {
        return getUserData(USER_DATA_FILES, userDataKey);
    }

    public static int getIntUserData(String userDataKey) {
        return getIntUserData(USER_DATA_FILES, userDataKey);
    }

    public static Constants.PatientStatus getUserDataPatient(String userDataKey) {
        return getUserDataPatient(USER_DATA_FILES, userDataKey);
    }

    public static Constants.Gender getUserDataGender(String patientGender) {
        return getUserDataGender(USER_DATA_FILES, patientGender);
    }

}
