package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestData;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAnyGroup()) {
            app.getGroupHelper().createGroup(new GroupData(TestData.groupName2,null,TestData.footerText2));
        }
        app.getGroupHelper().selectFirstGroup();
        app.getGroupHelper().deleteGroupLower();
        app.getNavigationHelper().returnToGroupPage();
    }

}