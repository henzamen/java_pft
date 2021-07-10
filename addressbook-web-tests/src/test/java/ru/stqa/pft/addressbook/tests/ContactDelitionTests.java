package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.BaseHelper;
import ru.stqa.pft.addressbook.appmanager.TestData;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class ContactDelitionTests extends TestBase {

    @Test
    public void testContactDeletion() throws InterruptedException {

        app.getNavigationHelper().clickLinkHome();
        if (!app.getContactHelper().isThereAnyContact()) {
            app.getNavigationHelper().clickLinkHome();
            app.getContactHelper().addNewContact();
            app.getContactHelper().fillContactForm(
                    new ContactData(
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

        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().pressDeleteAndAgree();
        app.getNavigationHelper().clickLinkHome();
        BaseHelper.reloadPage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size() - 1);

        before.remove(before.size() - 1);
        before.sort((ContactData s1, ContactData s2)->s1.getLastname().compareTo(s2.getLastname()));
        System.out.println(before);
        after.sort((ContactData s1, ContactData s2)->s1.getLastname().compareTo(s2.getLastname()));
        Assert.assertEquals(before, after);

        before.sort((ContactData s1, ContactData s2)->s1.getFirstname().compareTo(s2.getFirstname()));
        System.out.println(before);
        after.sort((ContactData s1, ContactData s2)->s1.getFirstname().compareTo(s2.getFirstname()));
        Assert.assertEquals(before, after);
    }
}