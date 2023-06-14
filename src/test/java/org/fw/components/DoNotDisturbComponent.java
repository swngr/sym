package org.fw.components;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.pages.BasePage;
import org.openqa.selenium.WebElement;

public class DoNotDisturbComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "button_dnd_cancel")
    private WebElement cancelBellButton;

    @iOSXCUITFindBy(accessibility = "button_dnd_apply")
    private WebElement applyChangesButton;

    @iOSXCUITFindBy(accessibility = "action_switch_dnd")
    private WebElement enableDisableButton;

    public boolean isDoNotDisturbCompDisplayed(){
        return isDisplayed(getPageTitleElement(), "Unable to display the \"Do Not Disturb\" component\"");
    }

    public void tapEnableDisableButton(){
        click(enableDisableButton, "Unable to click the \"Enable Disable\" button");
    }

    public boolean isEnableDisableButtonSelected(){
        return isSelected(enableDisableButton, "Unable to enable/disable the \"Enable Disable\" button\"");
    }

    public void tapApplyChanges(){
        click(applyChangesButton, "Unable to click the \"Apply Changes\" button");
    }

}
