package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.BaseHelper;
import ru.stqa.pft.addressbook.appmanager.TestData;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactDelitionTests extends TestBase {

    @Test
    public void testContactDeletion() throws InterruptedException {

        app.goTo().clickLinkHome();
        if (!app.getContactHelper().isThereAnyContact()) {
            app.goTo().clickLinkHome();
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
            app.goTo().goToHomePage();
        }

        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().pressDeleteAndAgree();
        app.goTo().clickLinkHome();
        BaseHelper.reloadPage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size() - 1);

        before.remove(before.size() - 1);
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