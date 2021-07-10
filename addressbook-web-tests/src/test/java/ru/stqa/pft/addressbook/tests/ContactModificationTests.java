package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestData;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {

        String address = "Voronezh";
        ContactData contactDataNew = new ContactData(
                TestData.firstName2,
                TestData.lastName2,
                null,
                null,
                null
        );

        app.getNavigationHelper().clickLinkHome();
        if (!app.getContactHelper().isThereAnyContact()) {
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().createGroup(new GroupData(TestData.groupName2, null, TestData.footerText2));
            app.getNavigationHelper().clickLinkHome();
            app.getContactHelper().addNewContact();
            app.getContactHelper().fillContactForm(
                    new ContactData(
                            TestData.firstName2,
                            TestData.lastName2,
                            TestData.mobile,
                            TestData.email,
                            TestData.groupName2
                    ), true
            );
            app.getContactHelper().submitContact();
            app.getNavigationHelper().goToHomePage();
        }

        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().clickEditContact(before.size() - 1);

        app.getContactHelper().fillContactForm(contactDataNew,false);
        app.getContactHelper().updateContactFieldByName("address", address);
        app.getContactHelper().clickUpdate();
        app.getNavigationHelper().clickLinkHome();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size());

        before.sort((ContactData s1, ContactData s2)->s1.getLastname().compareTo(s2.getLastname()));
        System.out.println(before);
        after.sort((ContactData s1, ContactData s2)->s1.getLastname().compareTo(s2.getLastname()));
        Assert.assertEquals(before, after);

        before.sort((ContactData s1, ContactData s2)->s1.getFirstname().compareTo(s2.getFirstname()));
        System.out.println(before);
        after.sort((ContactData s1, ContactData s2)->s1.getFirstname().compareTo(s2.getFirstname()));
        Assert.assertEquals(before, after);

//        app.getNavigationHelper().gotoGroupPage();
//        app.getGroupHelper().selectGroupName(TestData.groupName2);
//        app.getGroupHelper().deleteGroupLower();
    }
}