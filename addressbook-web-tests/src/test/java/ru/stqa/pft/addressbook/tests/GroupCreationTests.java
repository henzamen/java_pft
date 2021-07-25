package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() {          //итератор массивов объектов
        List<Object[]> list = new ArrayList<Object[]>();   //список массивов объектов
        list.add(new Object[] {new GroupData().withName("test1").withHeader("header1").withFooter("footer1")});
        list.add(new Object[] {new GroupData().withName("test2").withHeader("header2").withFooter("footer2")});
        list.add(new Object[] {new GroupData().withName("test3").withHeader("header3").withFooter("footer3")});
        return list.iterator();
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.getGroups().all();
        app.getGroups().create(group);
        assertThat(app.getGroups().count(), equalTo(before.size() + 1));
        Groups after = app.getGroups().all();
        assertThat(after, equalTo(
                before.withAdded(group.withValue(after.stream().mapToInt((g) -> g.getValue()).max().getAsInt()))));
    }

    @Test(enabled = false)
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