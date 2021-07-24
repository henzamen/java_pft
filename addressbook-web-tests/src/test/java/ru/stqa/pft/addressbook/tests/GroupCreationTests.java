package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.getGroups().all();
        GroupData group = new GroupData().withName(TestData.groupName2).withHeader(TestData.headerText1);
        app.getGroups().create(group);
        assertThat(app.getGroups().count(), equalTo(before.size() + 1));
        Groups after = app.getGroups().all();
        assertThat(after, equalTo(
                before.withAdded(group.withValue(after.stream().mapToInt((g) -> g.getValue()).max().getAsInt()))));
    }

    @Test
    public void testBadGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.getGroups().all();
        GroupData group = new GroupData().withName("test'2");
        app.getGroups().create(group);
        assertThat(app.getGroups().count(), equalTo(before.size()));
        Groups after = app.getGroups().all();
        assertThat(after, equalTo(before));
    }

}