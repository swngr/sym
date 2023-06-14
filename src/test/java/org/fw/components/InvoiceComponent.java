package org.fw.components;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.fw.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InvoiceComponent extends BasePage {

    @iOSXCUITFindBy(accessibility = "close_left_nav_bar")
    private WebElement cancelButton;

    @FindBy(name = "Search for Patients...")
    private WebElement searchForPatients;

    @FindBy(name = "Estimates")
    private WebElement estimates;

    @FindBy(name = "Invoices")
    private WebElement invoices;

    @FindBy(name = "Payments")
    private WebElement payments;

    @FindBy(name = "Price List")
    private WebElement priceList;

    @FindBy(name = "More")
    private WebElement more;

    private final NewInvoiceComponent newInvoiceComponent = new NewInvoiceComponent();

    public NewInvoiceComponent getNewInvoiceComponent() {
        return newInvoiceComponent;
    }

    public void tapCancel() {
        click(cancelButton, "Unable to click to the \"Cancel\" element");
    }

    public boolean isDisplayed() {
        return (isDisplayed(searchForPatients, "Unable to find the \"Search for Patients...\" element") &&
                isDisplayed(estimates, "Unable to find the \"Estimates\" element") &&
                isDisplayed(invoices, "Unable to find the \"Invoices\" element") &&
                isDisplayed(payments, "Unable to find the \"Payments\" element") &&
                isDisplayed(priceList, "Unable to find the \"Price List\" element") &&
                isDisplayed(more, "Unable to find the \"More\" element"));
    }

}
