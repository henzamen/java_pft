package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.BaseHelper;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDelitionTests extends TestBase {

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
    public void testContactDeletion() {
        Contacts before = app.getContactHelper().all();
        ContactData deletedContact = before.iterator().next();

        app.getContacts().selectContactById(deletedContact.getId());
        app.getContactHelper().pressDeleteAndAgree();
        app.goTo().clickLinkHome();
        BaseHelper.reloadPage();

        Contacts after = app.getContactHelper().all();
        assertThat(after.size(), equalTo(before.size() - 1));

        before.remove(before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}