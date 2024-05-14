package org.NTA.BT3;

import org.NTA.common.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.NTA.common.WebUI.*;

public class Category extends TestBase {
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
    Login loginTest = new Login();
    String nameTest ="Tuanbuffet123";

    @Test
    public void CheckAddCategory(){
        AddCategory(nameTest);
        SearchNameCategory(nameTest);
        for (int i = 1; i<= getListElements(By.xpath("//tbody/tr")); i++){
            Assert.assertTrue(getTextElement(By.xpath("//tbody/tr[" + i + "]")).contains(nameTest), "Tên trên Web không chứa tên trên data");
        }
    }
    public void AddCategory(String name) {
        loginTest.VerifyLoginSuccess();
        try {
            clickElement(productButton);
        }
        catch (Exception e){
            clickElement(menuListItemsButton);
            clickElement(productButton);
        }
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
    }
    public void AddFiles(By locatorName){
        clickElement(locatorName);
        enterText(searchYourFilesInput,"TestDemoQuynhTester");
        clickElement(itemsBannerImage);
        clickElement(addFilesButton);
    }
    public void SearchNameCategory(String name) {
        enterText(searchInput,name + Keys.ENTER);
    }
}
