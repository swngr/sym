package org.fw.pages;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class BillingLocationsPage extends BasePage{

    @iOSXCUITFindBy(accessibility = "New Invoice")
    private WebElement newInvoiceButton;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == \"Billing Locations\"`]")
    private WebElement billingLocationsWindowHeader;


    public String getTextBillingLocationsWindowHeader() {
        waitUntilVisible(billingLocationsWindowHeader, "Unable to display \"Billing Locations\" window");
        return billingLocationsWindowHeader.getText();
    }



}
