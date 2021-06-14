package ru.stqa.pft.addressbook.tests;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactRequiredData;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreation() {
        String name = "Henry";
        String lastName = "Sam";
        String mobile = "+7473 1234567";
        String email = "hensam@list.ru";
        String mobileUpdate = "+7473 1234888";

        app.getContactHelper().addNewContact();
        app.getContactHelper().fillContactForm(new ContactRequiredData(name, lastName, mobile, email));
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectContact(name + " " + lastName);
        app.getContactHelper().clickDetails();
        app.getNavigationHelper().clickLinkHome();
        app.getContactHelper().selectContact(name + " " + lastName);
        app.getContactHelper().updateContactField(name + " " + lastName, By.name("mobile"), mobileUpdate);
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectContact(name + " " + lastName);
        app.getContactHelper().pressDeleteAndAgree();
    }

}