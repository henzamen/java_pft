package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestData;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        Contacts before = app.getContactHelper().all();
        ContactData contact = new ContactData().withFirstname(TestData.firstName1).withLastname(TestData.lastName1);
        app.getContactHelper().clickAddNew();
        app.getContactHelper().fillForm(contact, true);
        app.getContactHelper().submitContact();
        app.goTo().goToHomePage();

        Contacts after = app.getContactHelper().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(contact.withLastname(contact.getLastname()))));
    }
}