package com.a101.pages;

import com.a101.utilities.Driver;
import com.a101.utilities.Utils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.a101.utilities.Utils.waitForClickAbility;
import static com.a101.utilities.Utils.waitForVisibility;

public class ProductPage extends A101BasePage{

    public void selectProductSize(String size){
        Driver.get().findElement(By.xpath(
                "//div[@class='size-select-wrapper']/a[@title='" + size + "']"))
                .click();
    }

    @FindBy(xpath = "//div[@class='col-sm-3']//button[contains(@class,'add-to-basket')]")
    private WebElement addToBasketButton;

    @FindBy(xpath = "//a[@class='go-to-shop']")
    private WebElement displayCartButton;

    public WebElement getAddToBasketButton(){
        waitForVisibility(addToBasketButton);
        return addToBasketButton;
    }
    public void addToBasketAndDisplay(){
        waitForClickAbility(addToBasketButton).click();
        waitForVisibility(displayCartButton);
        waitForClickAbility(displayCartButton).click();
    }

    @FindBy(xpath = "//div[@class='selected-variant-text']/span")
    private WebElement selectedColorText;

    public String getSelectedColor(){
        return selectedColorText.getText();
    }

    @FindBy(xpath = "//div[@class='product-heading']/h1")
    private WebElement productTitle;

    public String getProductTitle(){
        return productTitle.getText();
    }

    public String getProductUnitPrice(){
        String unitPrice = "";
        for (WebElement element : Driver.get().findElements(By.xpath("//div[@class='price single']/span"))) {
            unitPrice += element.getText();
        }
        return unitPrice;
    }

    @FindBy(xpath = "//div[@class='quantity-ct']/input")
    private WebElement productQuantity;

    public String getProductQuantity(){
        return productQuantity.getAttribute("value");
    }

}
