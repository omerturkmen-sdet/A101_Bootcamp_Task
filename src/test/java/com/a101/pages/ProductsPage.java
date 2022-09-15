package com.a101.pages;

import com.a101.utilities.Driver;
import com.a101.utilities.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends A101BasePage {

    public void selectColor(String color) {
        //waitForProductsPage();
        Driver.get().findElement(By.xpath
                        ("//input[contains(@name,'colour')][@value='" + color + "']"))
                .click();
    }

    @FindBy(className = "filters")
    private WebElement filtersElement;

    public WebElement getFiltersElement(){
        Utils.waitForVisibility(filtersElement);
        return filtersElement;
    }

    @FindBy(xpath = "//label[@class='selected-filters-item']/*")
    private List<WebElement> selectedFilterItems;

    public List<String> getSelectedFilter() {

        List<String> selectedFiltersList = new ArrayList<>();

        for (WebElement selectedFilter : selectedFilterItems) {
            if (selectedFilter.getTagName().equals("input")) {
                selectedFiltersList.add(selectedFilter.getAttribute("value"));
            } else if (selectedFilter.getTagName().equals("span")) {
                selectedFiltersList.add(selectedFilter.getAttribute("textContent"));
            }
        }
        return selectedFiltersList;
    }

    @FindBy(xpath = "//ul[@class='product-list-general']/li")
    private List<WebElement> products;

    public void selectRandomProduct() {
        int productNumber = products.size();
        int selectedProductIndex = Utils.getRandomNumber(productNumber);
        while (true) {
            String selectedProductLocator = "//ul[@class='product-list-general']/li[" + selectedProductIndex + "]";
            String selectedProductSaleOption = "(" + selectedProductLocator + "//div)[5]";
            String selectedProductLink = selectedProductLocator + "//a";
            if (!Driver.get().findElement(By.xpath(selectedProductSaleOption)).getAttribute("outerText").equals("Tükendi")) {
                new WebDriverWait(Driver.get(), Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath(selectedProductLink)));
                WebElement selectedProduct = Driver.get().findElement(By.xpath(selectedProductLink));
                selectedProductTitle = selectedProduct.getAttribute("title");
                Utils.clickWithJS(selectedProduct);
                return;
            }
            System.out.println("Product Sold Out. Another product will be selected");
        }
    }
}
