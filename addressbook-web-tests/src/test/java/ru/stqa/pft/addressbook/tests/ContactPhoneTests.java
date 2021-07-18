package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhones() {
        app.goTo().goToHomePage();
        ContactData contact = app.getContacts().all().iterator().next();
      //  ContactData contactInfoFromEditForm = app.getContacts().infoFromEditForm(contact);
    }
}
