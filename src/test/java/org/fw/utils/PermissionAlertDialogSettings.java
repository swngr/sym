package org.fw.utils;

import org.fw.managers.MobileDriverManager;

public class PermissionAlertDialogSettings {

    public static void setAllowUseFaceID(){
        MobileDriverManager.getMobileDriver().setSetting("acceptAlertButtonSelector", "**/XCUIElementTypeButton[`label == \"Use Face ID\"`]");
    }

    public static void setAllowAccessToAllPhotos(){
        MobileDriverManager.getMobileDriver().setSetting("acceptAlertButtonSelector", "**/XCUIElementTypeButton[`label == \"Allow Access to All Photos\"`]");
    }

    public static void setDiscardEstimateInvoice(){
        MobileDriverManager.getMobileDriver().setSetting("acceptAlertButtonSelector", "**/XCUIElementTypeButton[`label == \"Discard\"`]");
    }

}
