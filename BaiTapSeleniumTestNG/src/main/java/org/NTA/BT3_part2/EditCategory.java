package org.NTA.BT3_part2;

import org.NTA.BT3.Category;
import org.NTA.common.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.NTA.common.WebUI.*;

public class EditCategory extends TestBase {
    By editButton = By.xpath("(//a[@title='Edit'])[1]");
    By nameEditInput = By.xpath("//input[@id='name']");
    By saveButton = By.xpath("//button[normalize-space()='Save']");
    By categoryButton = By.xpath("//span[normalize-space()='Category']");
    By messageDataNotify = By.xpath("//span[@data-notify='message']");

    String nameTest ="Nguyễn Tuấn Anh";
    String nameEdit = "Nguyễn Tuấn Anh edited";
    Category category = new Category();
    @Test
    public void CheckEditCategory(){
        category.AddCategory(nameTest);
        category.SearchNameCategory(nameTest);
        editCategory(nameEdit);
    }
    public void editCategory(String nameEdit){
        clickElement(editButton);
        enterText(nameEditInput,nameEdit);
        clickElement(saveButton);
        Assert.assertTrue(isElementDisplayed(messageDataNotify));
        clickElement(categoryButton);
    }

}
