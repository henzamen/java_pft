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
    public void testContactDeletion() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();

        app.getContacts().modifyContactById(deletedContact.getId());
        app.getContactHelper().pressDeleteAndAgree();
        app.goTo().clickLinkHome();
        BaseHelper.reloadPage();

        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size() - 1));

        before.remove(before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}