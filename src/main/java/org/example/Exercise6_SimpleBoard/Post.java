package org.example.Exercise6_SimpleBoard;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post() {
    }

    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void addNewPost() {
        Autorization autorization = new Autorization();
        Scanner scanner = new Scanner(System.in);
        EntityManager entityManager = SimpleBoard.getEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<User> queryUser = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class);
        queryUser.setParameter("username", autorization.getCheckedUsername());
        User loggedInUser = queryUser.getSingleResult();
        entityManager.getTransaction().commit();
        String postTitle;
        String postContent;
        System.out.print("Podaj tytuł posta: ");
        postTitle = scanner.nextLine();
        System.out.print("Wpisz treść posta: ");
        postContent = scanner.nextLine();
        Post post = new Post(postTitle, postContent, loggedInUser);
        entityManager.getTransaction().begin();
        entityManager.persist(post);
        entityManager.getTransaction().commit();
        System.out.println("Post został dodany do bazy");
    }

    public void readPost() {
        EntityManager entityManager = SimpleBoard.getEntityManager();
        Scanner scanner = new Scanner(System.in);
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
        try {
            String selectedPost;
            selectedPost = scanner.nextLine();

            entityManager.getTransaction().begin();
            TypedQuery<String> queryContentOfPost = entityManager.createQuery(
                    "SELECT p.content FROM Post p WHERE p.title = :title", String.class);
            queryContentOfPost.setParameter("title", selectedPost);
            String readedContent = queryContentOfPost.getSingleResult();
            entityManager.getTransaction().commit();
            System.out.println(readedContent);
        } catch (NoResultException e) {
            System.out.println("Nie znaleziono takiego posta w bazie danych! Spróbuj jeszcze raz...");
        }
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", user=" + user +
                '}';
    }
}

