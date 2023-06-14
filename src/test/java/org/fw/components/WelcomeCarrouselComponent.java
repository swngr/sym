package org.fw.components;

import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.managers.MobileDriverManager;
import org.fw.pages.BasePage;
import org.openqa.selenium.WebElement;

public class WelcomeCarrouselComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "image")
    private WebElement mainImage;

    @iOSXCUITFindBy(accessibility = "label")
    private WebElement mainTitle;

    @iOSXCUITFindBy(accessibility = "description")
    private WebElement mainMessage;


    @iOSXCUITFindBy(accessibility = "button_next")
    private WebElement newsCarouselButton;

    @iOSXCUITFindBy(accessibility = "Next")
    private WebElement nextButton;

    @iOSXCUITFindBy(accessibility = "Done")
    private WebElement doneButton;


    private static boolean newsCarouselIsDone = false;

    public boolean isMainImageDisplayed() {
        return isDisplayed(mainImage, "Unable to display \"Image 1\" element");
    }

    public void goThroughNewsCarousel() {
        waitUntilVisible(newsCarouselButton, "Unable to display \"Next\" button");
        while (newsCarouselButton.isDisplayed()) {
            if (getTextCarrouselButton().equals("Next")){
                click(nextButton, "Unable to click \"Next\" button");
            }
            else if (getTextCarrouselButton().equals("Done")){
                click(doneButton, "Unable to click \"Done\" button");
                break;
            }
        }
    }

    public void newsCarousel() {
        if (newsCarouselIsDone) {
            return;
        }
        newsCarouselIsDone = true;
        goThroughNewsCarousel();
    }

    private WebElement foundNextButton(){
        if(MobileDriverManager.getMobileDriver().findElements(MobileBy.AccessibilityId("Next")).isEmpty()){
            return null;
        }else{
            return MobileDriverManager.getMobileDriver().findElements(MobileBy.AccessibilityId("Next")).get(0);
        }
    }

    public void closeWelcomeCarrousel(){
        if(isDisplayed()) {
            do{
                click(foundNextButton(), "Unable to click \"Next\" button");
            }while(foundNextButton()!=null);
            click(doneButton, "Unable to click \"Next\" button");
        }
    }

    public String getTextCarrouselButton() {
        return newsCarouselButton.getText();
    }

    public boolean isCarrouselTexDisplayed() {
        return newsCarouselButton.isDisplayed();
    }

    private boolean isDisplayed() {
        WebElement buttonNext = waitUntilElementIsVisibleCustomTime(MobileBy.AccessibilityId("button_next"), 10);
        return buttonNext!=null;
    }

    public String getTextWelcomeCarrouselButton() {
        return newsCarouselButton.getText();
    }
}
