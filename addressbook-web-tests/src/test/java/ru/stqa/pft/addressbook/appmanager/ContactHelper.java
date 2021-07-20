package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;


public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void pressDeleteAndAgree() throws InterruptedException {
        clickDeleteContact();
        //   acceptAllert();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> contactCheckBoxes = wd.findElements(By.cssSelector("input[name='selected[]']"));
        for (WebElement checkBox : contactCheckBoxes) {
            int idContact = Integer.parseInt(checkBox.getAttribute("id"));
            WebElement row = checkBox.findElement(By.xpath("./../.."));   //точка означает начало поиска с текущего элемента; две точки - к родительскому элементу
            String lastName = row.findElement(By.xpath("td[2]")).getText();
            String firstName = row.findElement(By.xpath("td[3]")).getText();
            String allPhones = row.findElement(By.xpath("td[6]")).getText();
            String[] phones = row.findElement(By.xpath("td[6]")).getText().split("\n");
            contacts.add(new ContactData()
                    .withId(idContact)
                    .withFirstname(firstName)
                    .withLastname(lastName)
                    .withAllPhones(allPhones));
        }
        return contacts;
    }

    public Contacts allWithoutId() {
        Contacts contacts = new Contacts();
        List<WebElement> contactCheckBoxes = wd.findElements(By.cssSelector("input[name='selected[]']"));
        for (WebElement checkBox : contactCheckBoxes) {
            WebElement row = checkBox.findElement(By.xpath("./../.."));
            String lastName = row.findElement(By.xpath("td[2]")).getText();
            String firstName = row.findElement(By.xpath("td[3]")).getText();
            contacts.add(new ContactData().withFirstname(firstName).withLastname(lastName));
        }
        return contacts;
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

    public void selectContactById(int id) {
        initContactModificationById(id);
    }

    public void clickEdit(int id) {
        initContactModificationById(id);
    }

    public void modify(ContactData contactData, boolean creation) {
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
        BaseHelper.clickUpdate();
    }

    public void clickAddNew() {
        click(By.linkText("add new"));
    }

    public void submitContact() {
        clickSubmit();
    }

    public boolean isThereAnyContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return  new ContactData()
                .withId(contact.getId())
                .withFirstname(firstname)
                .withLastname(lastname)
                .withHomePhone(home)
                .withMobilePhone(mobile)
                .withWorkPhone(work);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector("input[id='" + id + "']"));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }
}