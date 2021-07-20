package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.getGroups().all().size() == 0) {
            app.getGroups().create(new GroupData().withName(TestData.groupName2).withFooter(TestData.footerText2));
        }
    }

    @Test
    public void testGroupDeletion() {
        Groups before = app.getGroups().all();
        GroupData deletedGroup = before.iterator().next();
        app.getGroups().delete(deletedGroup);
        assertThat(app.getGroups().count(), equalTo(before.size() - 1));
        Groups after = app.getGroups().all();
        assertThat(after, equalTo(before.without(deletedGroup)));
    }
}