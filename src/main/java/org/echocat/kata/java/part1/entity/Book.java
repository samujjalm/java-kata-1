package org.echocat.kata.java.part1.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "book")
@Data
public class Book {
    @Id
    private String isbn;
    @Column
    private String title;

    @Column
    private String description;

    @ManyToMany
    private Set<Author> authors;

}
