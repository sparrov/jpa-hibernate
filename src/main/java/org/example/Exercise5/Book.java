package org.example.Exercise5;

import javax.persistence.*;
import java.util.List;

//@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JoinTable(name = "authors_books")
    private List<Author> authors;

    @Column(name = "title", nullable = false)
    private String title;

    @OneToMany(mappedBy = "book")
    private List<Chapter> chapters;

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }
}
