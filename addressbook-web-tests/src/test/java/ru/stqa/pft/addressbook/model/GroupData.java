package ru.stqa.pft.addressbook.model;


import java.util.Objects;

public class GroupData {
    private int value;
    private String name;
    private String header;
    private String footer;

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    public int getValue() { return value; }

    public GroupData withValue(int value) {
        this.value = value;
        return this;
    }

    public GroupData withName(String name) {
        this.name = name;
        return this;
    }

    public GroupData withHeader(String header) {
        this.header = header;
        return this;
    }

    public GroupData withFooter(String footer) {
        this.footer = footer;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupData groupData = (GroupData) o;
        return value == groupData.value && Objects.equals(name, groupData.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, name);
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "value='" + value + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
