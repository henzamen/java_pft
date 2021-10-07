package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.generators.RandomGenerator;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


public class ContactInGroup extends TestBase {

    Groups groups;
    GroupData targetGroup;
    int lastGroupValue;
    Contacts contacts;
    ContactData modifiedContact;
    int lastContactId;

    @BeforeMethod
    public void setPreconditions() {

        app.goTo().clickLinkHome();
        app.getContacts().clickAddNew();
        app.getContacts().modify(new ContactData()
                        .withFirstname(RandomGenerator.getRandomName(5))
                        .withLastname(RandomGenerator.getRandomName(5))
                        .withNickname(RandomGenerator.getRandomString(5)),
                true);
        app.goTo().groupPage();
        String groupName = RandomGenerator.getRandomString(5);
        app.getGroups().create(new GroupData()
                .withName(groupName)
                .withHeader("header " + groupName)
                .withFooter("footer " + groupName));
    }

    @Test
    public void addContactToGroup() {
        groups = app.db().groups();
        targetGroup = app.getGroups().getLastGroup(groups);
        lastGroupValue = targetGroup.getValue();
        contacts = app.db().contacts();
        modifiedContact = app.getContacts().getLastContact(contacts);
        lastContactId = modifiedContact.getId();

        app.goTo().clickLinkHome();
        app.getContacts().addToGroup(modifiedContact, targetGroup);
        app.getContactHelper().selectGroupToShow(Integer.toString(targetGroup.getValue()));

        contacts = app.db().contacts();
        groups = app.db().groups();
        modifiedContact = app.getContacts().getContactById(contacts, lastContactId);
        targetGroup = app.getGroups().getGroupByValue(groups, lastGroupValue);

        Assert.assertTrue(app.getContactHelper().isContactInGroup(modifiedContact, targetGroup));

    }

    @Test
    public void removeContactFromGroup() {
        app.goTo().clickLinkHome();
        app.getContactHelper().removeContactFromGroup(modifiedContact, targetGroup);
        contacts = app.db().contacts();
        groups = app.db().groups();
        modifiedContact = app.getContacts().getContactById(contacts, lastContactId);
        targetGroup = app.getGroups().getGroupByValue(groups, lastGroupValue);

        Assert.assertFalse(app.getContactHelper().isContactInGroup(modifiedContact, targetGroup));

    }

}