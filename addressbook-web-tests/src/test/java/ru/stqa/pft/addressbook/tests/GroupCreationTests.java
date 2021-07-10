package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData(TestData.groupName2, TestData.headerText1, null);
        app.getGroupHelper().createGroup(group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);

        int max = 0;
        for (GroupData g : after) {
            if (g.getValue() > max) {
                max = g.getValue();
            }
        }

        group.setValue(after.stream().max((o1, o2) -> Integer.compare(o1.getValue(), o2.getValue())).get().getValue());
        before.add(group);
        Comparator<? super GroupData> byValue = (g1, g2) -> Integer.compare(g1.getValue(), g2.getValue());
        before.sort(byValue);
        after.sort(byValue);
        Assert.assertEquals(before, after);
    }

}