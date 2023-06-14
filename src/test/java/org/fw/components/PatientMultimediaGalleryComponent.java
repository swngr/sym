package org.fw.components;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.managers.MobileDriverManager;
import org.fw.pages.BasePage;
import org.fw.utils.Constants;
import org.openqa.selenium.WebElement;

public class PatientMultimediaGalleryComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "close_left_nav_bar")
    private WebElement cancelButton;

    private WebElement getPatientPhotoGallery() {
        WebElement patientPhotoGallery = null;
        if (!MobileDriverManager.getMobileDriver().findElementsByAccessibilityId("cell_image_0_0").isEmpty()) {
            patientPhotoGallery = MobileDriverManager.getMobileDriver().findElementsByAccessibilityId("cell_image_0_0").get(0);
        }
        return patientPhotoGallery;
    }

    public void tapTheMostRecentAddedPhoto() {
        // Swipe down until the "mostRecentPhoto" button is displayed
        do {
            swipe(Constants.Direction.DOWN);
        }while(getPatientPhotoGallery() == null);
        swipe(Constants.Direction.DOWN);
        click(getPatientPhotoGallery(), "Unable to click to the \"mostRecentPhoto\" button");
    }

    public void tapCloseButton() {
        click(cancelButton, "Unable to click the \"cancelButton\" button");
    }

}
