package org.NTA.BT3;

import org.NTA.common.WebUI;
import org.NTA.common.TestBase;
import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Login extends TestBase {
    String URL ="https://cms.anhtester.com/login";
    String username = "admin@example.com";
    String password = "123456";
    By usernameInput = By.xpath("//input[@id='email']");
    By passwordInput = By.xpath("//input[@id='password']");
    By loginButton = By.xpath("//button[normalize-space()='Login']");
    By menuListItems = By.xpath("//button[@class='aiz-mobile-toggler']");
    By messageLoginFailed = By.xpath("//span[@data-notify='message']");
    WebUI webUI = new WebUI();

    @Test
    public void VerifyLoginSuccess()  {
        WebUI.openURL(URL);
        WebUI.enterText(usernameInput,username);
        WebUI.enterText(passwordInput,password);
        WebUI.clickElement(loginButton);
        Assert.assertTrue(WebUI.isElementDisplayed(menuListItems));
    }
    @Test
    public void VerifyLoginFailed() {
        WebUI.openURL(URL);
        WebUI.enterText(usernameInput,username);
        WebUI.enterText(passwordInput,"passwordFail");
        WebUI.clickElement(loginButton);
        Assert.assertTrue(WebUI.isElementDisplayed(messageLoginFailed));
    }
}
