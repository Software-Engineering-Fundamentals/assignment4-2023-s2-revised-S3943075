
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Library Card associated with the Student 
 */
public class LibraryCard {
    /**
     * Card id 
     */
    private int ID;

    /**
     * Issue Date of the Card
     */
    private Date IssueDate;

    /**
     * Expiry Date of the Card
     */
    private Date ExpiryDate;

    /**
     * Number of books borrowed
     */
    private List<Book> borrowed = new ArrayList<Book>();

    /**
     * Fine asscoaited with the card
     */
    private double fine;

    /**
     *  Details about the cardholder
     */
    private Student student;




    public LibraryCard(Student student, Date IssueDate, Date ExpiryDate, int ID) {
        this.student = student;
        this.IssueDate = IssueDate;
	   this.ExpiryDate = ExpiryDate;
	   this.ID = ID;
    }


    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }


    public Date getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(Date IssueDate) {
        this.IssueDate = IssueDate;
    }

    public Date getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(Date ExpiryDate) {
        this.ExpiryDate = ExpiryDate;
    }

    
    public List<Book> getBooks() {
        return borrowed;
    }

    

    /**
     * Issue a new book
     * @param Book: book to borrow 
     * @return true if the book is successfully borrowed, false otherwise
     */

     public boolean issueBook(Book book) throws IllegalBookIssueException{
        // Get the number of books borrowed by the student on the library card
        int numberOfBooksBorrowed = getBooks().size();
    
        // Check if the number of books borrowed is not greater than or equal to 4
        if (numberOfBooksBorrowed >= 4) {
            return false;
        }
    
        // Check if the same book is already issued on the library card
        if (getBooks().contains(book)) {
            throw new IllegalBookIssueException("The same book is already issued on the library card");
        }
    
        // Check that the library card is still valid
        Date currentDate = new Date();
        if (currentDate.after(getExpiryDate())) {
            // Library card is expired
            return false;
        }
    
        // Check that the book is available for borrowing
        if (!book.getStatus()) {
            // Book is not available
            return false;
        }
    
        // The book should not be issued if there is a pending fine associated with the library card
        if (getFine() > 0) {
            return false;
        }
    
        // If the above constraints are met, then issue the book to the student
        int borrowingDays = (book.getDemand() == 1) ? 3 : 15;
        book.setDays(borrowingDays);
        getBooks().add(book);
    
        // Return true to indicate successful book issuance
        return true;
    }



}
