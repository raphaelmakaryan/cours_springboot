package fr.raphaelmakaryan.cours_springboot.service;

import java.time.LocalDate;

public class Client {
    public int id;
    public String firstName;
    public String lastName;
    public LocalDate birthDate;
    public String numberDrive;

    public Client(int id, String firstName, String lastName, String numberDrive, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberDrive = numberDrive;
        this.birthDate = birthDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNumberDrive() {
        return numberDrive;
    }

    public void setNumberDrive(String numberDrive) {
        this.numberDrive = numberDrive;
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
                ", birthDate=" + birthDate +
                ", numberDrive=" + numberDrive +
                '}';
    }
}
