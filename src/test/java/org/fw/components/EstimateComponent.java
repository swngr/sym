package org.fw.components;

import org.fw.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EstimateComponent extends BasePage {

    @FindBy(name = "Cancel")
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

    private final NewEstimateComponent newEstimateComponent = new NewEstimateComponent();

    public NewEstimateComponent getNewEstimateComponent() {
        return newEstimateComponent;
    }

    public void tapCancel() {
        click(cancelButton, "Unable to click to the \"Cancel\" element");
    }

    public boolean isDisplayed() {
        return (isDisplayed(cancelButton, "Unable to find the \"Cancel\" element") &&
                isDisplayed(searchForPatients, "Unable to find the \"Search for Patients...\" element") &&
                isDisplayed(estimates, "Unable to find the \"Estimates\" element") &&
                isDisplayed(invoices, "Unable to find the \"Invoices\" element") &&
                isDisplayed(payments, "Unable to find the \"Payments\" element") &&
                isDisplayed(priceList, "Unable to find the \"Price List\" element") &&
                isDisplayed(more, "Unable to find the \"More\" element"));
    }

}
