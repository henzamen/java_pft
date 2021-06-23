package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDelitionTests extends TestBase {

    @Test
    public void testContactDeletion() {

        app.getNavigationHelper().clickLinkHome();
        app.getContactHelper().selectFirstContact();
        app.getContactHelper().pressDeleteAndAgree();

    }

}