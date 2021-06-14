package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        String groupName = "MyFirstGroup";
        String headerText = "My First Header";
        String footerText = "My First Footer";
        String textUpdate = "This is my first Test with Katalon Recorder.";

        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData(groupName,headerText,footerText));
        app.getGroupHelper().submitGroupCreation();
        app.getNavigationHelper().returnToGroupPage();
        app.getGroupHelper().selectGroup(groupName);
        app.getGroupHelper().updateGroupField(By.name("group_footer"),textUpdate);
        app.getNavigationHelper().returnToGroupPage();
        app.getGroupHelper().selectGroup(groupName);
        app.getGroupHelper().deleteGroupLower();
    }


}