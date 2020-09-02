package org.example.Exercise6_SimpleBoard;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        user.createDefaultUsers();
        Autorization autorization = new Autorization();
        autorization.checkAutorization();
        Menu menu = new Menu();
        menu.runMenu();

        EntityManager entityManager = SimpleBoard.getEntityManager();
        entityManager.close();
        EntityManagerFactory entityManagerFactory = SimpleBoard.getEntityManagerFactory();
        entityManagerFactory.close();
    }
}
