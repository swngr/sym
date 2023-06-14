package org.fw.managers;

import com.microsoft.appcenter.appium.EnhancedIOSDriver;
import com.microsoft.appcenter.appium.Factory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.fw.enums.Platform;
import org.fw.utils.Constants;
import org.fw.utils.PropertyUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionId;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class MobileDriverManager {

    private static final String BUILD = PropertyUtils.getProperty(Constants.BUILD);
    private static final String PLATFORM_VERSION = PropertyUtils.getProperty(Constants.PLATFORM_VERSION);
    private static final String PLATFORM_NAME = PropertyUtils.getProperty(Constants.PLATFORM_NAME);
    private static final String DEVICE_NAME = PropertyUtils.getProperty(Constants.DEVICE_NAME);
    private static final String BS_APP_URL = PropertyUtils.getProperty(Constants.BS_APP_URL);
    private static final String APP_NAME = PropertyUtils.getProperty(Constants.APP_NAME);
    private static final String AUTOMATION_NAME = PropertyUtils.getProperty(Constants.AUTOMATION_NAME);
    private static final String APPIUM_URL = PropertyUtils.getProperty(Constants.APPIUM_URL);
    private static final String BS_ACCESS_KEY = PropertyUtils.getProperty(Constants.BS_ACCESS_KEY);
    private static final String BS_USERNAME = PropertyUtils.getProperty(Constants.BS_USERNAME);
    private static final String BS_SERVER = PropertyUtils.getProperty(Constants.BS_SERVER);
    private static final boolean AUTO_DISMISS_ALERTS = PropertyUtils.getBooleanProperty(Constants.AUTO_DISMISS_ALERTS);
    private static final boolean AUTO_ACCEPT_ALERTS = PropertyUtils.getBooleanProperty(Constants.AUTO_ACCEPT_ALERTS);
    private static final boolean AUTO_GRANT_PERMISSIONS = PropertyUtils.getBooleanProperty(Constants.AUTO_GRANT_PERMISSIONS);
    private static final String APP_PUSH_TIMEOUT = PropertyUtils.getProperty(Constants.APP_PUSH_TIMEOUT);
    private static final boolean BS_NETWORK_LOGS = PropertyUtils.getBooleanProperty(Constants.BS_NETWORK_LOGS);
    private static final String DEVICE_UDID = PropertyUtils.getProperty(Constants.DEVICE_UDID);
    private static final boolean IS_RELEASE_VERSION = PropertyUtils.getBooleanProperty(Constants.IS_RELEASE_VERSION);

    private static final ThreadLocal<AppiumDriver<WebElement>> mobileDrivers = new ThreadLocal<>();

    public static synchronized AppiumDriver<WebElement> getMobileDriver() {
        return mobileDrivers.get();
    }

    public static synchronized void setMobileDrivers(AppiumDriver<WebElement> driver) {
        mobileDrivers.set(driver);
    }

    public static synchronized Boolean doesDriverExist() {
        return getMobileDriver() != null;
    }

    public static synchronized Boolean isDriverSessionActive() {
        return getDriverSessionId() != null;
    }

    private static synchronized SessionId getDriverSessionId() {
        SessionId sessionId = null;

        if (doesDriverExist()) {
            sessionId = MobileDriverManager.getMobileDriver().getSessionId();
        }

        return sessionId;
    }

    public static IOSDriver<WebElement> createIOSDriver() {
        LoggingManager.logInfo(MobileDriverManager.class, "=====> Creating new iOS driver ===");

        IOSDriver<WebElement> driver = null;
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AUTOMATION_NAME);
        capabilities.setCapability("autoDismissAlerts", AUTO_DISMISS_ALERTS);
        capabilities.setCapability("autoAcceptAlerts", AUTO_ACCEPT_ALERTS);
        capabilities.setCapability("autoGrantPermissions", AUTO_GRANT_PERMISSIONS);

        capabilities.setCapability(MobileCapabilityType.APP, getAppAbsolutePath("ios" + File.separator + APP_NAME));

        capabilities.setCapability(IOSMobileCapabilityType.WDA_LAUNCH_TIMEOUT, 500000);
        capabilities.setCapability(IOSMobileCapabilityType.USE_NEW_WDA, true);

        if(isReleaseVersion()){
            capabilities.setCapability("udid", DEVICE_UDID);
            capabilities.setCapability("noReset", false);
        }

        try {
            driver = new IOSDriver<>(new URL(APPIUM_URL), capabilities);
        } catch (MalformedURLException exception) {
            LoggingManager.logError(MobileDriverManager.class, "Error when creating iOS driver", exception);
        }

        return driver;
    }

    public static IOSDriver<WebElement> createLambdaTestIOSDriver() {
        LoggingManager.logInfo(MobileDriverManager.class, "=====> Creating new LambdaTest iOS driver ===");

        IOSDriver<WebElement> driver = null;
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("build", BUILD);
        capabilities.setCapability("name",PLATFORM_NAME+" "+DEVICE_NAME+" "+PLATFORM_VERSION);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AUTOMATION_NAME);
        capabilities.setCapability("autoDismissAlerts", AUTO_DISMISS_ALERTS);
        capabilities.setCapability("autoAcceptAlerts", AUTO_ACCEPT_ALERTS);
        capabilities.setCapability("autoGrantPermissions", AUTO_GRANT_PERMISSIONS);

        capabilities.setCapability(MobileCapabilityType.APP, APP_NAME);

        capabilities.setCapability("isRealMobile", true);

        try {
            driver = new IOSDriver<>(new URL(APPIUM_URL), capabilities);
        } catch (MalformedURLException exception) {
            LoggingManager.logError(MobileDriverManager.class, "Error when creating iOS driver", exception);
        }

        return driver;
    }

    public static EnhancedIOSDriver<WebElement> createAzureIOSDriver() {
        LoggingManager.logInfo(MobileDriverManager.class, "=====> Creating new Azure iOS driver ===");

        EnhancedIOSDriver<WebElement> driver = null;
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AUTOMATION_NAME);
        capabilities.setCapability("autoDismissAlerts", AUTO_DISMISS_ALERTS);
        capabilities.setCapability("autoAcceptAlerts", AUTO_ACCEPT_ALERTS);
        capabilities.setCapability("autoGrantPermissions", AUTO_GRANT_PERMISSIONS);

        capabilities.setCapability("appPushTimeout", APP_PUSH_TIMEOUT); // Set appPushTimeout to 5 minutes (300000 milliseconds)
        capabilities.setCapability("udid", DEVICE_UDID);
        capabilities.setCapability(MobileCapabilityType.APP, getAppAbsolutePath("ios" + File.separator + APP_NAME));

        capabilities.setCapability(IOSMobileCapabilityType.WDA_LAUNCH_TIMEOUT, 500000);

        try {
            driver = Factory.createIOSDriver(new URL(APPIUM_URL), capabilities);
        } catch (MalformedURLException exception) {
            LoggingManager.logError(MobileDriverManager.class, "Error when creating Azure iOS driver", exception);
        }

        return driver;
    }

    public static IOSDriver<WebElement> createBrowserStackIOSDriver() {
        LoggingManager.logInfo(MobileDriverManager.class, "=====> Creating new BrowserStack iOS driver ===");

        IOSDriver<WebElement> driver = null;
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AUTOMATION_NAME);
        capabilities.setCapability(MobileCapabilityType.APP, BS_APP_URL);
        capabilities.setCapability("autoDismissAlerts", AUTO_DISMISS_ALERTS);
        capabilities.setCapability("autoAcceptAlerts", AUTO_ACCEPT_ALERTS);
        capabilities.setCapability("autoGrantPermissions", AUTO_GRANT_PERMISSIONS);
        capabilities.setCapability("browserstack.networkLogs", BS_NETWORK_LOGS);

        capabilities.setCapability(IOSMobileCapabilityType.WDA_LAUNCH_TIMEOUT, 500000);

        try {
            driver = new IOSDriver<>(new URL("http://" + BS_USERNAME + ":" + BS_ACCESS_KEY + "@" + BS_SERVER + "/wd/hub"), capabilities);
        } catch (MalformedURLException exception) {
            LoggingManager.logError(MobileDriverManager.class, "Error when creating BrowserStack iOS driver", exception);
        }

        return driver;
    }

    public static void createMobileDriver() {
        if (System.getenv("APP_CENTER_TEST") != null) {
            switch (System.getenv("APP_CENTER_TEST")) {
                case "1":
                    if (PropertyUtils.isPlatform(Platform.ANDROID)) {
                        // TODO: add android
                    } else if (PropertyUtils.isPlatform(Platform.IOS)) {
                        EnhancedIOSDriver<WebElement> driver = createAzureIOSDriver();
                        setMobileDrivers(driver);
                    }
                    break;
                case "2":
                    if (PropertyUtils.isPlatform(Platform.ANDROID)) {
                        // TODO: add android
                    } else if (PropertyUtils.isPlatform(Platform.IOS)) {
                        IOSDriver<WebElement> driver = createBrowserStackIOSDriver();
                        setMobileDrivers(driver);
                    }
                    break;
                case "3":
                    if (PropertyUtils.isPlatform(Platform.ANDROID)) {
                        // TODO: add android
                    } else if (PropertyUtils.isPlatform(Platform.IOS)) {
                        IOSDriver<WebElement> driver = createLambdaTestIOSDriver();
                        setMobileDrivers(driver);
                    }
                    break;
                default:
                    break;
            }
        } else {
            if (PropertyUtils.isPlatform(Platform.IOS)) {
                IOSDriver<WebElement> driver = createIOSDriver();
                setMobileDrivers(driver);
            } else if (PropertyUtils.isPlatform(Platform.ANDROID)) {
                // TODO: add android
            }
        }
    }

    private static String getAppAbsolutePath(String appName) {
        return PropertyUtils.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "apps" + File.separator + appName;
    }

    // Tests are running on app (true) or ipa (false)
    public static boolean isReleaseVersion(){
        return IS_RELEASE_VERSION;
    }

    public static void tearDownDriver() {
        AppiumDriver<WebElement> driver = getMobileDriver();
        if (driver != null) {
            driver.quit();
        }
    }
}
