package ru.stqa.pft.addressbook.model;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table(name="addressbook")

public class ContactData {

    //1
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id;
    //2
    @Column(name = "firstname")
    private String firstname;
    //3
    @Column(name = "lastname")
    private String lastname;
    //4
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    //5
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    //6
    @Column(name = "email")
    @Type(type = "text")
    private String email;
    //7
    @Column(name = "address")
    @Type(type = "text")
    private String address;
    //8
    @Column(name = "nickname")
    private String nickname;

    //others = transient
    @Transient
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;
    @Transient
    private String allPhones;
    @Transient
    private String email2;
    @Transient
    private String email3;
    @Transient
    private String allEmails;
    @Transient
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;
    @Transient
    private String group;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;    //метод возвращает тот же объект, в котором этот метод вызывается
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    ////////////////////////////////////////////////

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    /////////////////////////////////////////////////

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMobilePhone() { return mobilePhone; }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getEmail() { return email; }

    public String getAddress() { return address; }

    public String getNickname() { return nickname; }

    ////////////////////////////////////////////////

    public String getEmail2() { return email2; }

    public String getEmail3() { return email3; }

    public String getAllEmails() { return allEmails; }

    public String getGroup() {
        return group;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getAllPhones() { return allPhones; }

    public File getPhoto() { return new File(photo); }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstname, that.firstname)&& Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, mobilePhone, workPhone, email, address, nickname);
    }


}