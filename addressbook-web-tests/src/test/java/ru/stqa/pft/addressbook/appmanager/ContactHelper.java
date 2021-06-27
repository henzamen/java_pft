package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactRequiredData;


public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void pressDeleteAndAgree() {
        clickDeleteContact();
        acceptAllert();
    }

    public void clickDeleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void clickUpdateLower() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void clickFirstImgEdit() {
        click(By.xpath("(//input[@name='selected[]'])[1]/../..//img[@title='Edit']"));
    }

    public void updateContactFieldByName(String fieldByName, String value) {
        type(By.xpath("//*[@name='" + fieldByName + "']"), value);
    }

    public void selectFirstContact() {
        click(By.xpath("(//input[@name='selected[]'])[1]"));
    }

    public void fillContactForm(ContactRequiredData contactRequiredData, boolean creation) {
        type(By.name("firstname"), contactRequiredData.getFirstname());
        type(By.name("lastname"), contactRequiredData.getLastname());
        type(By.name("mobile"), contactRequiredData.getMobile());
        type(By.name("email"), contactRequiredData.getEmail());

        if (creation) {
            if (contactRequiredData.getGroup() != null) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactRequiredData.getGroup());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void submitContact() {
        clickSubmit();
    }

    public boolean isThereAnyContact() {
        return isElementPresent(By.name("selected[]"));
    }
}