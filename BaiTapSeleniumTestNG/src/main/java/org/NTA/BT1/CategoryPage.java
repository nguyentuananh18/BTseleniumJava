package org.NTA.BT1;

import org.NTA.common.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static org.NTA.common.WebUI.*;
import static org.NTA.common.WebUI.isElementDisplayed;

public class CategoryPage extends TestBase {
    String URL ="https://cms.anhtester.com/login";
    String username = "admin@example.com";
    String password ="123456";
    String name ="NguyenTuanAnh123ND";

    By usernameInput = By.xpath("//input[@id='email']");
    By passwordInput = By.xpath("//input[@id='password']");
    By loginButton = By.xpath("//button[normalize-space()='Login']");
    By menuListItems = By.xpath("//button[@class='aiz-mobile-toggler']");
    By messageLoginFailed = By.xpath("//span[@data-notify='message']");

    //page category
    By productButton = By.xpath("//span[normalize-space()='Products']");
    By categoryButton = By.xpath("//span[normalize-space()='Category']");
    By addCategoryButton = By.xpath("//span[normalize-space()='Add New category']");
    By searchButton = By.xpath("//button[normalize-space()='Search']");
    By firstItemButton = By.xpath("//div[@class=\"card-body\"]//div[@class=\"row gutters-5\"]/div[1]");
    By titleItem = By.xpath("//span[@class='text-truncate title']");
    By menuListItemsButton = By.xpath("//button[@class='aiz-mobile-toggler']");
    By itemList = By.xpath("//div[@class='row gutters-5']/div");
    By nameInput = By.xpath("//input[@id='name']");
    By parentCategoryButton = By.xpath("//div[contains(text(),'No Parent')]");
    By parentCategoryInput = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    By orderingNumberInput = By.xpath("//input[@id='order_level']");
    By typeCategoryButton = By.xpath("//div[contains(text(),'Physical')]");
    By chooseFileBannerButton = By.xpath("//label[contains(text(),'Banner')]//following-sibling::div");
    By chooseFileIconButton = By.xpath("//label[contains(text(),'Icon')]//following-sibling::div");
    By metaTitleInput = By.xpath("//input[@placeholder='Meta Title']");
    By metaDescriptionInput = By.xpath("//textarea[@name='meta_description']");
    By filteringAttributesButton = By.xpath("//div[contains(text(),'Nothing selected')]");
    By itemsBannerImage = By.xpath("//div[@class='aiz-uploader-all clearfix c-scrollbar-light']//h6/span[contains(normalize-space(),'TestDemoQuynhTester')]");
    By searchYourFilesInput = By.xpath("//input[@placeholder='Search your files']");
    By addFilesButton = By.xpath("//button[normalize-space()='Add Files']");
    By saveButton = By.xpath("//button[normalize-space()='Save']");
    By searchInput = By.xpath("//input[@id='search']");
    public boolean VerifyLoginSuccess()  {
        openBrowser();
        openURL(URL);
        enterText(usernameInput,username);
        enterText(passwordInput,password);
        clickElement(loginButton);
        return isElementDisplayed(menuListItems);
    }
    public boolean VerifyLoginFailed() {
        openBrowser();
        openURL(URL);
        enterText(usernameInput,username);
        enterText(passwordInput,"passwordFail");
        clickElement(loginButton);
        return !isElementDisplayed(menuListItems);
    }
    public boolean CheckAddCategory(){
        clickElement(menuListItemsButton);
        clickElement(productButton);
        clickElement(categoryButton);
        clickElement(addCategoryButton);
        enterText(nameInput, name);
        clickElement(parentCategoryButton);
        enterText(parentCategoryInput,"--aka");
        enterText(orderingNumberInput, "5");
        AddFiles(chooseFileBannerButton);
        AddFiles(chooseFileIconButton);
        enterText(metaTitleInput,"TuanbuffetPro");
        enterText(metaDescriptionInput,"Pro nhất Nam Định");
        clickElement(saveButton);
        return searchNameCategory(name);
    }

    public void AddFiles(By locator){
        clickElement(locator);
        enterText(searchYourFilesInput,"TestDemoQuynhTester");
        clickElement(itemsBannerImage);
        clickElement(addFilesButton);
    }
    public boolean searchNameCategory(String name) {
        enterText(searchInput,name + Keys.ENTER);
        boolean status = false;
        for (int i = 1; i<= getListElements(By.xpath("//tbody/tr")); i++){
            if (!getTextElement(By.xpath("//tbody/tr")).contains(name)){
                break;
            }
            if (i == getListElements(By.xpath("//tbody/tr"))){
                status = true;
            }
        }
        return status;
    }

    public static void main(String[] args) {
        CategoryPage addCategoryPage = new CategoryPage();
        addCategoryPage.VerifyLoginSuccess();
        System.out.println(addCategoryPage.CheckAddCategory());
    }
}
