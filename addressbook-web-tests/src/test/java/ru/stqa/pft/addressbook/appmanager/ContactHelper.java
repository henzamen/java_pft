package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Random;


public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void pressDeleteAndAgree() {
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
            String address = row.findElement(By.xpath("td[4]")).getText();
            String allPhones = row.findElement(By.xpath("td[6]")).getText();
            String allEmails = row.findElement(By.xpath("td[5]")).getText();
            contacts.add(new ContactData()
                    .withId(idContact)
                    .withFirstname(firstName)
                    .withLastname(lastName)
                    .withAddress(address)
                    .withAllPhones(allPhones)
                    .withAllEmails(allEmails));
        }
        return contacts;
    }

    public void clickDeleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void modifyContactById(int id) {
        initContactModificationById(id);
    }

    public void modify(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("address"), contactData.getAddress());
        type(By.name("nickname"), contactData.getNickname());

        if (creation) {
//            if (contactData.getGroup() != null) {
//                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
//            }
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

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withFirstname(firstname)
                .withLastname(lastname)
                .withHomePhone(home)
                .withMobilePhone(mobile)
                .withWorkPhone(work)
                .withEmail(email)
                .withEmail2(email2)
                .withEmail3(email3)
                .withAddress(address);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector("input[id='" + id + "']"));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();

    }

    public Boolean isContactInGroup(ContactData contact, GroupData group) {
        Boolean contInGr = false;
        for (GroupData gr : contact.getGroups()) {
            if (group.getValue() == gr.getValue()) {
                contInGr = true;
                break;
            }
        }
        return contInGr;
    }



    public ContactData randomContact(Contacts contacts) {
        int size = contacts.size();
        int curId = 0;
        int randId = new Random().nextInt(size);

        for (ContactData contact : contacts) {
            if (curId == randId) {
                return contact;
            }
            curId++;
        }
        return null;
    }

    public ContactData getLastContact(Contacts contacts) {
        int lastId = contacts.stream().mapToInt((g) -> g.getId()).max().getAsInt();
        return getContactById(contacts, lastId);
    }

    public ContactData getContactById(Contacts contacts, int id) {
        for (ContactData contact : contacts) {
            if (contact.getId() == id) {
                return contact;
            }
        }
        return null;
    }


    public void addToGroup(ContactData contact, GroupData group){
        selectGroupToShow("[none]"); //обоход ошибки приложения
        wd.findElement(By.id(String.valueOf(contact.getId()))).click();
        Select toGroups = new Select (wd.findElement(By.name("to_group")));
        toGroups.selectByValue(Integer.toString(group.getValue()));

        wd.findElement(By.name("add")).click();
        wd.findElement(By.linkText("home")).click();
    }

    public void selectGroupToShow (String valueText) {
        Select showGroups = new Select (wd.findElement(By.name("group")));
        showGroups.selectByValue(valueText);
    }

    public void removeContactFromGroup(ContactData contact, GroupData group) {
        selectGroupToShow(Integer.toString(group.getValue()));
        selectContactById(contact.getId());
        wd.findElement(By.name("remove")).click();
    }

}