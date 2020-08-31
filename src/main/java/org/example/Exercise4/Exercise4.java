package org.example.Exercise4;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

import static org.example.Exercise4.Gender.FEMALE;
import static org.example.Exercise4.Gender.MALE;


public class Exercise4 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa.hibernate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        Employee emp1 = new Employee(
                "Ilona",
                "Wróbel",
                11111111111L,
                LocalDate.of(1990, 5, 17),
                (short) 30,
                FEMALE,
                new Address("Wspólna", "Nowy Tuchom", "80-209", "POLSKA")
        );
        Employee emp2 = new Employee(
                "Tymon",
                "Wróbel",
                11111111112L,
                LocalDate.of(2018, 9, 26),
                (short) 1,
                MALE,
                new Address("Wspólna", "Nowy Tuchom", "80-209", "POLSKA")
        );


        entityManager.getTransaction().begin();
        entityManager.persist(emp1);
        entityManager.persist(emp2);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        Employee empF1 = entityManager.find(Employee.class, 1);
        Employee empF2 = entityManager.find(Employee.class, 2);
        System.out.println(empF1);
        System.out.println(empF2);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        emp1.setLastName("Chróścińska");
        emp1.setAddress(new Address("1", "1", "1", "GB"));
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        System.out.println("Done!");
    }
}
