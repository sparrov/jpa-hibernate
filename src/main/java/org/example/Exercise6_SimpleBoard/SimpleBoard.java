package org.example.Exercise6_SimpleBoard;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SimpleBoard {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa.hibernate");
    private static final EntityManager entityManager = entityManagerFactory.createEntityManager();

    private SimpleBoard() {
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
