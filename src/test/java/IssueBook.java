
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;


public class IssueBook {

    private LibraryCard libraryCard;
    private Book availableBook;
    private Book alreadyIssuedBook;

    @BeforeEach
    public void setUp() throws IllegalBookIssueException {
        // Initialize test objects
        Student student = new Student("John Doe", 123);
        Date issueDate = new Date();
        Date expiryDate = new Date(issueDate.getTime() + (30 * 24 * 60 * 60 * 1000)); // Assuming 30 days validity
        libraryCard = new LibraryCard(student, issueDate, expiryDate, 1);

        // Initialize an available book
        availableBook = new Book(1, "Java Programming", 0);

        // Initialize a book that is already issued
        alreadyIssuedBook = new Book(2, "Data Structures", 1);
        libraryCard.issueBook(alreadyIssuedBook); // Issue the book to make it already issued
    }

    //Showing the number of books borrowed
    @Test
    @DisplayName("Test getting the number of books borrowed")
    public void testGetNumberOfBooksBorrowed() {
        assertEquals(0, libraryCard.getBooks().size());
    }

    //testing if the number of books borrowed is greater than 4
    @Test
    @DisplayName("Test if the number of books borrowed is greater than 4")
    public void testIssueMoreThanFourBooks() throws IllegalBookIssueException {
        for (int i = 0; i < 5; i++) {
            libraryCard.issueBook(availableBook);
        }
        assertEquals(0, libraryCard.getBooks().size());
    }

    //Testing if the exception works
    @Test
    @DisplayName("Test if the exception works if the same book is already issued")
    public void testIssueAlreadyIssuedBook() {
        assertThrows(IllegalBookIssueException.class, () -> libraryCard.issueBook(alreadyIssuedBook));
    }

    //Testing if the library card is valid
    @Test
    @DisplayName("Test if the library card is still valid")
    public void testValidLibraryCard() throws IllegalBookIssueException {
        assertEquals(true, libraryCard.issueBook(availableBook));
    }

    //Testing if there is a fine associated with the library card
    @Test
    @DisplayName("Test if there is a fine associated with the library card")
    public void testFineAssociated() throws IllegalBookIssueException {
        libraryCard.setFine(10.0);
        assertEquals(false, libraryCard.issueBook(availableBook));
    }

    //Testing if the book is in high demand and the associated time it can be issued for
    @Test
    @DisplayName("Test if the book is high demand or low demand and see how long it can be issued for")
    public void testIssueBookBasedOnDemand() throws IllegalBookIssueException {
        // Assuming it's low demand, so it can be issued for 15 days
        assertEquals(true, libraryCard.issueBook(availableBook)); 
        assertEquals(15, availableBook.days());
    }



    
}
