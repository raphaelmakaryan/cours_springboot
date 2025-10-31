package fr.raphaelmakaryan.cours_springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "license")
    private String license;

    @Column(name = "birthday")
    private LocalDate birthday;

    public Client() {
        super();
    }

    public String getLastName() {
        return this.last_name;
    }

    public void setlastName(String last_name) {
        this.last_name = last_name;
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

    public String getfirst_name() {
        return first_name;
    }

    public void setfirst_name(String first_name) {
        this.first_name = first_name;
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
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", birthday=" + birthday +
                ", license=" + license +
                '}';
    }
}
