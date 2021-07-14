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
        Groups before = app.group().all();
        GroupData group = new GroupData().withName(TestData.groupName2).withHeader(TestData.headerText1);
        app.group().create(group);
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size() + 1)); // объект before остается неизменным
        // поэтому можно и здесь и в конце делать проверку

        assertThat(after, equalTo(
                before.withAdded(group.withValue(after.stream().mapToInt((g) -> g.getValue()).max().getAsInt()))));
    }

}