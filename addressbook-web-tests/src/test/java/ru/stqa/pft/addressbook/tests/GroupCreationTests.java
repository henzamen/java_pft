package ru.stqa.pft.addressbook.tests;


import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {
    Logger logger = LoggerFactory.getLogger(GroupCreationTests.class);

    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(app.properties.getProperty("groups.file.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(GroupData.class);
            List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml); //XStream возвращает объект неизвестного типа (здесь же приведение к известному)
            //groups.stream().map((g) -> new Object[] {g});      // к каждому объекту типа GroupData применить функцию-обвертку в массив из одного такого объекта
            return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();    //из потока собрать обратно список
        }

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

    @Test
    public void testBadGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.getGroups().all();
        GroupData group = new GroupData().withName(app.properties.getProperty("group.badName"));
        app.getGroups().create(group);
        assertThat(app.getGroups().count(), equalTo(before.size()));
        Groups after = app.getGroups().all();
        assertThat(after, equalTo(before));
    }

}