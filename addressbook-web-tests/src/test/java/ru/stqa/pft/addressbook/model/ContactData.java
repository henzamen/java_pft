package ru.stqa.pft.addressbook.model;


import java.util.Objects;


public class ContactData {

    private int id;
    private String firstname;
    private String lastname;
    private String mobile;
    private String email;
    private String group;
    private String homePhone;
    private  String mobilePhone;
    private  String workPhone;

   //

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

    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return  this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return  this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", group='" + group + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id
                && Objects.equals(firstname, that.firstname)
                && Objects.equals(lastname, that.lastname)
                && Objects.equals(mobile, that.mobile)
                && Objects.equals(email, that.email)
                && Objects.equals(group, that.group)
                && Objects.equals(homePhone, that.homePhone)
                && Objects.equals(mobilePhone, that.mobilePhone)
                && Objects.equals(workPhone, that.workPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, mobile, email, group, homePhone, mobilePhone, workPhone);
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }
}
