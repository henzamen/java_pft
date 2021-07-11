package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestData;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
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

        app.goTo().clickLinkHome();
        if (!app.getContactHelper().isThereAnyContact()) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(TestData.groupName2).withFooter(TestData.footerText2));
            app.goTo().clickLinkHome();
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
            app.goTo().goToHomePage();
        }

        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().clickEditContact(before.size() - 1);

        app.getContactHelper().fillContactForm(contactDataNew,false);
        app.getContactHelper().updateContactFieldByName("address", address);
        app.getContactHelper().clickUpdate();
        app.goTo().clickLinkHome();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size());

        Comparator<? super ContactData> byLastname = (g1, g2) -> g1.getLastname().compareTo(g2.getLastname());
        before.sort(byLastname);
        after.sort(byLastname);
        Assert.assertEquals(before, after);

        Comparator<? super ContactData> byFirstname = (g1, g2) -> g1.getFirstname().compareTo(g2.getFirstname());
        before.sort(byFirstname);
        after.sort(byFirstname);
        Assert.assertEquals(before, after);
    }
}