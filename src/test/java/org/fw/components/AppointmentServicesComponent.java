package org.fw.components;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class AppointmentServicesComponent {

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label CONTAINS \"Automation\"`]")
    private WebElement automationService;
}
