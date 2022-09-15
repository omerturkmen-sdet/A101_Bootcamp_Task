package com.a101.pages;

import com.a101.utilities.Driver;

import com.a101.utilities.Utils;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import static com.a101.utilities.Utils.*;

public abstract class A101BasePage {

    Actions actions;
    WebDriverWait wait;
    Faker faker;
    Select select;

    public A101BasePage() {
        PageFactory.initElements(Driver.get(),this);
        actions = new Actions(Driver.get());
        wait = new WebDriverWait(Driver.get(),Duration.ofSeconds(10));
        faker = new Faker();
    }

    protected static String selectedProductTitle = "";
    @FindBy(xpath = "//ul[@class='desktop-menu']/li/a")
    protected List<WebElement> categoriesList;

    public WebElement navigateCategory(String categoryName) {

        for (WebElement webElement : categoriesList) {
            if (webElement.getAttribute("title").contains(categoryName)) {
                return webElement;
            }
        }
        return null;
    }

    private String pageUrl = "https://www.a101.com.tr/";

    public void navigateProduct(String categoryName, String subCategoryName) {
        navigateCategoryForDifferentBasePage(categoryName, subCategoryName);

        if (!Driver.get().getCurrentUrl().equals(pageUrl)) {
            return;
        }
        moveToElement(navigateCategory(categoryName));
        Driver.get().findElement(By.xpath("//a[@title='" + subCategoryName + "']")).click();
    }


    /**
     * If user land on different base page it will handle exception by selecting product with proper locators
     *
     * @param categoryName
     * @param subCategoryName
     */
    private void navigateCategoryForDifferentBasePage(String categoryName, String subCategoryName) {
        try {
            WebElement categories = Driver.get().findElement(By.xpath("//*[normalize-space()='Kategoriler']"));
            moveToElement(categories);

            WebElement selectedCategory = Driver.get().findElement(By.xpath("//a[contains(@title,'" + categoryName + "')]"));
            moveToElement(selectedCategory);

            Driver.get().findElement(By.xpath("//a[contains(@title,'" + subCategoryName + "')]")).click();

        } catch (Exception e) {}
    }


    public void acceptCookies() {
        By cookieAccept = By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll");
        waitForClickAbility(cookieAccept).click();
        waitForInvisibility(cookieAccept);
    }


    public void lastAssertionAfterPaymentConfirmation(){
        wait.until(ExpectedConditions.urlContains("approve"));
        Assertions.assertTrue(Driver.get().getCurrentUrl().contains("approve"));
    }
}
