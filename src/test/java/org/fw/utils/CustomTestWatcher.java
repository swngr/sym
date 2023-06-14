package org.fw.utils;

import org.fw.managers.LoggingManager;
import org.fw.managers.MobileDriverManager;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class CustomTestWatcher extends TestWatcher {

    public CustomTestWatcher() {
        super();
    }

    @Override
    protected void starting(Description description) {
        LoggingManager.logInfo(getClass(), "=====> Test Starting - " + description.toString()+ " ===");
        boolean isAppActivated = MobileDriverManager.getMobileDriver().queryAppState(UserDataUtils.getUserData(Constants.APP_ID)).toString().equals("RUNNING_IN_FOREGROUND");
        if(!isAppActivated) {
            MobileDriverManager.getMobileDriver().activateApp(UserDataUtils.getUserData(Constants.APP_ID));
        }
    }

    @Override
    protected void finished(Description description) {
        LoggingManager.logInfo(getClass(), "=====> Test Finished - " + description.toString() + " ===");
        killApp();
    }

    @Override
    protected void failed(Throwable e, Description description) {
        LoggingManager.logInfo(getClass(), "=====> Test Failed - Message: " + e.getMessage() +
                ". Description: " + description.toString()+ " ===");
        killApp();
    }

    // It only kills, it doesn't uninstall the app
    private void killApp(){
        MobileDriverManager.getMobileDriver().terminateApp(UserDataUtils.getUserData(Constants.APP_ID));
    }

}
