package org.NTA.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;


public class TestBase {
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    public void openBrowser() {
        driver.set(new ChromeDriver());
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        /*getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().timeouts().setScriptTimeout(Duration.ofSeconds(20));*/
    }

    @AfterMethod
    public void afterMethod() {
        driver.get().quit();
    }
}
