package ru.gorbenko.models;

public class Students {
    int id;
    String name;
    String surname;
    int stipend;
    int kurs;
    String city;
    int age;

    public Students(int id, String name, String surname, int stipend, int kurs, String city, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.stipend = stipend;
        this.kurs = kurs;
        this.city = city;
        this.age = age;
    }

    public Students() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getStipend() {
        return stipend;
    }

    public void setStipend(int stipend) {
        this.stipend = stipend;
    }

    public int getKurs() {
        return kurs;
    }

    public void setKurs(int kurs) {
        this.kurs = kurs;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
