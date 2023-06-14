package org.fw.components;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.managers.MobileDriverManager;
import org.fw.pages.BasePage;
import org.openqa.selenium.WebElement;

public class ReferralSourceComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "back_nav_bar_btn")
    private WebElement backButton;

    public void submitReferralSource(String patientReferralSource) {
        WebElement cellElement = MobileDriverManager.getMobileDriver().findElementByAccessibilityId("cell_parent_" + patientReferralSource);
        click(cellElement, "Unable to click to the \"Referral Source\" element");
    }

}
