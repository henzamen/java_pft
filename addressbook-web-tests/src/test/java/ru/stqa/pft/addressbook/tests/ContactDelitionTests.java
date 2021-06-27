package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestData;
import ru.stqa.pft.addressbook.model.ContactRequiredData;

public class ContactDelitionTests extends TestBase {

    @Test
    public void testContactDeletion() {

        app.getNavigationHelper().clickLinkHome();
        if (!app.getContactHelper().isThereAnyContact()) {
            app.getNavigationHelper().clickLinkHome();
            app.getContactHelper().addNewContact();
            app.getContactHelper().fillContactForm(
                    new ContactRequiredData(
                            TestData.firstName2,
                            TestData.lastName2,
                            TestData.mobile,
                            TestData.email,
                            null
                    ), true
            );
            app.getContactHelper().submitContact();
            app.getNavigationHelper().goToHomePage();
        }
        app.getContactHelper().selectFirstContact();
        app.getContactHelper().pressDeleteAndAgree();
    }
}