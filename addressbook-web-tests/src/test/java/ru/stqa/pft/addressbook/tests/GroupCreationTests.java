package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestData;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData(TestData.groupName1,TestData.headerText,TestData.footerText));
        app.getGroupHelper().submitGroupCreation();
        app.getNavigationHelper().returnToGroupPage();

    }

}