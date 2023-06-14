package org.fw.components;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.pages.BasePage;
import org.openqa.selenium.WebElement;

public class MediaComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "close_left_nav_bar")
    private WebElement closeButton;

    @iOSXCUITFindBy(accessibility = "Up and down Chevrons")
    private WebElement upAndDownChevrons;

    @iOSXCUITFindBy(accessibility = "sort")
    private WebElement sort;

    @iOSXCUITFindBy(accessibility = "add")
    private WebElement add;

    public boolean isDisplayed(){
        return (isEnabled(upAndDownChevrons, "Unable to find the \"upAndDownChevrons\" element") &&
                isEnabled(sort, "Unable to find the \"sort\" element") &&
                isEnabled(add, "Unable to find the \"add\" element"));
    }

    public void tapClose() {
        click(closeButton, "Unable to click the \"closeButton\" button");
    }

}
