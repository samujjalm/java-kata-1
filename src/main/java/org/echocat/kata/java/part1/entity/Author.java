package org.echocat.kata.java.part1.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "author")
@Data
public class Author {

    @Id
    private String email;
    @Column
    private String firstName;
    @Column
    private String lastName;
}
