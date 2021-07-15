package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.getGroups().all().size() == 0) {
            app.getGroups().create(new GroupData().withName(TestData.groupName1).withHeader(TestData.headerText1));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.getGroups().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withValue(modifiedGroup.getValue()).withName(TestData.groupName2)
                .withFooter(TestData.footerText2);
        app.getGroups().modify(group);
        Groups after = app.getGroups().all();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }
}
