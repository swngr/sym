package org.fw.components;

import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.offset.PointOption;
import org.fw.managers.MobileDriverManager;
import org.fw.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.List;

public class DateOfBirthComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "Show year picker")
    private WebElement showDatePickerButton;

    @iOSXCUITFindBy(accessibility = "Hide year picker")
    private WebElement hideDatePickerButton;

    @iOSXCUITFindBy(accessibility = "date_system_picker")
    private WebElement dateSystemPicker;

    @FindBy(name = "1980")
    private WebElement yearDatePicker;

    public void submitDobForm(Calendar cal){
        click(showDatePickerButton, "Unable to click to the \"Forward\" button");
        List<WebElement> datePickerWheels = dateSystemPicker.findElements(By.className("XCUIElementTypePickerWheel"));

        sendKeys(datePickerWheels.get(0), new DateFormatSymbols().getMonths()[cal.get(Calendar.MONTH)], "Unable to send key to the \"Month\" date picker");
//        setValues((MobileElement) datePickerWheels.get(0), new DateFormatSymbols().getMonths()[cal.get(Calendar.MONTH)], "Unable to send key to the \"Month\" date picker");
        sendKeys(datePickerWheels.get(1), String.valueOf(cal.get(Calendar.YEAR)), "Unable to send key to the \"Year\" date picker");
//        setValues((MobileElement) datePickerWheels.get(1), String.valueOf(cal.get(Calendar.YEAR)), "Unable to send key to the \"Year\" date picker");
        click(hideDatePickerButton, "Unable to click to the \"Forward\" button");
        WebElement dayDatePicker = MobileDriverManager.getMobileDriver().findElement(By.xpath("//*[contains(@name, '"+cal.get(Calendar.DAY_OF_MONTH)+"')]"));
        click(dayDatePicker, "Unable to send key to the \"Day\" date picker");
        closeDatePicker();
    }

    private void closeDatePicker(){
        // Click somewhere to close datepicker
        TouchAction touch = new TouchAction(MobileDriverManager.getMobileDriver());
        touch.tap(PointOption.point(100, 100)).perform();
    }

}
