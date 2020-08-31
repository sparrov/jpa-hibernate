package org.example.Exercise5;

import javax.persistence.*;

//@Entity
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Chapter() {
    }

    public Chapter(int number, String title) {
        this.number = number;
        this.title = title;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
