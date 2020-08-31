package org.example.Exercise6;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa.hibernate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        User user1 = new User("test", "test");
        User user2 = new User("admin", "admin");
        User user3 = new User("root", "root");

        entityManager.getTransaction().begin();
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.persist(user3);
        entityManager.getTransaction().commit();

        String checkUsername;
        String checkPassword;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Wprowadź nazwę użytkownika: ");
        checkUsername = scanner.nextLine();
        System.out.print("Wprowadź hasło: ");
        checkPassword = scanner.nextLine();

        try {
            entityManager.getTransaction().begin();
            TypedQuery<String> queryUsername = entityManager.createQuery(
                    "SELECT u.username FROM User u WHERE u.username = :username", String.class);
            queryUsername.setParameter("username", checkUsername);
            String checkedUsername = queryUsername.getSingleResult();

            TypedQuery<String> queryPassword = entityManager.createQuery(
                    "SELECT u.password FROM User u WHERE u.password = :password", String.class);
            queryPassword.setParameter("password", checkPassword);
            String checkedPassword = queryPassword.getSingleResult();

            TypedQuery<User> queryUser = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username", User.class);
            queryUser.setParameter("username", checkUsername);
            User loggedInUser = queryUser.getSingleResult();

            entityManager.getTransaction().commit();

            if (checkUsername.equals(checkedUsername) && checkPassword.equals(checkedPassword)) {
                System.out.println("Witaj " + checkedUsername + ", jesteś zalogowany");
                int selectedOption;
                String postTitle;
                String postContent;
                do {
                    System.out.println("Wybierz opcję z menu:");
                    System.out.println("1 - Dodaj nowy post");
                    System.out.println("2 - Odczytaj post");
                    System.out.println("3 - Zakończ program");
                    System.out.print("Wprowadź numer opcji z menu: ");
                    selectedOption = scanner.nextInt();
                    scanner.nextLine();
                    switch (selectedOption) {
                        case 1:
                            System.out.print("Podaj tytuł posta: ");
                            postTitle = scanner.nextLine();
                            System.out.print("Wpisz treść posta: ");
                            postContent = scanner.nextLine();
                            Post post = new Post(postTitle, postContent, loggedInUser);
                            entityManager.getTransaction().begin();
                            entityManager.persist(post);
                            entityManager.getTransaction().commit();
                            System.out.println("Post został dodany do bazy");
                            break;
                        case 2:
                            entityManager.getTransaction().begin();
                            TypedQuery<String> queryTitlesOfPosts = entityManager.createQuery(
                                    "SELECT p.title FROM Post p", String.class
                            );
                            List<String> titlesOfPosts = queryTitlesOfPosts.getResultList();
                            entityManager.getTransaction().commit();
                            System.out.println("Lista tytułów wszystkich postów:");
                            for (String title : titlesOfPosts) {
                                System.out.println(title);
                            }
                            System.out.println("Wpisz tytuł posta, który chcesz odczytać:");
                            String selectedPost;
                            selectedPost = scanner.nextLine();

                            entityManager.getTransaction().begin();
                            TypedQuery<String> queryContentOfPost = entityManager.createQuery(
                                    "SELECT p.content FROM Post p WHERE p.title = :title", String.class);
                            queryContentOfPost.setParameter("title", selectedPost);
                            String readedContent = queryContentOfPost.getSingleResult();
                            entityManager.getTransaction().commit();
                            System.out.println(readedContent);
                            break;
                        default:
                            System.out.println("Nieprawidłowy wybór! Spróbuj jeszcze raz...");
                    }
                }
                while (selectedOption != 3);
                System.out.println("Koniec programu!");

            }
        } catch (NoResultException e) {
            System.out.println("Nieprawidlowa nazwa użytkownika lub hasło! Spróbuj ponownie...");
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}
