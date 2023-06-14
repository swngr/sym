package org.fw.components;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.pages.BasePage;
import org.fw.utils.Constants;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GenderComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "back_nav_bar_btn")
    private WebElement backButton;

    @iOSXCUITFindBy(accessibility = "Female")
    private WebElement genderFemale;

    @iOSXCUITFindBy(accessibility = "Male")
    private WebElement genderMale;

    @iOSXCUITFindBy(accessibility = "Others")
    private WebElement genderOthers;

    public void submitGenderForm(Constants.Gender patientGender) {
        waitUntilVisible(genderFemale, "Unable to show the \"genderFemale\" element");
        List<WebElement> genders = new ArrayList<>();
        genders.add(genderFemale);
        genders.add(genderMale);
        genders.add(genderOthers);
        for (WebElement cell: genders) {
            if (cell.getText().equals(patientGender.getName())){
                if (cell.getText().equals(Constants.Gender.FEMALE.getName())){
                    click(backButton, "Unable to click to the \"Back\" button");
                }else{
                    click(cell, "Unable to click to the \""+patientGender.getName()+"\" element");
                }
                return;
            }
        }
    }

}
