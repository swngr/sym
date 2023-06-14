package org.fw.pages;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class ReportsPage extends BasePage {

    @iOSXCUITFindBy(accessibility = "Report Dashboard")
    private WebElement reportsDashboard;

    @iOSXCUITFindBy(accessibility = "Cancel")
    private WebElement cancelButton;



    public boolean isReportsDashboardDisplayed() {
        waitUntilVisible(reportsDashboard, " \"Reports Dashboard\" is not being displayed");
        return reportsDashboard.isDisplayed();
    }

    public void tapOnCancelButton() {
        waitUntilVisible(cancelButton, " \"Cancel\" button is not being displayed");
        click(cancelButton, "Unable to tap on \"Cancel\" button");
    }




}
