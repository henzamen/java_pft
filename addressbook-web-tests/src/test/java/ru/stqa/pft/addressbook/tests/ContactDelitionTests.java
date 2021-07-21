package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.BaseHelper;
import ru.stqa.pft.addressbook.appmanager.TestData;
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
                    .withFirstname(TestData.firstName2)
                    .withLastname(TestData.lastName2)
                    .withMobilePhone(TestData.mobile)
                    .withEmail(TestData.email), true);
            app.getContacts().submitContact();
            app.goTo().goToHomePage();
        }
    }

    @Test
    public void testContactDeletion() throws InterruptedException {
        Contacts before = app.getContactHelper().all();
        int beforeSize = app.getContacts().getContactCount();
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