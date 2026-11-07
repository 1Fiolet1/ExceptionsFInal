package ru.seleznev.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Book {
    private String title;

    private String author;

    @Setter
    private int availableCopies;

    @Override
    public String toString() {
        return author + " " + title + " Доступно копий: " + availableCopies;
    }
}