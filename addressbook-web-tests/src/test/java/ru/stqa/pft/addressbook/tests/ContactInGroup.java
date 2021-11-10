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
    int targetGroupValue;
    Contacts contacts;
    ContactData modifiedContact;
    int targetContactId;

    @BeforeMethod
    public void setPreconditions() {
        //check if any group exists
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            String groupName = RandomGenerator.getRandomString(5);
            app.getGroups().create(new GroupData()
                    .withName(groupName)
                    .withHeader("header " + groupName)
                    .withFooter("footer " + groupName));

        }
        //check if any contact exists
        if (app.db().contacts().size() == 0) {
            createContact();
        }
    }

    @Test
    public void addContactToGroup() {
        getContactWithoutGroup();

        app.goTo().clickLinkHome();
        app.getContacts().addToGroup(modifiedContact, targetGroup);
        app.getContactHelper().selectGroupToShow(Integer.toString(targetGroup.getValue()));

        contacts = app.db().contacts();
        groups = app.db().groups();
        modifiedContact = app.getContacts().getContactById(contacts, targetContactId);
        targetGroup = app.getGroups().getGroupByValue(groups, targetGroupValue);

        Assert.assertTrue(app.getContactHelper().isContactInGroup(modifiedContact, targetGroup));

    }

    @Test
    public void removeContactFromGroup() {
        getContactWithGroup();

        app.goTo().clickLinkHome();
        app.getContactHelper().removeContactFromGroup(modifiedContact, targetGroup);
        contacts = app.db().contacts();
        groups = app.db().groups();
        modifiedContact = app.getContacts().getContactById(contacts, targetContactId);
        targetGroup = app.getGroups().getGroupByValue(groups, targetGroupValue);

        Assert.assertFalse(app.getContactHelper().isContactInGroup(modifiedContact, targetGroup));
    }

    ///////////////////////////////PRIVATE METHODS USED//////////////////////////////////////

    private void getContactWithoutGroup() {
        contacts = app.db().contacts();
        groups = app.db().groups();
        targetContactId = 0;
        search:
        for (ContactData contact : contacts) {
            for (GroupData group : groups) {
                if (!app.getContacts().isContactInGroup(contact, group)) {
                    modifiedContact = contact;
                    targetGroup = group;
                    targetContactId = contact.getId();
                    targetGroupValue = group.getValue();
                    break search;
                }
            }

        }
        if (targetContactId == 0) {
            createContact();
            groups = app.db().groups();
            targetGroup = app.getGroups().getLastGroup(groups);
            targetGroupValue = targetGroup.getValue();
        }
    }

    private void createContact() {
        app.goTo().clickLinkHome();
        app.getContacts().clickAddNew();
        app.getContacts().modify(new ContactData()
                        .withFirstname(RandomGenerator.getRandomName(5))
                        .withLastname(RandomGenerator.getRandomName(5))
                        .withNickname(RandomGenerator.getRandomString(5)),
                true);
        contacts = app.db().contacts();
        modifiedContact = app.getContacts().getLastContact(contacts);
        targetContactId = modifiedContact.getId();
    }

    private void getContactWithGroup() {
        contacts = app.db().contacts();
        groups = app.db().groups();
        targetContactId = 0;
        search:
        for (ContactData contact : contacts) {
            for (GroupData group : groups) {
                if (app.getContacts().isContactInGroup(contact, group)) {
                    modifiedContact = contact;
                    targetGroup = group;
                    targetContactId = contact.getId();
                    targetGroupValue = group.getValue();
                    break search;
                }
            }

        }
        if (targetContactId == 0) {
            addContactToGroup();
        }
    }
}