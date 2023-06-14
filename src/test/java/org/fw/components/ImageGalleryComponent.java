package org.fw.components;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.managers.MobileDriverManager;
import org.fw.pages.BasePage;
import org.fw.utils.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ImageGalleryComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "Back")
    private WebElement backButton;

    @FindBy(xpath = "//XCUIElementTypeToolbar[@name=\"Toolbar\"]/XCUIElementTypeOther/XCUIElementTypeOther")
    private WebElement toolbarShareButton;

    public boolean isDisplayed(){
        WaitUtils.sleep(getClass(), 3);
        return (isDisplayed(backButton, "Unable to display the \"backButton\" button") &&
                isDisplayed(toolbarShareButton, "Unable to display the \"toolbarShareButton\" input"));
    }

    public void tapBackButton(){
        click(backButton, "Unable to click the \"backButton\" button");
    }

}
