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
        Contacts before = app.getContactHelper().allWithoutId();
        ContactData contact = new ContactData()
                .withFirstname(TestData.firstName1)
                .withLastname(TestData.lastName1);
        app.getContactHelper().clickAddNew();
        app.getContactHelper().modify(contact, true);
        app.goTo().goToHomePage();

        Contacts after = app.getContactHelper().allWithoutId();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(contact
                .withLastname(contact.getLastname())
                .withFirstname(contact.getFirstname()))));
    }
}