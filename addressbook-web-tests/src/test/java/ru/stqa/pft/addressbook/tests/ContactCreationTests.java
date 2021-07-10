package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestData;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData(TestData.firstName1, TestData.lastName1,
                null, null, null);
        app.getContactHelper().addNewContact();
        app.getContactHelper().fillContactForm(contact, true);
        app.getContactHelper().submitContact();
        app.getNavigationHelper().goToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        before.sort((ContactData s1, ContactData s2) -> s1.getLastname().compareTo(s2.getLastname()));
        System.out.println(before);
        after.sort((ContactData s1, ContactData s2) -> s1.getLastname().compareTo(s2.getLastname()));
        Assert.assertEquals(before, after);

        before.sort((ContactData s1, ContactData s2) -> s1.getFirstname().compareTo(s2.getFirstname()));
        System.out.println(before);
        after.sort((ContactData s1, ContactData s2) -> s1.getFirstname().compareTo(s2.getFirstname()));
        Assert.assertEquals(before, after);
    }
}