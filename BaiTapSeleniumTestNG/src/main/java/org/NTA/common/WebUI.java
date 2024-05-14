package org.NTA.common;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class WebUI extends TestBase{
    private static final int EXPLICIT_WAIT_TIMEOUT = 2;
    private static final int WAIT_PAGE_LEADED_TIMEOUT = 20;
    private static final WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_PAGE_LEADED_TIMEOUT),Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT)) ;
    public static void openURL(String URL) {
        getDriver().get(URL);
        waitForPageLoad();
    }
    public static void sleep(int second) throws InterruptedException {
        Thread.sleep(second * 1000);
    }
    public static void switchToWindows(int number){
        Set<String> windows = getDriver().getWindowHandles();
        String firstWindow = (String)windows.toArray()[number];
        getDriver().switchTo().window(firstWindow);
    }
    public static void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_PAGE_LEADED_TIMEOUT),Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
        boolean pageLoaded = wait.until(webDriver -> {
            String jsLoad = "return document.readyState";
            String jQueryLoad = "return jQuery.active == 0";
            return ((String) ((JavascriptExecutor) webDriver).executeScript(jsLoad)).equals("complete")
                    && ((Boolean) ((JavascriptExecutor) webDriver).executeScript(jQueryLoad));
        });
        if (!pageLoaded) {
            System.out.println("Trang web không tải xong sau thời gian chờ.");
        }
    }

    // Phương thức kiểm tra sự hiện diện của phần tử
    public static boolean VerifyIsElementDisplayed(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public static void waitForElementVisible(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_PAGE_LEADED_TIMEOUT),Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    // Phương thức xóa giá trị ô văn bản
    public static void clearText(By locator) {
        WebElement element = getDriver().findElement(locator);
        element.clear();
    }

    // Phương thức nhập giá trị vào ô văn bản
    public static void enterText(By locator, String text)  {
        waitForPageLoad();
        waitForElementVisible(locator);
        WebElement element = getDriver().findElement(locator);
        element(locator).sendKeys(text);
    }

    // Phương thức nhấp chuột vào phần tử
    public static void clickElement(By locator) {
        waitForPageLoad();
        waitForElementVisible(locator);

        element(locator).click();
    }

    // Phương thức kiểm tra hiển thị của phần tử
    public static boolean isElementDisplayed(By locator) {
        waitForPageLoad();
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_PAGE_LEADED_TIMEOUT),Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    public static String getTextElement(By by) {
        waitForPageLoad();
        waitForElementVisible(by);
        return element(by).getText();
    }
    // Load lại page Web
    public static void refreshPage(){
        getDriver().navigate().refresh();
    }
    public static int getListElements(By locator){
        List<WebElement> listOfDivElements = getDriver().findElements(locator);
        return listOfDivElements.size();
    }
    public static WebElement element(By by) {
        return getDriver().findElement(by);
    }

    //Alert
    public static void AlertDismiss(){
        getDriver().switchTo().alert().dismiss();
    }
    public static void AlertAccept(){
        getDriver().switchTo().alert().accept();
    }
    public static void AlertSendKeys(String text){
        getDriver().switchTo().alert().sendKeys(text);
    }
    public static String AlertClick(){
        return getDriver().switchTo().alert().getText();
    }
}
