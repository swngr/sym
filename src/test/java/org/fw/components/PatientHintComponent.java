package org.fw.components;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.pages.BasePage;
import org.openqa.selenium.WebElement;

public class PatientHintComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "Patient Search Tips:")
    private WebElement title;

    @iOSXCUITFindBy(accessibility = "Examples:")
    private WebElement examples;

    @iOSXCUITFindBy(accessibility = "Will find any name that contains ‘Rob’ such as Robert or Roberta.")
    private WebElement example;

    @iOSXCUITFindBy(accessibility = "Will find any name matching ‘Rob’ exactly.")
    private WebElement example2;

    @iOSXCUITFindBy(accessibility = "Will find anyone whose birthday is April 03, 1980.")
    private WebElement example3;

    public boolean isDisplayed() {
        return (title.isDisplayed() &&
                examples.isDisplayed() &&
                example.isDisplayed() &&
                example2.isDisplayed() &&
                example3.isDisplayed());
    }

    public void closeHint(){
        tapOutsideElement();
    }

}
