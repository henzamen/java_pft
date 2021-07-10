package ru.stqa.pft.addressbook.model;


import java.util.Objects;

public class GroupData {
    private int value;
    private final String name;
    private final String header;
    private final String footer;


    public GroupData(int value, String name, String header, String footer) {
        this.value = value;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }

    public GroupData(String name, String header, String footer) {
        this.value = Integer.MAX_VALUE;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }

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

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupData groupData = (GroupData) o;
        return Objects.equals(name, groupData.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "value='" + value + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
