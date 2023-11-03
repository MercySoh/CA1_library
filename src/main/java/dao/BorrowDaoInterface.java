package dao;

import business.Borrow;

import java.util.List;

/**
 * @author Conor
 */

public interface BorrowDaoInterface {

    /**
     * This method allows the user to borrow a book,
     * within 4 weeks, this will decrease the quantity of the books
     * available copies
     *
     * @param user_id the user ID supplied of the individual borrowing the book
     * @param book_id the book ID supplied of the books identifier
     *
     * @return the rows affected in the Borrow table, 1 is for if successful and 0 is when
     *         there is failure
     */
    int bookBorrow(int user_id,int book_id);


    /**
     * This method allows the user to return the borrowed book,
     * there is a check, whether to impose a fine if late, also
     * the books quantity increase as its returned
     *
     * @param user_id the user ID supplied of the individual returning the book
     * @param book_id the book ID supplied of the book being returned
     *
     * @return the rows affected in the Borrow table, 1 is for if successful and 0 is when
     *         there is failure
     */
    int returnBook(int user_id,int book_id);

    /**
     * This method allows the user to view their current loans of books that they
     * have currently borrowed
     *
     * @param user_id the user ID supplied of the individual who holds the loans
     *
     * @return A List containing the active User Loans, in which they have borrowed a book
     */
    List<Borrow> getAllCurrentLoansByUserID(int user_id);

    /**
     * This method allows the user to view their history loans of books that they
     * have borrowed past and present
     *
     * @param user_id the user ID supplied of the individual who holds the loans
     *
     * @return A List containing a history of User Loans
     */
    List<Borrow> getAllLoansByUserID(int user_id);

    /**
     * The method will check if the loan the user is taking already exists,
     * this is used in the borrowBook method to verify if there is a loan present
     * ensuring that only one book is borrowed
     *
     * @param user_id the user ID supplied of the individual who is borrowing the book
     * @param book_id the book ID supplied of the book, to check whether it exists or not
     *
     * @return true or false whether a loan exist or not
     */
    boolean checkingDuplicateLoans(int user_id,int book_id);

    /**
     * The method will check if the book that has been returned late or on time,
     * this will decide if a late fee is to be imposed on the user or not
     *
     * @param user_id the user ID supplied of the individual who has borrowed the book
     * @param book_id the book ID supplied of the book which has been borrowed
     *
     * @void, there is no return
     */
    void imposeLateFee(int user_id,int book_id);
}
