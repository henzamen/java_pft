package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestData;
import ru.stqa.pft.addressbook.model.ContactRequiredData;

import static ru.stqa.pft.addressbook.appmanager.BaseHelper.clickSubmit;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() throws InterruptedException {

        app.getContactHelper().addNewContact();
        app.getContactHelper().fillContactForm(new ContactRequiredData(TestData.firstName1, TestData.lastName1,
                TestData.mobile, TestData.email));
        clickSubmit();
        app.getNavigationHelper().goToHomePage();

    }

}