package com.a101.pages;

import com.a101.utilities.CreditCardInformation;
import com.a101.utilities.Driver;
import com.a101.utilities.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.a101.utilities.Utils.*;
public class PaymentPage extends A101BasePage{
    @FindBy(xpath = "//div[contains(@class,'payment-tab')]")
    private WebElement paymentPageSubtitle;

    @FindBy(xpath = "//div[@data-slug='masterpass']//input[@name='name']")
    private WebElement fullNameInputBox;

    @FindBy(xpath = "//div[@data-slug='masterpass']//input[@type='tel']")
    private WebElement cardNumberInputBox;

    @FindBy(xpath = "//div[@data-slug='masterpass']//select[@name='card_month']")
    private WebElement cardExpireMonthDropdown;

    @FindBy(xpath = "//div[@data-slug='masterpass']//select[@name='card_year']")
    private WebElement cardExpireYearDropdown;

    @FindBy(xpath = "//div[@data-slug='masterpass']//input[@name='card_cvv']")
    private WebElement cvcInputBox;

    @FindBy(name = "installment")
    private WebElement installmentRadioButton;

    @FindBy(id = "agrement2")
    private WebElement acceptContractCheckbox;

    @FindBy(xpath = "(//div[@id='js-orders-complete-button']/button)[2]")
    private WebElement completeOrderButton;

    @FindBy(xpath = "//div[.='Kart bilgilerinizi kontrol ediniz.']")
    private WebElement creditCardErrorMessage;

    public WebElement getCreditCardErrorMessage(){
        waitForVisibility(creditCardErrorMessage);
        return creditCardErrorMessage;
    }

    public WebElement getPaymentPageSubtitle(){
        return paymentPageSubtitle;
    }

    public void makePaymentWithFakeData(){
        waitForVisibility(paymentPageSubtitle);
        fullNameInputBox.sendKeys(faker.name().fullName());
        cardNumberInputBox.sendKeys(faker.business().creditCardNumber());
        selectRandomOptionFromDropdown(cardExpireMonthDropdown);
        selectRandomOptionFromDropdown(cardExpireYearDropdown);
        cvcInputBox.sendKeys("845");
    }

    public void makePaymentWithValidData(CreditCardInformation creditCardInformation){
        waitForVisibility(paymentPageSubtitle);
        fullNameInputBox.sendKeys(creditCardInformation.ownerFullName);
        waitFor(1);
        cardNumberInputBox.sendKeys(creditCardInformation.cardNumber);
        waitFor(1);
        new Select(cardExpireMonthDropdown).selectByValue(creditCardInformation.expireMonth);
        waitFor(1);
        new Select(cardExpireYearDropdown).selectByValue(creditCardInformation.expireYear);
        waitFor(1);
        cvcInputBox.sendKeys(creditCardInformation.cvc);
        waitFor(1);
        acceptingContractAndCompleteOrder();
    }

    public void acceptingContractAndCompleteOrder(){
        waitForVisibility(installmentRadioButton);
        clickWithJS(acceptContractCheckbox);
        clickWithJS(completeOrderButton);
    }

    @FindBy(xpath = "(//div[@class='content']/div)[3]")
    private WebElement productPrice;

    @FindBy(xpath = "(//div[@class='content']/div)[2]")
    private WebElement productTitle;

    @FindBy(xpath = "(//div[@class='title']/span)[4]")
    private WebElement productQuantity;

    public String getProductPrice(){
//        waitForVisibility(productPrice);
        return productPrice.getText();
    }

    public String getProductTitle(){
//        waitForVisibility(productTitle);
        return productTitle.getText();
    }

    public String getQuantity(){
//        waitForVisibility(productQuantity);
        return productQuantity.getText().substring(0,1);
    }

}
