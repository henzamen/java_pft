package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.getGroups().all().size() == 0) {
            app.getGroups().create(new GroupData()
                    .withName(app.properties.getProperty("group1.name"))
                    .withHeader(app.properties.getProperty("group1.header"))
            );
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.getGroups().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withValue(modifiedGroup.getValue())
                .withName(app.properties.getProperty("group2.name"))
                .withFooter(app.properties.getProperty("group2.footer"));
        app.getGroups().modify(group);
        assertThat(app.getGroups().count(), equalTo(before.size()));
        Groups after = app.getGroups().all();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }

}