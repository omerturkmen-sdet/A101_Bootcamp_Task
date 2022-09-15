package com.a101.utilities;

import io.cucumber.java.en_old.Ac;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class Utils {

    private static WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(10));

    public static void waitFor(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(timeOutInSeconds));
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    public static void selectRandomOptionFromDropdown(WebElement dropdownElement){
        waitForClickAbility(dropdownElement);
        Select select = new Select(dropdownElement);
        int index = getRandomNumber(select.getOptions().size());
        if (index == select.getOptions().size()){
            index--;
        }else if (index == 0){
            index = 1;
        }
        select.selectByIndex(index);
    }

    public static void clickWithJS(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor)Driver.get();
        executor.executeScript("arguments[0].click();", element);
    }

    public static int getRandomNumber(int max){
        Random random = new Random();
        int randomNumber = random.nextInt(max) + 1;
        return randomNumber;
    }

    public static WebElement waitForClickAbility(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForClickAbility(By locator){
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public static void waitForVisibility(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForInvisibility(WebElement element){
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitForInvisibility(By locator){
        wait.until(ExpectedConditions.invisibilityOf(Driver.get().findElement(locator)));
    }

    public static void moveToElement(WebElement element){
        Actions actions = new Actions(Driver.get());
        actions.moveToElement(element).perform();
    }

    public static String convertLowerWithEnglishCharacters(String word){
        word = word.toLowerCase().replace("ı","i").replace("ç","c");
        return word;
    }
}
