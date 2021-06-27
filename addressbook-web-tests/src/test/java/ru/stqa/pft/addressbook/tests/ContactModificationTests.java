package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestData;
import ru.stqa.pft.addressbook.model.ContactRequiredData;
import ru.stqa.pft.addressbook.model.GroupData;

import static java.lang.Thread.sleep;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() throws InterruptedException {

        String address = "Voronezh";
        ContactRequiredData contactRequiredDataNew = new ContactRequiredData(
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
                    new ContactRequiredData(
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
        sleep(1000);
        app.getContactHelper().clickFirstImgEdit();
        sleep(1000);
        app.getContactHelper().fillContactForm(contactRequiredDataNew,false);
        app.getContactHelper().updateContactFieldByName("address", address);
        app.getContactHelper().clickUpdate();
        app.getNavigationHelper().clickLinkHome();
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup(TestData.groupName2);
        app.getGroupHelper().deleteGroupLower();
    }
}