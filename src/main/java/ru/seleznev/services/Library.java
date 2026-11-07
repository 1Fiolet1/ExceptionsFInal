package ru.seleznev.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.seleznev.domain.Book;
import ru.seleznev.exceptions.ItemNotFoundException;
import ru.seleznev.exceptions.NoAvailableCopiesException;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class Library {

    private List<Book> books = new ArrayList<>();

    public void addBook(String title, String author, int copies) {

        if (title == null || author == null) {
            throw new IllegalArgumentException("Title or Author is null");
        }

        int indexOfBook = getIndexOfBook(title);

        if (indexOfBook != -1) {
            int currenCopies = books.get(indexOfBook).getAvailableCopies();
            books.get(indexOfBook).setAvailableCopies(currenCopies + copies);
        } else {
            books.add(new Book(title, author, copies));
        }

    }

    public void takeBook(String title) throws NoAvailableCopiesException, ItemNotFoundException {
        int indexOfBook = findIndexOfBookAndValidate(title);

        int currenCopies = books.get(indexOfBook).getAvailableCopies();

        if (currenCopies == 0) {
            throw new NoAvailableCopiesException("Zero copies");
        }

        books.get(indexOfBook).setAvailableCopies(currenCopies - 1);
    }

    public void returnBook(String title) throws ItemNotFoundException {

        int indexOfBook = findIndexOfBookAndValidate(title);

        int currenCopies = books.get(indexOfBook).getAvailableCopies();
        books.get(indexOfBook).setAvailableCopies(currenCopies + 1);
    }

    public List<Book> getAllBooks() {
        return books;
    }

    private int getIndexOfBook(String title) {
        for (int i = 0; i < books.size(); ++i) {
            if (books.get(i).getTitle().equals(title)) {
                return i;
            }
        }

        return -1;
    }

    private int findIndexOfBookAndValidate(String title) throws ItemNotFoundException {
        if (title == null) {
            throw new IllegalArgumentException("title is null");
        }

        int indexOfBook = getIndexOfBook(title);
        if (indexOfBook == -1) {
            throw new ItemNotFoundException("No available copies");
        }

        return indexOfBook;
    }
}
