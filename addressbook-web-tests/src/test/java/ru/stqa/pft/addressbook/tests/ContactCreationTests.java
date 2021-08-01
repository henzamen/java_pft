package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(
                new File(app.properties.getProperty("contacts.file.xml"))));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }

        XStream xstream = new XStream();
        XStream.setupDefaultSecurity(xstream);
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.autodetectAnnotations(true);
        xstream.processAnnotations(ContactData.class);



        List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
        return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {
        Contacts before = app.db().contacts();
        //Contacts before = app.getContactHelper().allWithoutId();
        app.getContactHelper().clickAddNew();
        app.getContactHelper().modify(contact, true);
        app.goTo().goToHomePage();

        Contacts after = app.db().contacts();
        //Contacts after = app.getContactHelper().allWithoutId();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(contact
                .withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())
                .withFirstname(contact.getFirstname())
                .withLastname(contact.getLastname())
                .withMobilePhone(contact.getMobilePhone())
                .withWorkPhone(contact.getWorkPhone())
                .withEmail(contact.getEmail())
                .withAddress(contact.getAddress())
                .withNickname(contact.getNickname()))));
   }

}