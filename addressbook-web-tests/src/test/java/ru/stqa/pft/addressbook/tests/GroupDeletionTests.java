package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestData;

public class GroupDeletionTests extends TestBase {

    @Test
    public static void testGroupDeletion() {

        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup(TestData.groupName2);
        app.getGroupHelper().deleteGroupLower();
        app.getNavigationHelper().returnToGroupPage();
    }

}