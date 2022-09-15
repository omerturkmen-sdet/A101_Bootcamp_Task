package com.a101.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.a101.utilities.Utils.*;

public class CheckoutPage extends A101BasePage{

    @FindBy(xpath = "//a[normalize-space()='ÜYE OLMADAN DEVAM ET']")
    private WebElement continueWithoutRegisterButton;

    @FindBy(name = "user_email")
    private WebElement emailInputBox;

    @FindBy(xpath = "//button[normalize-space()='DEVAM ET']")
    private WebElement emailConfirmButton;

    @FindBy(xpath = "//a[contains(@class,'new-address')]")
    private WebElement addAddressButton;

    @FindBy(name = "title")
    private WebElement addressTitle;

    @FindBy(name = "first_name")
    private WebElement firstNameInputBox;

    @FindBy(name = "last_name")
    private WebElement lastNameInputBox;

    @FindBy(name = "phone_number")
    private WebElement phoneNumberInputBox;

    @FindBy(name = "city")
    private WebElement cityDropdown;

    @FindBy(name = "township")
    private WebElement townshipDropdown;

    @FindBy(name = "district")
    private WebElement districtDropdown;

    @FindBy(name = "line")
    private WebElement fullAddressInputBox;

    @FindBy(xpath = "//button[normalize-space()='KAYDET']")
    private WebElement addressSubmitButton;

    @FindBy(xpath = "//input[@type='radio'][@name='shipping']")
    private List<WebElement> shippingOptionList;


    /**
     * After adding address click to navigate payment page
     */
    @FindBy(xpath = "//button[normalize-space()='Kaydet ve Devam Et']")
    private WebElement navigatePaymentPageButton;

    public void proceedWithoutRegistration(){
        continueWithoutRegisterButton.click();
        emailInputBox.sendKeys("examplemail@mail.com");
        emailConfirmButton.click();
        waitForVisibility(addAddressButton);
        addAddressButton.click();
    }

    public void fillAddressForm(){
        addressTitle.sendKeys("Test Address");
        firstNameInputBox.sendKeys(faker.name().firstName());
        lastNameInputBox.sendKeys(faker.name().lastName());
        phoneNumberInputBox.sendKeys(faker.phoneNumber().phoneNumber());

        selectRandomOptionFromDropdown(cityDropdown);
        waitFor(1);
        selectRandomOptionFromDropdown(townshipDropdown);
        waitFor(1);
        selectRandomOptionFromDropdown(districtDropdown);

        fullAddressInputBox.sendKeys(faker.address().fullAddress());
        addressSubmitButton.click();

    }

    public void navigatePayment(){
        selectShipment();
        waitForClickAbility(navigatePaymentPageButton).click();
    }
    private void selectShipment(){
        if (shippingOptionList.size() > 1){
            int randomNumber = getRandomNumber(shippingOptionList.size());
            WebElement selectedShippingOption = shippingOptionList.get(randomNumber-1);
            clickWithJS(selectedShippingOption);
        }
    }
}
