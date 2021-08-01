package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData> {

    private Set<GroupData> delegate;

    public Groups(Groups groups) {
        this.delegate = new HashSet<GroupData>(groups.delegate);
        //из существующего объекта, переданного в качестве параметра строим/копируем новое множество
        // и присваиваем его как атрибут новому объекту

    }

    public Groups() {
        this.delegate = new HashSet<GroupData>();

    }

    public Groups(Collection<GroupData> groups) {
        this.delegate = new HashSet<GroupData>(groups);
    }

    @Override
    protected Set<GroupData> delegate() {
        return delegate;
    }

    public Groups withAdded(GroupData group) {
        Groups groups = new Groups(this);  //создаем копию
        groups.add(group); // в эту копию добавляем объект, переданный в качестве параметра
        return groups;            //возвращаем созданую копию с добавленной группой
    }

    public Groups without(GroupData group) {
        Groups groups = new Groups(this);  //создаем копию
        groups.remove(group); // в эту копию добавляем объект, переданный в качестве параметра
        return groups;            //возвращаем созданую копию с добавленной группой
    }

}