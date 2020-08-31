package org.example.Exercise4;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

//@Entity
//@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Integer id;

    @Column(name = "first_name", nullable = false, length = 100)
    String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    String lastName;

    @Column(name = "pesel", nullable = false, unique = true, length = 11)
    Long pesel;

    @Column(name = "birth_day", nullable = false)
    LocalDate birthDate;

    @Transient
    short age;

    @Enumerated(value = EnumType.STRING)
    Gender gender;

    @Embedded
    Address address;

    public Employee() {
    }

    public Employee(String firstName, String lastName, Long pesel, LocalDate birthDate, short age, Gender gender, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.birthDate = birthDate;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPesel(Long pesel) {
        this.pesel = pesel;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pesel=" + pesel +
                ", birthDate=" + birthDate +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
