package org.fw.components;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.pages.BasePage;
import org.openqa.selenium.WebElement;

public class PushNotificationComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "NotificationShortLookView")
    private WebElement pushNotificationView;

    public boolean isDisplayed() {
        // Customized seconds of timeout, because sometimes the push notification take too long to be displayed (sometimes more than 1 minute).
        return isDisplayedCustomTime(pushNotificationView , 60, "Unable to display the \"pushNotificationView\" component");
    }

    public void tapPushNotificationComponent(){
        click(pushNotificationView, "Unable to click the \"pushNotificationView\" component");
    }

}
