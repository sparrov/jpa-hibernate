package org.example.Exercise6_SimpleBoard;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Scanner;

public class Autorization {
    private static String checkedUsername;

    public String getCheckedUsername() {
        return checkedUsername;
    }

    public void checkAutorization() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Wprowadź nazwę użytkownika: ");
        String checkUsername = scanner.nextLine();
        System.out.print("Wprowadź hasło: ");
        String checkPassword = scanner.nextLine();

        EntityManager entityManager = SimpleBoard.getEntityManager();

        try {
            entityManager.getTransaction().begin();
            TypedQuery<String> queryUsername = entityManager.createQuery(
                    "SELECT u.username FROM User u WHERE u.username = :username", String.class);
            queryUsername.setParameter("username", checkUsername);
            checkedUsername = queryUsername.getSingleResult();
            TypedQuery<String> queryPassword = entityManager.createQuery(
                    "SELECT u.password FROM User u WHERE u.password = :password", String.class);
            queryPassword.setParameter("password", checkPassword);
            String checkedPassword = queryPassword.getSingleResult();
            entityManager.getTransaction().commit();

            if (checkUsername.equals(checkedUsername) && checkPassword.equals(checkedPassword)) {
                System.out.println("Witaj " + "\"" + checkedUsername + "\"" + ", jesteś zalogowany.");
            }
        } catch (NoResultException e) {
            System.out.println("Nieprawidlowa nazwa użytkownika lub hasło! Spróbuj ponownie...");
        }

    }


}