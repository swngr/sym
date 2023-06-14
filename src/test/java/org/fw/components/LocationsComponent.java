package org.fw.components;


import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.managers.MobileDriverManager;
import org.fw.pages.BasePage;
import org.fw.utils.WaitUtils;
import org.openqa.selenium.WebElement;

public class LocationsComponent extends BasePage {


    @iOSXCUITFindBy(iOSNsPredicate = "type == \"XCUIElementTypeSheet\"")
    private WebElement allLocationsList;


    public void clickOnChangeLocation(String location) {
        WaitUtils.sleep(getClass(), 3);
        click(MobileDriverManager.getMobileDriver().findElementByAccessibilityId (location),"Unable to click on: " + location);
    }

}
