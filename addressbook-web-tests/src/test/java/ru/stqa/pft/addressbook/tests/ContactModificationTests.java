package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().clickLinkHome();
        if (app.getContacts().all().size() == 0) {
            app.getContacts().clickAddNew();
            app.getContacts().modify(new ContactData()
                    .withFirstname(app.properties.getProperty("contact2.firstName"))
                    .withLastname(app.properties.getProperty("contact2.lastName"))
                    .withMobilePhone(app.properties.getProperty("contact2.mobilePhone"))
                    .withEmail(app.properties.getProperty("contact2.email"))
                    , true);
            app.getContacts().submitContact();
            app.goTo().goToHomePage();
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.getContacts().all();
        ContactData modifiedContact = before.iterator().next();

        ContactData modContactData = new ContactData()
                .withId((modifiedContact.getId()))
                .withFirstname(app.properties.getProperty("contact2.firstName"))
                .withLastname(app.properties.getProperty("contact2.lastName"));

        app.getContacts().selectContactById(modContactData.getId());
        app.getContacts().modify(modContactData, false);

        app.goTo().clickLinkHome();
        Contacts after = app.getContacts().all();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(modContactData)));
    }
}