package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        String groupName = "MyFirstGroup";
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("MyFirstGroup", "First",
                "This is my first Test with Katalon Recorder."));
        app.getGroupHelper().submitGroupCreation();
        app.clickGroupPageInMessage();
        app.getGroupHelper().selectGroup(groupName);
        app.editUpdateGroup();
        app.getNavigationHelper().returnToGroupPage();
        app.getGroupHelper().selectGroup(groupName);
        //deleteGroup();
        app.logout();
    }

}