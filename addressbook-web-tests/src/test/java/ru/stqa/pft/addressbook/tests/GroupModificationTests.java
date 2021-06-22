package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import static ru.stqa.pft.addressbook.appmanager.TestData.*;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {

        GroupData groupData = new GroupData(
                groupName2,
                "Header-2",
                "Footer-2");

        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectFirstGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(groupData);
        app.getGroupHelper().submitGroupModification();
        app.getNavigationHelper().returnToGroupPage();

    }

}
