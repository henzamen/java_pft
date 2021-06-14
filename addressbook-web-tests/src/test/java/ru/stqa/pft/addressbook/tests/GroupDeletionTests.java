package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public static void testGroupDeletion() {
        String groupName = "MyFirstGroup";
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup(groupName);
        app.getGroupHelper().deleteGroupLower();
        app.getNavigationHelper().returnToGroupPage();
    }

}