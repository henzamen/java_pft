package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestData;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData().withFirstname(TestData.firstName1).withLastname(TestData.lastName1);
        app.getContactHelper().addNewContact();
        app.getContactHelper().fillContactForm(contact, true);
        app.getContactHelper().submitContact();
        app.goTo().goToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byLastname = (g1, g2) -> g1.getLastname().compareTo(g2.getLastname());
        before.sort(byLastname);
        after.sort(byLastname);
        Assert.assertEquals(before, after);

        Comparator<? super ContactData> byFirstname = (g1, g2) -> g1.getFirstname().compareTo(g2.getFirstname());
        before.sort(byFirstname);
        after.sort(byFirstname);
        Assert.assertEquals(before, after);
    }
}