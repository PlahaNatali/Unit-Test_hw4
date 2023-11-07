package fourth.book;


import seminars.fourth.book.Book;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import seminars.fourth.book.BookRepository;
import seminars.fourth.book.BookService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Test
    public void BookGetTest() {
        String id = "1";
        String titile = "Руслан и Людмила";
        String author = "А.С. Пушкин";
        Book book = new Book(id, titile, author);
        assertEquals(book.getId(), id);
        assertEquals(book.getTitle(), titile);
        assertEquals(book.getAuthor(), author);
    }

    @Test
    public void BookSetTest() {
        String id = "1";
        String titile = "Руслан и Людмила";
        String author = "А.С. Пушкин";
        Book book = new Book("0");
        book.setId(id);
        book.setTitle(titile);
        book.setAuthor(author);
        assertEquals(book.getId(), id);
        assertEquals(book.getTitle(), titile);
        assertEquals(book.getAuthor(), author);
    }

    @Test
    public void BookServiceFindByIdTest() {
        String id = "1";
        String titile = "Руслан и Людмила";
        String author = "А.С. Пушкин";
        BookRepository bookRepository = mock(BookRepository.class);
        BookService bookService = new BookService(bookRepository);
        when(bookRepository.findById("1")).thenReturn(new Book(id, titile, author));

        Book book = bookService.findBookById("1");

        verify(bookRepository).findById("1");
        assertEquals(book.getId(), id);
        assertEquals(book.getTitle(), titile);
        assertEquals(book.getAuthor(), author);

        when(bookRepository.findById("")).thenThrow(IllegalArgumentException.class);;
        assertThrows(IllegalArgumentException.class, () -> bookService.findBookById(""));
    }

    @Test
    public void BookServiceFindAllTest() {
        BookRepository bookRepository = mock(BookRepository.class);
        BookService bookService = new BookService(bookRepository);
        when(bookRepository.findAll()).thenReturn(Arrays.asList(new Book("1", "1", "1"),
                new Book("2", "2", "2"),
                new Book("3", "3", "3")));

        List<Book> books = bookService.findAllBooks();

        verify(bookRepository).findAll();
        assertEquals(books.size(), 3);
        assertEquals(books.get(1).getId(), "2");
        assertEquals(books.get(1).getTitle(), "2");
        assertEquals(books.get(1).getAuthor(), "2");
    }
}