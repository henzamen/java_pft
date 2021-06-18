package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestData;

public class ContactDelitionTests extends TestBase {

    @Test
    public static void testContactDeletion() {

        app.getNavigationHelper().clickLinkHome();
        app.getContactHelper().selectContact(TestData.firstName2 + " " + TestData.lastName2);
        app.getContactHelper().pressDeleteAndAgree();

    }

}