package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestData;
import ru.stqa.pft.addressbook.model.ContactRequiredData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {

        String address = "Voronezh";
        ContactRequiredData contactRequiredDataNew = new ContactRequiredData(
                TestData.firstName2,
                TestData.lastName2,
                "+7473 1234888",
                "gensam@bk.ru"
        );

        app.getNavigationHelper().clickLinkHome();
        app.getContactHelper().clickFirstImgEdit(TestData.fullName);
        app.getContactHelper().fillContactForm(contactRequiredDataNew);
        app.getContactHelper().updateContactFieldByName("address", address);
        app.getContactHelper().clickUpdate();
        app.getNavigationHelper().clickLinkHome();

    }

}
