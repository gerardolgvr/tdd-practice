package bookstoread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("A bookshelf")
public class BookShelfSpec {

    private BookShelf shelf;

    @BeforeEach
    void init() throws Exception {
        shelf = new BookShelf();
    }

    /* first feature: As a user, I want to add multiple books to my bookshelf so that I can read them later */
    @Test
    @DisplayName("is empty when no book is added to it")
    public void shelfEmptyWhenNoBookAdded() throws Exception {
        List<String> books = shelf.books();
        assertTrue(books.isEmpty(), () -> "BookShelf should be empty.");
    }

    /* first feature: As a user, I want to add multiple books to my bookshelf so that I can read them later */
    @Test
    @DisplayName("bookshelf should have two books when two books added")
    void bookshelfContainsTwooBooksWhenTwoBooksAdded(){
        shelf.add("Effective Java", "Code Complete");
        List<String> books = shelf.books();
        assertEquals(2, books.size(), () -> "BookShelf should have two books.");
    }

    /* first feature: As a user, I want to add multiple books to my bookshelf so that I can read them later */
    @Test
    @DisplayName("when add method is called withoud argument no book will be added")
    public void emptyBookShelfWhenAddAddIsCalledWithoutBooks(){
        shelf.add();
        List<String> books = shelf.books();
        assertTrue(books.isEmpty(), () -> "BookShelf should be empty");
    }

    /* first feature: As a user, I want to add multiple books to my bookshelf so that I can read them later */
    @Test
    @DisplayName("Should not be able to add book to books")
    void booksReturnedFromBookShelfIsImmutableForClient(){
        shelf.add("Effective Java", "Code Complete");
        List<String> books = shelf.books();
        try {
            books.add("The Mythical Man-Month");
            fail(() -> "Should not be able to add book to books");
        } catch (Exception e){
            assertTrue(e instanceof UnsupportedOperationException, () -> "Should throw UnsupportedOperationException");
        }
    }

}
