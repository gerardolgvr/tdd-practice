package bookstoread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("A bookshelf")
public class BookShelfSpec {

    private BookShelf shelf;
    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;

    @BeforeEach
    void init() throws Exception {
        shelf = new BookShelf();
        effectiveJava = new Book("Effective Java", "Joshua Bloch", LocalDate.of(2008, Month.MAY, 8));
        codeComplete = new Book("Code Complete", "Joshua Bloch", LocalDate.of(2004, Month.JUNE, 9));
        mythicalManMonth = new Book("The Mythical Man-Month", "Joshua Bloch", LocalDate.of(1975, Month.JANUARY, 1));
    }

    /* first feature: As a user, I want to add multiple books to my bookshelf so that I can read them later */
    @Test
    @DisplayName("bookshelf is empty when no book is added to it")
    public void shelfEmptyWhenNoBookAdded() throws Exception {
        List<Book> books = shelf.books();
        assertTrue(books.isEmpty(), () -> "BookShelf should be empty.");
    }

    /* first feature: As a user, I want to add multiple books to my bookshelf so that I can read them later */
    @Test
    @DisplayName("bookshelf contains two books when two books are added")
    void bookshelfContainsTwooBooksWhenTwoBooksAdded(){
        shelf.add(effectiveJava, codeComplete);
        List<Book> books = shelf.books();
        assertEquals(2, books.size(), () -> "BookShelf should have two books.");
    }

    /* first feature: As a user, I want to add multiple books to my bookshelf so that I can read them later */
    @Test
    @DisplayName("empty bookshelf remains empty when add is called without books")
    public void emptyBookShelfWhenAddAddIsCalledWithoutBooks(){
        shelf.add();
        List<Book> books = shelf.books();
        assertTrue(books.isEmpty(), () -> "BookShelf should be empty");
    }

    /* first feature: As a user, I want to add multiple books to my bookshelf so that I can read them later */
    @Test
    @DisplayName("bookshelf returns an immutable books collection to client")
    void booksReturnedFromBookShelfIsImmutableForClient(){
        shelf.add(effectiveJava, codeComplete);
        List<Book> books = shelf.books();
        try {
            books.add(mythicalManMonth);
            fail(() -> "Should not be able to add book to books");
        } catch (Exception e){
            assertTrue(e instanceof UnsupportedOperationException, () -> "Should throw UnsupportedOperationException");
        }
    }

    /* second feature: As a user, I should be able to arrange my bookshelf based on certain criteria */
    @Test
    @DisplayName("bookshelf is arranged lexicographically by book title")
    void bookshelfArrangedByBookTitle(){
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);
        List<Book> books = shelf.arrange();
        assertEquals(Arrays.asList(codeComplete, effectiveJava, mythicalManMonth), books, () -> "Books in a bookshelf should be arranged lexicographically by book title");
    }

    /* second feature: As a user, I should be able to arrange my bookshelf based on certain criteria */
    @Test
    @DisplayName("Books in bookshelf are in insertion order")
    void booksInBookShelfAreInInsertionOrderAfterCallingArrange(){
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);
        shelf.arrange();
        List<Book> books = shelf.books();
        assertEquals(Arrays.asList(effectiveJava, codeComplete, mythicalManMonth), books, () -> "Books in bookshelf are in insertion order");
    }

    /* second feature: As a user, I should be able to arrange my bookshelf based on certain criteria */
    @Test
    @DisplayName("bookshelf is arranged by user provided criteria (by book title lexicographically)")
    void bookshelfArrangedByUserProvidedCriteria(){
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);
        Comparator<Book> reversed = Comparator.<Book>naturalOrder().reversed();
        List<Book> books = shelf.arrange(reversed);
        assertThat(books).isSortedAccordingTo(reversed);
    }

    /* third feature: As user, I should be able to group books in my bookshelf based on certain criteria */


}
