package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {

    private Set<ContactData> delegate;

    public Contacts(Contacts contacts) {
        this.delegate = new HashSet<ContactData>(contacts.delegate);
        //из существующего объекта, переданного в качестве параметра строим/копируем новое множество
        // и присваиваем его как атрибут новому объекту

    }

    public Contacts() {
        this.delegate = new HashSet<ContactData>();

    }


    @Override
    protected Set<ContactData> delegate() {
        return delegate;
    }

    public Contacts withAdded(ContactData contact) {
        Contacts contacts = new Contacts(this);  //создаем копию
        contacts.add(contact); // в эту копию добавляем объект, переданный в качестве параметра
        return contacts;            //возвращаем созданую копию с добавленной группой
    }

    public Contacts without(ContactData contact) {
        Contacts contacts = new Contacts(this);  //создаем копию
        contacts.remove(contact); // в эту копию добавляем объект, переданный в качестве параметра
        return contacts;            //возвращаем созданую копию с добавленной группой
    }

}
