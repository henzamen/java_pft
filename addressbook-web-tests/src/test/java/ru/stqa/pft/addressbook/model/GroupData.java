package ru.stqa.pft.addressbook.model;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@XStreamAlias("group")
@Entity
@Table(name = "group_list")
public class GroupData {

    @XStreamOmitField  // не сохранять в xml следующее поле
    @Id
    @Column(name = "group_id")
    private int value = Integer.MAX_VALUE;

    @Column(name = "group_name")
    private String name;

    @Column(name = "group_header")
    @Type(type = "text")
    private String header;

    @Column(name = "group_footer")
    @Type(type = "text")
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

    public int getValue() {
        return value;
    }

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
        return value == groupData.value && Objects.equals(name, groupData.name)
                && Objects.equals(header, groupData.header) && Objects.equals(footer, groupData.footer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, name, header, footer);
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "value=" + value +
                ", name='" + name + '\'' +
                ", header='" + header + '\'' +
                ", footer='" + footer + '\'' +
                '}';
    }

}