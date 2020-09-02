package org.example.Exercise6_SimpleBoard;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void createDefaultUsers() {
        User user1 = new User("test", "test");
        User user2 = new User("admin", "admin");
        User user3 = new User("root", "root");

        EntityManager entityManager = SimpleBoard.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.persist(user3);
        entityManager.getTransaction().commit();
    }
}

