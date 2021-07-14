package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;


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

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void clickEditContact(int index) {

        click(By.xpath("(//input[@name='selected[]'])[" + (index + 1) +"]/../..//img[@title='Edit']"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            if (contactData.getGroup() != null) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
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

    public List<ContactData> getContactList() {
        List<WebElement> contactRows = wd.findElements(By.xpath("//tr[@name='entry']"));
        int contactsSize = contactRows.size();
        List<ContactData> contacts = new ArrayList<ContactData>();

        for (int i =0; i<contactsSize; i++) {
            int contactRowNum = i+1;
            String lastName = wd.findElement(By.xpath("(//tr[@name='entry']/td[2])["+ contactRowNum +"]")).getText();
            String firstName = wd.findElement(By.xpath("(//tr[@name='entry']/td[3])["+ contactRowNum +"]")).getText();
            contacts.add(new ContactData().withFirstname(firstName).withLastname(lastName));
        }
        return contacts;
    }
}