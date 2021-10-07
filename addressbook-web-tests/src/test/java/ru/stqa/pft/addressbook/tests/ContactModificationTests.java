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
        if(app.db().contacts().size()==0) {
            app.goTo().clickLinkHome();
            app.getContacts().clickAddNew();
            app.getContacts().modify(new ContactData()
                    .withFirstname(app.properties.getProperty("contact1.firstName"))
                    .withLastname(app.properties.getProperty("contact1.lastName"))
                    .withMobilePhone(app.properties.getProperty("contact1.mobile"))
                    .withWorkPhone(app.properties.getProperty("contact1.work"))
                    .withEmail(app.properties.getProperty("contact1.email"))
                    .withAddress(app.properties.getProperty("contact1.address"))
                    .withNickname(app.properties.getProperty("contact1.nickname")),
                    true);
            app.goTo().goToHomePage();
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData modContactData = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstname(app.properties.getProperty("contact2.firstName"))
                .withLastname(app.properties.getProperty("contact2.lastName"))
                .withMobilePhone(app.properties.getProperty("contact2.mobile"))
                .withWorkPhone(app.properties.getProperty("contact2.work"))
                .withEmail(app.properties.getProperty("contact2.email"))
                .withAddress(app.properties.getProperty("contact2.address"))
                .withNickname(app.properties.getProperty("contact2.nickname"));

        app.getContacts().modifyContactById(modContactData.getId());
        app.getContacts().modify(modContactData, false);

        app.goTo().clickLinkHome();
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(modContactData)));
    }
}