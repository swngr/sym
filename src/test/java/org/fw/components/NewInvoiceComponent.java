package org.fw.components;

import org.fw.managers.MobileDriverManager;
import org.fw.pages.BasePage;
import org.fw.utils.PermissionAlertDialogSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NewInvoiceComponent extends BasePage {

    private WebElement getCloseButton(){
        return waitUntilElementIsVisible(By.xpath("//XCUIElementTypeButton[@name='New Invoice']"));
    }

    public void tapCloseLocation() {
        click(getCloseButton(), "Unable to click to the \"New Invoice\" element");
    }

    public WebElement getNewEstimateInvoiceComp() {
        return MobileDriverManager.getMobileDriver().findElementByXPath("//XCUIElementTypeNavigationBar[@name='New Invoice']/..");
    }

    public void tapClose() {
        PermissionAlertDialogSettings.setDiscardEstimateInvoice();
        WebElement cancelButton = getNewEstimateInvoiceComp().findElement(By.name("Cancel"));
        click(cancelButton, "Unable to click to the \"Cancel\" element");
    }

}
