package com.a101.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {



    private Driver(){}

    private static WebDriver driver;

    public static WebDriver get(){

        if(driver==null){
            String browser = "chrome";
            switch (browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    ChromeOptions handlingSSL = new ChromeOptions();
                    handlingSSL.setAcceptInsecureCerts(true);
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
            }
        }
        return driver;
    }

    public static void closeDriver(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }
    /*
    private Driver(){}
    private static WebDriver driver;

    public static WebDriver get(){
        if (driver == null){

            String browser = "chrome";

            switch (browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().addArguments("--disable-notifications"));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "safari":
                    if (System.getProperty("os.name").contains("mac")){
                        WebDriverManager.safaridriver().setup();
                        driver = new SafariDriver();
                    }else {
                        System.out.println("Your OS is not proper for safari");
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                    }
                    break;
            }
        }
        return driver;
    }


    public static void closeDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
*/
}
