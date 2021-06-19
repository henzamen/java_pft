package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactRequiredData;


public class ContactHelper extends BaseHelper{

    public ContactHelper(WebDriver wd) {
        super(wd);

    }

    public void pressDeleteAndAgree()  {
        clickDeleteContact();
        acceptAllert();

    }

    public static void clickDeleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void clickUpdateLower() {
        click(By.xpath("(//input[@name='update'])[2]"));

    }

    public static void clickImgEdit (String nameLastName) {
        click(By.xpath("//input[@title='Select (" + nameLastName + ")']/../..//img[@title='Edit']"));

    }

    public static void updateContactFieldByName(String fieldByName, String value) {

        type(By.xpath("//*[@name='" + fieldByName +"']"),value);

    }

    public void selectContact(String nameLastName) {
        click(By.xpath("//input[@title='Select (" + nameLastName + ")']"));

    }

    public void fillContactForm(ContactRequiredData contactRequiredData) {
        type(By.name("firstname"), contactRequiredData.getFirstname());
        type(By.name("lastname"), contactRequiredData.getLastname());
        type(By.name("mobile"), contactRequiredData.getMobile());
        type(By.name("email"), contactRequiredData.getEmail());

    }

    public void addNewContact() {
        click(By.linkText("add new"));

    }

}