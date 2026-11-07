package ru.seleznev;

import ru.seleznev.domain.Book;
import ru.seleznev.exceptions.ItemNotFoundException;
import ru.seleznev.exceptions.NoAvailableCopiesException;
import ru.seleznev.services.Library;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main( String[] args ) {
        System.out.println("Добро пожаловать в библиотеку!");
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        int option;

        myLoop:
        while (true) {
            System.out.println("""
                
                1. Вывести каталог.
                2. Добавить объект.
                3. Выдать объект.
                4. Вернуть объект.
                5. Выйти из приложения.
                
                Введите число:""");
            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Повторите ввод");
                continue;
            }

            if (option < 1 || option > 5) {
                System.out.println("введите число от 1 до 5");
                continue;
            }

            switch (option) {
                case 1:
                    List<Book> books = library.getAllBooks();
                    if (books.isEmpty()) {
                        System.out.println("Книг пока нет");
                        break;
                    }
                    books.forEach(it -> System.out.println(it.toString()));
                    break;
                case 2:
                    try {
                        System.out.println("Введите название, автора и количество копий (3 строками)");
                        String title = scanner.nextLine();
                        String author = scanner.nextLine();
                        int copies = scanner.nextInt();
                        scanner.nextLine();
                        library.addBook(title, author, copies);
                        System.out.println("Книга добавлена");
                    } catch (InputMismatchException e) {
                        System.out.println("Введены неккоректные данные");
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Введите название книги");
                        String title = scanner.nextLine();
                        library.takeBook(title);
                        System.out.println("Книга выдана");
                    } catch (InputMismatchException e) {
                        System.out.println("Введены неккоректнок название книги");
                        System.out.println(e.getMessage());
                    } catch (NoAvailableCopiesException e) {
                        System.out.println("На данный момент нету доступных копий книги");
                    } catch (ItemNotFoundException e) {
                        System.out.println("Такая книга не найдена");
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Введите название книги");
                        String title = scanner.nextLine();
                        library.returnBook(title);
                        System.out.println("Книга возвращена в библиотеку");
                    } catch (InputMismatchException e) {
                        System.out.println("Введены неккоректнок название книги");
                        System.out.println(e.getMessage());
                    } catch (ItemNotFoundException e) {
                        System.out.println("Такая книга не найдена");
                    }
                    break;
                case 5:
                    System.out.println("До свидания!");
                    break myLoop;
            }


        }

        scanner.close();
    }
}
