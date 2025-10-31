package fr.raphaelmakaryan.cours_springboot.service;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Client {
    @Id
    public int id;
    public String firstName;
    public String lastName;
    public LocalDate birthday;
    public String license;

    public Client(int id, String firstName, String lastName, String license, LocalDate birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.license = license;
        this.birthday = birthday;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getbirthday() {
        return birthday;
    }

    public void setbirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getlicense() {
        return license;
    }

    public void setlicense(String license) {
        this.license = license;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", license=" + license +
                '}';
    }
}
