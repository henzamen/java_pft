package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.BaseHelper;
import ru.stqa.pft.addressbook.appmanager.TestData;
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
            app.getContacts().fillForm(new ContactData()
                    .withFirstname(TestData.firstName2)
                    .withLastname(TestData.lastName2)
                    .withMobile(TestData.mobile)
                    .withEmail(TestData.email),true);
            app.getContacts().submitContact();
            app.goTo().goToHomePage();
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.getContacts().all();
        ContactData modifiedContact = before.iterator().next();
        app.getContacts().clickEdit(before.size() - 1);

        ContactData modifiedContactData = new ContactData()
                .withFirstname(TestData.firstName2)
                .withLastname(TestData.lastName2);
        app.getContacts().fillForm(modifiedContactData,false);
        BaseHelper.clickUpdate();
        app.goTo().clickLinkHome();

        Contacts after = app.getContacts().all();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(modifiedContactData)));
    }
}