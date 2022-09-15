package com.a101.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends A101BasePage{

    @FindBy(xpath = "//div[contains(@id,'basket-item')]//img")
    private WebElement itemInBasketImg;

    public String getItemTitle(){
        return itemInBasketImg.getAttribute("alt");
    }

    @FindBy(xpath = "//span[@class='current-price']")
    private WebElement itemInBasketUnitPrice;

    public String getItemUnitPriceAsString(){
        return itemInBasketUnitPrice.getText();
    }

    public double getItemUnitPriceAsDouble(){
        String unitPrice = getItemUnitPriceAsString();
        String unitPriceWithoutCurrency = unitPrice.substring(1);
        return Double.parseDouble(unitPriceWithoutCurrency);
    }

    @FindBy(xpath = "//div[contains(@class,'quantity')]/input")
    private WebElement itemInBasketQuantity;

    public String getQuantityAsString(){
        return itemInBasketQuantity.getAttribute("value");
    }

    public int getQuantityAsInt(){
        return Integer.parseInt(getQuantityAsString());
    }

    @FindBy(xpath = "//div[contains(@id,'basket-item')]//div[@class='column price']")
    private WebElement itemInBasketTotalPrice;

    public String getItemTotalPriceAsString(){
        return itemInBasketTotalPrice.getText();
    }

    public double getItemTotalPriceAsDouble(){
        String totalPriceWithoutCurrency = getItemTotalPriceAsString().substring(1);
        return Double.parseDouble(totalPriceWithoutCurrency);
    }

    @FindBy(xpath = "//div[@class='summary']//a[@title='Sepeti Onayla']")
    private WebElement confirmCartButton;

    public void confirmCart(){
        confirmCartButton.click();
    }
}