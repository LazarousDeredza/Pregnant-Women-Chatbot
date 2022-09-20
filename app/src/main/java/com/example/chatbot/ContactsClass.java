package com.example.chatbot;

public class ContactsClass {

    String name,location,phone,profession;

    public ContactsClass() {
    }

    public ContactsClass(String name, String location, String phone, String profession) {
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
